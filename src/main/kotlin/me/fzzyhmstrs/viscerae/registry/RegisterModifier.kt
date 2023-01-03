package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.ai_odyssey.modifier.ModifierConsumers
import me.fzzyhmstrs.ai_odyssey.modifier.ModifierPredicates
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AugmentModifier> = mutableListOf()

    val GORY = AugmentModifier(Identifier(Viscerae.MOD_ID,"gory"), cooldownModifier = -10.0).withConsumer(
        ModifierConsumers.BLOODY_CONSUMER) .also { regMod.add(it) }
    val BLOODY = AugmentModifier(Identifier(Viscerae.MOD_ID,"bloody"), cooldownModifier = -5.0).withConsumer(
        ModifierConsumers.BLOODY_CONSUMER).withDescendant(GORY) .also { regMod.add(it) }
    val BLOOD_PACT = AugmentModifier(Identifier(Viscerae.MOD_ID,"blood_pact"), manaCostModifier = -50.0).withConsumer(
        ModifierConsumers.BLOOD_PACT_CONSUMER) .also { regMod.add(it) }
    val BLOOD_MAGIC = AugmentModifier(Identifier(Viscerae.MOD_ID,"blood_magic"), levelModifier = 1).withDamage(1.0F).withDuration(durationPercent = 10).withSpellToAffect(
        ModifierPredicates.SOUL_PREDICATE) .also { regMod.add(it) }
    val MARROW_PACT = AugmentModifier(Identifier(Viscerae.MOD_ID,"marrow_pact"), manaCostModifier = -100.0).withConsumer(
        ModifierConsumers.MARROW_PACT_CONSUMER) .also { regMod.add(it) }
    val MARROW_MAGIC = AugmentModifier(Identifier(Viscerae.MOD_ID,"marrow_magic"), levelModifier = 2).withDamage(2.0F).withDuration(durationPercent = 25).withSpellToAffect(
        ModifierPredicates.SOUL_PREDICATE) .also { regMod.add(it) }
    val EMPOWERED = AugmentModifier(Identifier(Viscerae.MOD_ID,"empowered")).withDamage(0f,0f,5f).withConsumer(
        ModifierConsumers.EMPOWERED_CONSUMER).withSpellToAffect(ModifierPredicates.EMPOWERED_PREDICATE) .also { regMod.add(it) }


    fun registerAll(){
        regMod.forEach {
            ModifierRegistry.register(it)
        }
        regMod.clear()
    }

}
