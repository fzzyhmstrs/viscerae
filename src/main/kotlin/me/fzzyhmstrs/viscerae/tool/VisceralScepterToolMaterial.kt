package me.fzzyhmstrs.viscerae.tool

import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import me.fzzyhmstrs.viscerae.registry.RegisterItem
import me.fzzyhmstrs.viscerae.registry.RegisterStatus
import net.minecraft.item.Item
import net.minecraft.recipe.Ingredient
import kotlin.math.max


object VisceralScepterToolMaterial: ScepterToolMaterial() {
    override fun getDurability(): Int {
        return VisceraeConfig.items.tier2ScepterDurability
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 1.0f
    }
    override fun getAttackDamage(): Float {
        return 4.0f
    }
    override fun getAttackSpeed(): Double {
        return -3.0
    }
    override fun getMiningLevel(): Int {
        return 1
    }
    override fun getEnchantability(): Int {
        return 35
    }
    override fun getRepairIngredient(): Ingredient {
        return Ingredient.ofItems(RegisterItem.VISCERA)
    }
    override fun healCooldown(): Long {
        return max(VisceraeConfig.items.baseRegenRateTicks - 25L,minCooldown())
    }
    override fun scepterTier(): Int{
        return 2
    }
}
