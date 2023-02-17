package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.gear_core.interfaces.DamageTracking
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack

open class JaggedCrownItem(settings: Settings): AbstractAugmentJewelryItem(settings), DamageTracking {

    override fun onWearerDamaged(
        stack: ItemStack,
        wearer: LivingEntity,
        attacker: LivingEntity?,
        source: DamageSource,
        amount: Float
    ): Float {
        attacker?.damage(DamageSource.thorns(wearer),2.0f)
        return super.onWearerDamaged(stack, wearer, attacker, source, amount)
    }

}