package me.fzzyhmstrs.viscerae.entity.block

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.interfaces.TrialTracking
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import me.fzzyhmstrs.viscerae.registry.RegisterTag
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
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
    private var trialTimer = 0
    private var finishedTimer = -1
    private var offeredStack = ItemStack.EMPTY
    private var trial = FALLBACK


    fun activate(player: PlayerEntity,hand: Hand,world: World){
        val stack = player.getStackInHand(hand)
        if (offeredStack != ItemStack.EMPTY || active || finishedTimer > 0){
            return
        }
        if (!(stack.isIn(RegisterTag.BASIC_TRIAL_KEYS) || stack.isIn(RegisterTag.ADVANCED_TRIAL_KEYS))) {
            return
        } else {
            offeredStack = stack.copy()
            player.setStackInHand(hand, ItemStack.EMPTY)
        }
        if (world.registryKey == World.NETHER){
            if (stack.isIn(RegisterTag.ADVANCED_TRIAL_KEYS)){
                val testId = Identifier(trial.namespace,"nether_advanced_${trial.path}")
                if (Trials.getTrialData(testId) != null){
                    trial = testId
                } else {
                    val testId2 = Identifier(trial.namespace,"advanced_${trial.path}")
                    if (Trials.getTrialData(testId2) != null){
                        trial = testId
                    } else {
                        val testId3 = Identifier(trial.namespace,"nether_${trial.path}")
                        if (Trials.getTrialData(testId3) != null){
                            trial = testId
                        }
                    }
                }
            }

        } else {
            if (stack.isIn(RegisterTag.ADVANCED_TRIAL_KEYS)){
                val testId = Identifier(trial.namespace,"advanced_${trial.path}")
                if (Trials.getTrialData(testId) != null){
                    trial = testId
                }
            }
        }
        val box = Box(pos.add(16.0,6.0,16.0),pos.add(-16.0,-6.0,-16.0))
        val players = world.getNonSpectatingEntities(PlayerEntity::class.java,box)
        var trialsWon = 0
        for (plyr in players){
            trialsWon += (plyr as TrialTracking).trialsWon()
        }
        activeTrialsWon = trialsWon
        active = true

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
        private val bar = ServerBossBar(Text.translatable("event.minecraft.raid"), BossBar.Color.RED, BossBar.Style.NOTCHED_10)

        fun tick(world: World, pos: BlockPos, state2: BlockState, blockEntity: TrialBlockEntity){
            if (blockEntity.finishedTimer >= 0){
                blockEntity.finishedTimer -= 1
            }
            if (blockEntity.active){
                val data = Trials.getTrialData(blockEntity.trial)
                if (data != null){
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
                    val playerList= blockEntity.activePlayers.ifEmpty {
                        val box = Box(blockEntity.pos.add(16.0, 6.0, 16.0), blockEntity.pos.add(-16.0, -6.0, -16.0))
                        val plyrLst = world.getNonSpectatingEntities(PlayerEntity::class.java, box)
                        var trialsWon = 0
                        for (plyr in plyrLst){
                            trialsWon += (plyr as TrialTracking).trialsWon()
                        }
                        blockEntity.activeTrialsWon = trialsWon
                        blockEntity.activePlayers = plyrLst
                        plyrLst
                    }
                    val wave = data.provideNextWave(blockEntity.trialTimer,world,playerList)
                    if (wave.isNotEmpty()){
                        summonWave(world,pos,wave)
                    }
                    blockEntity.trialTimer += 1
                    if (blockEntity.trialTimer >= data.maxWaveTime()){
                        blockEntity.active = false
                        blockEntity.finishedTimer = FINISHED
                        blockEntity.trialTimer = 0
                    }
                }
            }
        }

        fun summonWave(world: World,pos: BlockPos,waveData: List<Identifier>){

        }

    }

}