package me.fzzyhmstrs.viscerae.item

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.server.world.ServerWorld

class InsatiableHungerItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings): SwordItem(material, attackDamage, attackSpeed, settings) {

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        val recentDamage = target.damageTracker.mostRecentDamage
        val recentAmount = recentDamage?.damage?:0f
        val healAmount = recentAmount * VisceraeConfig.items.vampiricSwordHealFraction
        attacker.heal(healAmount)

        return super.postHit(stack, target, attacker)
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        super.onWearerKilledOther(stack, wearer, victim, world)
        incrementKillCount(stack)
        val nbt = stack.orCreateNbt
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier> {
        val attributeModifiers = HashMultimap.create(super.getAttributeModifiers(slot))

        return attributeModifiers
    }

}