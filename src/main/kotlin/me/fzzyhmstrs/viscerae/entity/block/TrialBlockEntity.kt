package me.fzzyhmstrs.viscerae.entity.block

import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.interfaces.TrialTracking
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import me.fzzyhmstrs.viscerae.registry.RegisterTag
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

class TrialBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(RegisterEntity.TRIAL_BLOCK_ENTITY,pos,state) {

    private var active = false
    private var activeTrialsWon = 0
    private var activePlayers: List<PlayerEntity> = listOf()
    private var activeEntities: List<LivingEntity> = listOf()
    private var trialTimer = 0
    private var finishedTimer = -1
    private var offeredStack = ItemStack.EMPTY
    private var trial = FALLBACK
    private var bar: ServerBossBar? = null


    fun activate(player: PlayerEntity, hand: Hand, world: World): Boolean{
        val stack = player.getStackInHand(hand)
        if (offeredStack != ItemStack.EMPTY || active || finishedTimer > 0){
            return false
        }
        val possibleTrials = Trials.checkKeyItem(stack.item)
        if (possibleTrials.isEmpty()) return false
        
        offeredStack = stack.copy()
        player.setStackInHand(hand, ItemStack.EMPTY)
        
        trial = possibleTrials[world.random.nextInt(possibleTrials.size)]
        
        if (world.registryKey == World.NETHER){
            val testId = Identifier(trial.namespace,"nether_${trial.path}")
            if (Trials.getTrialData(testId) != null){
                trial = testId
            }
        }
        val trialToActivate = Trials.getTrialData(trial)?:Trials.getTrialData(FALLBACK)?:return false
        val w = trialToActivate.getWidth()
        val h = trialToActivate.getHeight()
        val box = Box(pos.add(w,h,w),pos.add(-w,-h,-w))
        val players = world.getNonSpectatingEntities(PlayerEntity::class.java,box)
        var trialsWon = 0
        bar = ServerBossBar(trialToActivate.trialTitle,BossBar.Color.RED,BossBar.Style.NOTCHED_10)
        for (plyr in players){
            if (plyr is ServerPlayerEntity) {
                bar?.addPlayer(plyr)
            }
            trialsWon += (plyr as TrialTracking).trialsWon()
        }
        activeTrialsWon = trialsWon
        active = true
        return true
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        nbt.putBoolean("trial_active",active)
        nbt.putInt("active_trials_won",activeTrialsWon)
        nbt.putInt("trial_timer",trialTimer)
        nbt.putInt("finished_timer",finishedTimer)
        val stack = NbtCompound()
        offeredStack.writeNbt(stack)
        nbt.put("offered_stack",stack)
        nbt.putString("trial_id",trial.toString())
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        if (nbt.contains("trial_active")) {
            active = nbt.getBoolean("trial_active")
        }
        if (nbt.contains("active_trials_won")){
            activeTrialsWon = nbt.getInt("active_trials_won")
        }
        if (nbt.contains("trial_timer")){
            trialTimer = nbt.getInt("trial_timer")
        }
        if (nbt.contains("finished_timer")){
            finishedTimer = nbt.getInt("finished_timer")
        }
        if (nbt.contains("offered_stack")) {
            val stack = nbt.getCompound("offered_stack")
            offeredStack = ItemStack.fromNbt(stack)
        }
        if (nbt.contains("trial_id")){
            trial = Identifier.tryParse(nbt.getString("trial_id"))?: FALLBACK
        }
    }

    companion object{

        private val FALLBACK = Identifier(Viscerae.MOD_ID,"zombie_trial")
        private const val FINISHED = 12000


        fun tick(world: World, pos: BlockPos, state2: BlockState, blockEntity: TrialBlockEntity){
            // process the delay between trials
            if (blockEntity.finishedTimer >= 0){
                blockEntity.finishedTimer -= 1
                return
            }
            //return if a trial isn't active
            if (! blockEntity.active) return
            //get the trial data
            val data = Trials.getTrialData(blockEntity.trial)
            //if it's null, something is wrong. Abort the trial, send a log error, return
            if (data == null){
                Viscerae.LOGGER.error("couldn't find trial ${blockEntity.trial}, aborting!")
                blockEntity.active = false
                return
            }
            //refresh block entities player list once a second
            if (blockEntity.trialTimer % 20 == 0){
                val box = Box(blockEntity.pos.add(16.0, 6.0, 16.0), blockEntity.pos.add(-16.0, -6.0, -16.0))
                val plyrLst = world.getNonSpectatingEntities(PlayerEntity::class.java, box)
                var trialsWon = 0
                for (plyr in plyrLst){
                    trialsWon += (plyr as TrialTracking).trialsWon()
                }
                blockEntity.activeTrialsWon = trialsWon
                blockEntity.activePlayers = plyrLst
            }
            //fail the trial if there are no players in range. may want to let the trial set the range so the big temple or small trials can mess with that?
            if (blockEntity.activePlayers.isEmpty()){
                blockEntity.active = false
                blockEntity.finishedTimer = FINISHED
                return
            }
            //request wave data for this tick
            val wave = data.provideNextWave(blockEntity.trialTimer,world,blockEntity.activePlayers)
            //if there is actually was wave data, set up the new list of entities and summon them in
            //may want to have another trial setting that lets you pick to wait for the wave to be defeated or just tacks on entities to the list, resets the bar to full and trucks on
            if (wave.isNotEmpty()){
                blockEntity.activeEntities = summonWave(world,pos,wave)
            }
            var waveDefeated = updateBossBar(blockEntity.activePlayers,blockEntity.activeEntities,blockEntity.bar)

            blockEntity.trialTimer += 1
            if (blockEntity.trialTimer >= data.maxWaveTime()){
                blockEntity.active = false
                blockEntity.finishedTimer = FINISHED
                blockEntity.trialTimer = 0
            }


        }

        fun summonWave(world: World,pos: BlockPos,waveData: List<Identifier>): List<LivingEntity>{

        }

        fun updateBossBar(players: List<PlayerEntity>, mobs: List<LivingEntity>, bar: ServerBossBar?): Boolean{

        }

    }

}
