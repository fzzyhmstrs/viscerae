package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.interfaces.KillTracking
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.server.world.ServerWorld

class InsatiableHungerItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings): SwordItem(material, attackDamage, attackSpeed, settings), KillTracking {

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        val recentDamage = target.damageTracker.mostRecentDamage
        val recentAmount = recentDamage?.damage?:0f
        val healAmount = recentAmount * VisceraeConfig.items.vampiricSwordHealFraction
        attacker.heal(healAmount)

        return super.postHit(stack, target, attacker)
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        val nbt = stack.orCreateNbt
        if (!nbt.contains("kills")){
            nbt.putInt("kills",1)
        } else {
            val kills = nbt.getInt("kills")
            nbt.putInt("kills",kills + 1)
        }
        if (!nbt.contains("charges")){
            nbt.putInt("charges",1)
        } else {
            val kills = nbt.getInt("charges")
            nbt.putInt("charges",kills + 1)
        }
    }

}