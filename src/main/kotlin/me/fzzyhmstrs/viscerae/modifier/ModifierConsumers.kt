package me.fzzyhmstrs.ai_odyssey.modifier

import me.fzzyhmstrs.amethyst_core.item_util.AugmentScepterItem
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.amethyst_core.trinket_util.EffectQueue
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModifierConsumers {

    val BLOOD_PACT_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodPactConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.isPlayer){
                it.damage(DamageSource.GENERIC,1.0F)
            }
        }
    }
}
