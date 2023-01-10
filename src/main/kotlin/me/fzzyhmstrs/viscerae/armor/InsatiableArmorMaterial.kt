package me.fzzyhmstrs.viscerae.armor

import me.fzzyhmstrs.viscerae.registry.RegisterItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

@Suppress("PrivatePropertyName")
class InsatiableArmorMaterial : ArmorMaterial {
    private val BASE_DURABILITY = intArrayOf(13, 15, 16, 11)
    private val PROTECTION_VALUES = intArrayOf(4, 8, 10, 5)


    override fun getName(): String = "viscerae_bloodstone"
    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_IRON
    override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(RegisterItem.BLOODSTONE)
    override fun getEnchantability(): Int = 25
    override fun getProtectionAmount(slot: EquipmentSlot): Int = PROTECTION_VALUES[slot.entitySlotId]
    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * 49
    override fun getKnockbackResistance(): Float = 0.15F
    override fun getToughness(): Float = 3F
}
