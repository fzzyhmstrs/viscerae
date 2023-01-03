package me.fzzyhmstrs.viscerae.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.MinorSupportAugment
import me.fzzyhmstrs.amethyst_core.trinket_util.EffectQueue
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.registry.RegisterItem
import me.fzzyhmstrs.viscerae.registry.RegisterStatus
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.Items
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class NetheriteSkinAugment(tier: Int, maxLvl: Int, vararg slot: EquipmentSlot): MinorSupportAugment(tier, maxLvl, *slot) {

    override val baseEffect: AugmentEffect
        get() = super.baseEffect
            .withDuration(500,100)
            .withRange(2.9,0.1)
            .withAmplifier(0,1,0)

    override fun supportEffect(
        world: World,
        target: Entity?,
        user: LivingEntity,
        level: Int,
        effects: AugmentEffect
    ): Boolean {
        return if(target != null) {
            if (target is LivingEntity) {
                EffectQueue.addStatusToQueue(target,RegisterStatus.NETHERITE_SKIN,effects.duration(level), effects.amplifier(level)/3)
                world.playSound(null, target.blockPos, soundEvent(), SoundCategory.PLAYERS, 0.8F, world.random.nextFloat() * 0.4f + 0.8f)
                true
            } else {
                false
            }
        } else {
            EffectQueue.addStatusToQueue(user,RegisterStatus.NETHERITE_SKIN,effects.duration(level), effects.amplifier(level)/3)
            world.playSound(null, user.blockPos, soundEvent(), SoundCategory.PLAYERS, 0.8F, world.random.nextFloat() * 0.4f + 0.8f)
            true
        }
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE
    }

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.FURY,800,250,18, imbueLevel, LoreTier.HIGH_TIER, Items.NETHERITE_SCRAP)
    }
}
