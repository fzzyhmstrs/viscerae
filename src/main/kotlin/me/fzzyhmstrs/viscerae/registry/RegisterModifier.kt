package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.modifier.ModifierConsumers
import me.fzzyhmstrs.viscerae.modifier.ModifierPredicates
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AbstractModifier<*>> = mutableListOf()

    //trinket modifiers
    val WHISPER_OF_REGRET_SCEPTER = AugmentModifier(Identifier(Viscerae.MOD_ID,"whisper_of_regret_scepter"),1)
        .withDamage(0.0f,0.0f,25f)
        .withAmplifier(2)
        .also { regMod.add(it) }
    val WHISPER_OF_REGRET = EquipmentModifier(Identifier(Viscerae.MOD_ID,"whisper_of_regret"), persistent = true, randomSelectable = false)
        .withModifiers(WHISPER_OF_REGRET_SCEPTER.modifierId)
        .also { regMod.add(it) }


    //weapon modifiers
    val BLOOD_SLASH = EquipmentModifier(Identifier(Viscerae.MOD_ID,"blood_slash"), persistent = true, randomSelectable = false)
        .withOnUse(ModifierConsumers.BLOOD_SLASH_USE_CONSUMER)
        .withPostHit(ModifierConsumers.BLOOD_SLASH_HIT_CONSUMER).also { regMod.add(it) }
    val BLOODTHIRSTY = EquipmentModifier(Identifier(Viscerae.MOD_ID,"bloodthirsty"), persistent = true, randomSelectable = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,"083dcbc6-908f-11ed-a1eb-0242ac120002",1.0,EntityAttributeModifier.Operation.ADDITION)
        .withOnUse(ModifierConsumers.BLOODTHIRSTY_USE_CONSUMER)
        .withPostHit(ModifierConsumers.BLOODTHIRSTY_HIT_CONSUMER).also { regMod.add(it) }
    val DEADBLOW = EquipmentModifier(Identifier(Viscerae.MOD_ID,"deadblow"), persistent = true, randomSelectable = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,"gr59yl13-908f-11ed-a1eb-0242ac120002",0.5,EntityAttributeModifier.Operation.ADDITION)
        .withOnUse(ModifierConsumers.DEADBLOW_USE_CONSUMER).also { regMod.add(it) }
    val FRENZIED = EquipmentModifier(Identifier(Viscerae.MOD_ID,"frenzied"), persistent = true, randomSelectable = false)
        .withPostHit(ModifierConsumers.FRENZIED_HIT_CONSUMER).also { regMod.add(it) }
    val GLUTTONOUS = EquipmentModifier(Identifier(Viscerae.MOD_ID,"gluttonous"), persistent = true, randomSelectable = false)
        .withTick(ModifierConsumers.GLUTTONOUS_TICK_CONSUMER).also { regMod.add(it) }
    val INNER_FIRE = EquipmentModifier(Identifier(Viscerae.MOD_ID,"inner_fire"), persistent = true, randomSelectable = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,"14ae83tp-908f-11ed-a1eb-0242ac120002",1.0,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .withPostHit(ModifierConsumers.INNER_FIRE_HIT_CONSUMER).also { regMod.add(it) }
    val SOUL_BOMB = EquipmentModifier(Identifier(Viscerae.MOD_ID,"soul_bomb"), persistent = true, randomSelectable = false)
        .withPostHit(ModifierConsumers.SOUL_BOMB_HIT_CONSUMER).also { regMod.add(it) }
    val SOUL_CHAINS = EquipmentModifier(Identifier(Viscerae.MOD_ID,"soul_chains"), persistent = true, randomSelectable = false)
        .withOnUse(ModifierConsumers.SOUL_CHAINS_USE_CONSUMER).also { regMod.add(it) }
    val VAMPIRIC = EquipmentModifier(Identifier(Viscerae.MOD_ID,"vampiric"), persistent = true, randomSelectable = false)
        .withPostHit(ModifierConsumers.VAMPIRIC_HIT_CONSUMER).also { regMod.add(it) }
    val VITAL = EquipmentModifier(Identifier(Viscerae.MOD_ID,"vital"), persistent = true, randomSelectable = false)
        .withPostHit(ModifierConsumers.VITAL_HIT_CONSUMER).also { regMod.add(it) }

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
