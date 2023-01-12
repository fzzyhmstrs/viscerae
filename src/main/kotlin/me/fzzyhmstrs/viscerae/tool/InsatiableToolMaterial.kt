package me.fzzyhmstrs.viscerae.tool

import me.fzzyhmstrs.viscerae.registry.RegisterItem
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient




object InsatiableToolMaterial: ToolMaterial{
    override fun getDurability(): Int {
        return 2550
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 10f
    }
    override fun getAttackDamage(): Float {
        return 5.0f
    }
    override fun getMiningLevel(): Int {
        return MiningLevels.NETHERITE
    }
    override fun getEnchantability(): Int {
        return 20
    }

    override fun getRepairIngredient(): Ingredient? {
        return Ingredient.ofItems(RegisterItem.ENSOULED_GEM)
    }
}