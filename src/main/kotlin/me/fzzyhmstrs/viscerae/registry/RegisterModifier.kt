package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.ai_odyssey.modifier.ModifierConsumers
import me.fzzyhmstrs.ai_odyssey.modifier.ModifierPredicates
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AugmentModifier> = mutableListOf()

    val BLOOD_PACT = AugmentModifier(Identifier(Viscerae.MOD_ID,"blood_pact"), manaCostModifier = -100.0).withConsumer(
        ModifierConsumers.BLOOD_PACT_CONSUMER) .also { regMod.add(it) }
    val BLOOD_MAGIC = AugmentModifier(Identifier(Viscerae.MOD_ID,"blood_magic"), levelModifier = 1).withDamage(2.0F).withAmplifier(1).withDuration(durationPercent = 10).withSpellToAffect(
        ModifierPredicates.SOUL_PREDICATE) .also { regMod.add(it) }


    fun registerAll(){
        regMod.forEach {
            ModifierRegistry.register(it)
        }
        regMod.clear()
    }

}
