package me.fzzyhmstrs.viscerae.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.MinorSupportAugment
import me.fzzyhmstrs.fzzy_core.trinket_util.EffectQueue
import me.fzzyhmstrs.viscerae.registry.RegisterStatus
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.world.World

class EnlivenAugment(tier: Int, maxLvl: Int, vararg slot: EquipmentSlot): MinorSupportAugment(tier, maxLvl, *slot){

    override val baseEffect: AugmentEffect
        get() = super.baseEffect
            .withDamage(5.75F,0.25F,0.0F)
            .withDuration(550,50,0)
            .withAmplifier(2,0,0)

    override fun supportEffect(
        world: World,
        target: Entity?,
        user: LivingEntity,
        level: Int,
        effects: AugmentEffect
    ): Boolean {
        user.damage(DamageSource.STARVE,effects.damage(level))
        world.playSound(null, user.blockPos, soundEvent(), SoundCategory.PLAYERS, 1.0F, 1.0F)
        applyAmplifyingStatus(user,StatusEffects.STRENGTH,effects,level,0)
        applyAmplifyingStatus(user,RegisterStatus.BLOODLUST,effects,level,-1)
        applyAmplifyingStatus(user,StatusEffects.JUMP_BOOST,effects,level,0)
        effects.accept(user,AugmentConsumer.Type.BENEFICIAL)
        return true
    }

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.GRACE,1200,80,15,imbueLevel,LoreTier.HIGH_TIER, Items.GLISTERING_MELON_SLICE)
    }
    
    private fun applyAmplifyingStatus(user: LivingEntity,status: StatusEffect, effects: AugmentEffect,level: Int, ampMod: Int){
        if (user.hasStatusEffect(RegisterStatus.BLOODLUST)){
            val effect = user.getStatusEffect(status)
            val amp = effect?.amplifier?:0
            val duration = effect?.duration?:0
            if (duration > 0){
                val amp2 = if (amp < effects.amplifier(level) + ampMod)  {effects.amplifier(level) + ampMod} else {amp + 1}
                val duration2 = if(duration < effects.duration(level)) {effects.duration(level)} else {duration}
                EffectQueue.addStatusToQueue(user,status, duration2, amp2)
            }
        } else {
            EffectQueue.addStatusToQueue(user,status, effects.duration(level), effects.amplifier(level) + ampMod)
        }
    }
}
