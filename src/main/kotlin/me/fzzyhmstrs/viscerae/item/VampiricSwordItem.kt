package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial

class VampiricSwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings): SwordItem(material, attackDamage, attackSpeed, settings) {

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        val recentDamage = target.damageTracker.mostRecentDamage
        val recentAmount = recentDamage?.damage?:0f
        val healAmount = recentAmount * VisceraeConfig.items.vampiricSwordHealFract
        attacker.heal(healAmount)
        return super.postHit(stack, target, attacker)
    }

}