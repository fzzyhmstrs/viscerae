package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.effects.BloodBoilStatusEffect
import me.fzzyhmstrs.viscerae.effects.BloodlustStatusEffect
import me.fzzyhmstrs.viscerae.effects.IronSkinStatusEffect
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegisterStatus {

    val BLOOD_BOIL = BloodBoilStatusEffect(StatusEffectCategory.HARMFUL,0x9E4035)
    val BLOODLUST = BloodlustStatusEffect(StatusEffectCategory.BENEFICIAL,0x910814)
        .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,"2gt5f6f5-8ae3-11ed-a1eb-0242ac120002",0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,"12312ab3-8ae3-11ed-a1eb-0242ac120002",0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
    val BLOODTHIRST = BloodlustStatusEffect(StatusEffectCategory.BENEFICIAL,0x910814)
        .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,"a47r1298-8ae3-11ed-a1eb-0242ac120002",0.075,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
    val IRON_SKIN = IronSkinStatusEffect(StatusEffectCategory.BENEFICIAL,0x808080)
        .addAttributeModifier(EntityAttributes.GENERIC_ARMOR,"2b3af1f0-8ae3-11ed-a1eb-0242ac120002",1.0,EntityAttributeModifier.Operation.ADDITION)
    val NETHERITE_SKIN = IronSkinStatusEffect(StatusEffectCategory.BENEFICIAL,0x4D494D)
        .addAttributeModifier(EntityAttributes.GENERIC_ARMOR,"2b3af1f0-8ae3-11ed-a1eb-0242ac120002",2.0,EntityAttributeModifier.Operation.ADDITION)
        .addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,"bc1672b2-8ae3-11ed-a1eb-0242ac120002",0.5,EntityAttributeModifier.Operation.ADDITION)

    fun registerAll(){
        Registry.register(Registry.STATUS_EFFECT, Identifier(Viscerae.MOD_ID,"blood_boil"), BLOOD_BOIL)
        Registry.register(Registry.STATUS_EFFECT, Identifier(Viscerae.MOD_ID,"bloodlust"), BLOODLUST)
        Registry.register(Registry.STATUS_EFFECT, Identifier(Viscerae.MOD_ID,"bloodthirst"), BLOODTHIRST)
        Registry.register(Registry.STATUS_EFFECT, Identifier(Viscerae.MOD_ID,"iron_skin"), IRON_SKIN)
        Registry.register(Registry.STATUS_EFFECT, Identifier(Viscerae.MOD_ID,"netherite_skin"), NETHERITE_SKIN)
    }
}
