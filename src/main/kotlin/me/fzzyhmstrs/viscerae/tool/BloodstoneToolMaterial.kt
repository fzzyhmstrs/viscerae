package me.fzzyhmstrs.viscerae.tool

import me.fzzyhmstrs.viscerae.registry.RegisterItem
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient




object BloodstoneToolMaterial: ToolMaterial{
    override fun getDurability(): Int {
        return 200
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 7f
    }
    override fun getAttackDamage(): Float {
        return 2.0f
    }
    override fun getMiningLevel(): Int {
        return MiningLevels.IRON
    }
    override fun getEnchantability(): Int {
        return 5
    }

    override fun getRepairIngredient(): Ingredient? {
        return Ingredient.ofItems(RegisterItem.BLOODSTONE)
    }
}