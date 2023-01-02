package me.fzzyhmstrs.viscerae.tool

import me.fzzyhmstrs.viscerae.registry.RegisterItem
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient




object VampiricToolMaterial: ToolMaterial{
    override fun getDurability(): Int {
        return 350
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 10f
    }
    override fun getAttackDamage(): Float {
        return 2.5f
    }
    override fun getMiningLevel(): Int {
        return MiningLevels.DIAMOND
    }
    override fun getEnchantability(): Int {
        return 15
    }

    override fun getRepairIngredient(): Ingredient? {
        return Ingredient.ofItems(RegisterItem.BLOODSTONE)
    }
}