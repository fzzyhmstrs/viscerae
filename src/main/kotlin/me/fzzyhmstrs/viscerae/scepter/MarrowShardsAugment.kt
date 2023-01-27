package me.fzzyhmstrs.viscerae.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.SummonProjectileAugment
import me.fzzyhmstrs.viscerae.entity.MarrowShardEntity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.world.World

class MarrowShardsAugment(tier: Int, maxLvl: Int, vararg slot: EquipmentSlot): SummonProjectileAugment(tier, maxLvl, *slot) {

    override val baseEffect: AugmentEffect
        get() = super.baseEffect
            .withDamage(6.8F,0.2F,0.0F)
            .withAmplifier(2,0,0)
            .withRange(3.9,0.1,0.0)

    override fun applyTasks(
        world: World,
        user: LivingEntity,
        hand: Hand,
        level: Int,
        effects: AugmentEffect
    ): Boolean {
        val speed = 2.0F
        val div = 0.35F
        val shardCount = (level / 6) + 1
        val startingAngle = 10 * (shardCount - 1)
        var bl = true
        for (i in 0 until shardCount){
            val angle = startingAngle - (10 * i)
            val yaw = user.yaw + angle
            val shard = MarrowShardEntity.createMarrowShard(world, user, speed, div, yaw, effects, level)
            bl = bl && spawnProjectileEntity(world,user,shard,soundEvent())
        }
        return bl
    }

    private fun spawnProjectileEntity(world: World, entity: LivingEntity, projectile: ProjectileEntity, soundEvent: SoundEvent): Boolean{
        val bl = world.spawnEntity(projectile)
        if(bl) {
            world.playSound(
                null,
                entity.blockPos,
                soundEvent,
                SoundCategory.PLAYERS,
                1.0f,
                world.getRandom().nextFloat() * 0.4f + 0.8f
            )
        }
        return bl
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.BLOCK_BONE_BLOCK_BREAK
    }

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.FURY,26,25,1,imbueLevel, LoreTier.LOW_TIER, Items.BONE_BLOCK)
    }

}