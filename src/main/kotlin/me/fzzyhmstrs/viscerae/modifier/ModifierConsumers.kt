package me.fzzyhmstrs.viscerae.modifier

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.trinket_util.EffectQueue
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.registry.RegisterStatus
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

object ModifierConsumers {

    val BLOOD_PACT_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodPactConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.isPlayer){
                it.damage(DamageSource.STARVE,0.33333F)
            }
        }
    }

    val MARROW_PACT_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> marrowPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun marrowPactConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.isPlayer){
                it.damage(DamageSource.STARVE,1.0F)
            }
        }
    }

    val BLOODY_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodyConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodyConsumer(list: List<LivingEntity>){
        if (Viscerae.vRandom.nextFloat() < 0.04) {
            list.forEach {
                if (it.isPlayer) {
                    EffectQueue.addStatusToQueue(it, StatusEffects.REGENERATION,50,1)
                }
            }
        }
    }

    val EMPOWERED_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> empoweredConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun empoweredConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.hasStatusEffect(RegisterStatus.BLOODLUST)){
                val effect = it.getStatusEffect(RegisterStatus.BLOODLUST)
                val amp = effect?.amplifier?:0
                val duration = effect?.duration?:0
                if (duration > 0){
                    val duration2 = if(duration < 100) {100} else {duration}
                    it.addStatusEffect(StatusEffectInstance(StatusEffects.WITHER,duration2,amp + 1))
                }
            } else {
                it.addStatusEffect(
                    StatusEffectInstance(StatusEffects.WITHER, 80)
                )
            }
        }
    }
}
