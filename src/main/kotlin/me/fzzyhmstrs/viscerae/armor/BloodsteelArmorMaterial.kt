package me.fzzyhmstrs.viscerae.armor

import me.fzzyhmstrs.viscerae.registry.RegisterItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
object BloodsteelArmorMaterial : ArmorMaterial {
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val PROTECTION_VALUES = intArrayOf(3, 5, 7, 3)


    override fun getName(): String = "viscerae_bloodsteel"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_IRON
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.BLOODSTEEL_INGOT)
    override fun getEnchantability(): Int = 15
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 25
    override fun getKnockbackResistance(): Float = 0.0F
    override fun getToughness(): Float = 1.25F
}
