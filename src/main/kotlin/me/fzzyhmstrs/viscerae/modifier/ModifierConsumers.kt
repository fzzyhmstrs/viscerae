package me.fzzyhmstrs.ai_odyssey.modifier

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.trinket_util.EffectQueue
import me.fzzyhmstrs.viscerae.Viscerae
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffects

object ModifierConsumers {

    val BLOOD_PACT_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodPactConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.isPlayer){
                it.damage(DamageSource.GENERIC,1.0F)
            }
        }
    }

    val BLOODY_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodyConsumer(list: List<LivingEntity>){
        if (Viscerae.vRandom.nextFloat() < 0.04) {
            list.forEach {
                if (it.isPlayer) {
                    EffectQueue.addStatusToQueue(it, StatusEffects.REGENERATION,50,1)
                }
            }
        }
    }
}
