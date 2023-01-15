package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.mana_util.ManaItem
import me.fzzyhmstrs.amethyst_core.trinket_util.TrinketUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

open class JaggedCrownItem(settings: Settings): AbstractAugmentJewelryItem(settings) {

    override fun onWearerDamaged(
        stack: ItemStack,
        wearer: LivingEntity,
        attacker: LivingEntity?,
        source: DamageSource,
        amount: Float
    ) {
        attacker?.damage(DamageSource.thorns(wearer),2.0f)
        super.onWearerDamaged(stack, wearer, attacker, source, amount)
    }

}