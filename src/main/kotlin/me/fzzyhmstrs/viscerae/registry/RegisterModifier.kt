package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.WeaponModifier
import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.modifier.ModifierConsumers
import me.fzzyhmstrs.viscerae.modifier.ModifierPredicates
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AugmentModifier> = mutableListOf()

    //weapon modifiers
    val BLOOD_SLASH = WeaponModifier(Identifier(Viscerae.MOD_ID,"blood_slash"))
    val BLOODTHIRSTY = WeaponModifier(Identifier(Viscerae.MOD_ID,"bloodthirsty"))
    val DEADBLOW = WeaponModifier(Identifier(Viscerae.MOD_ID,"deadblow"))
    val FRENZIED = WeaponModifier(Identifier(Viscerae.MOD_ID,"frenzied"))
    val GLUTTONOUS = WeaponModifier(Identifier(Viscerae.MOD_ID,"gluttonous"))
    val INNER_FIRE = WeaponModifier(Identifier(Viscerae.MOD_ID,"inner_fire"))
    val SOUL_BOMB = WeaponModifier(Identifier(Viscerae.MOD_ID,"soul_bomb"))
    val SOUL_CHAINS = WeaponModifier(Identifier(Viscerae.MOD_ID,"soul_chains"))
    val VAMPIRIC = WeaponModifier(Identifier(Viscerae.MOD_ID,"vampiric"))
    val VITAL = WeaponModifier(Identifier(Viscerae.MOD_ID,"vital"))

    //scepter modifiers
    val GORY = AugmentModifier(Identifier(Viscerae.MOD_ID,"gory"), cooldownModifier = -10.0).withConsumer(
        ModifierConsumers.BLOODY_CONSUMER) .also { regMod.add(it) }
    val BLOODY = AugmentModifier(Identifier(Viscerae.MOD_ID,"bloody"), cooldownModifier = -5.0).withConsumer(
        ModifierConsumers.BLOODY_CONSUMER).withDescendant(GORY) .also { regMod.add(it) }
    val MARROW_PACT = AugmentModifier(Identifier(Viscerae.MOD_ID,"marrow_pact"), manaCostModifier = -100.0).withConsumer(
        ModifierConsumers.MARROW_PACT_CONSUMER) .also { regMod.add(it) }
    val MARROW_MAGIC = AugmentModifier(Identifier(Viscerae.MOD_ID,"marrow_magic"), levelModifier = 2).withDamage(2.0F)
        .withDuration(durationPercent = 25).also { regMod.add(it) }
    val BLOOD_PACT = AugmentModifier(Identifier(Viscerae.MOD_ID,"blood_pact"), manaCostModifier = -50.0).withConsumer(
        ModifierConsumers.BLOOD_PACT_CONSUMER).withDescendant(MARROW_PACT) .also { regMod.add(it) }
    val BLOOD_MAGIC = AugmentModifier(Identifier(Viscerae.MOD_ID,"blood_magic"), levelModifier = 1).withDamage(1.0F)
        .withDuration(durationPercent = 10).withDescendant(MARROW_MAGIC) .also { regMod.add(it) }
    val VISCERAL = AugmentModifier(Identifier(Viscerae.MOD_ID,"visceral")).withDamage(0f,0f,5f).withConsumer(
        ModifierConsumers.EMPOWERED_CONSUMER).withSpellToAffect(ModifierPredicates.EMPOWERED_PREDICATE) .also { regMod.add(it) }
    val ENSOULED = AugmentModifier(Identifier(Viscerae.MOD_ID,"ensouled"), cooldownModifier = -5.0)
        .withSpellToAffect(ModifierPredicates.SOUL_PREDICATE) .also { regMod.add(it) }
    val SPIRITED = AugmentModifier(Identifier(Viscerae.MOD_ID,"spirited"), cooldownModifier = -5.0)
        .withSpellToAffect(ModifierPredicates.SOUL_PREDICATE).withDescendant(ENSOULED) .also { regMod.add(it) }

    fun registerAll(){
        regMod.forEach {
            ModifierRegistry.register(it)
        }
        regMod.clear()
    }

}
