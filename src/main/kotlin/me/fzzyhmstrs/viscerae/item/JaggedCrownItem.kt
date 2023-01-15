package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.mana_util.ManaItem
import me.fzzyhmstrs.amethyst_core.trinket_util.TrinketUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

open class JaggedCrownItem(settings: Settings): AbstractAugmentJewelryItem(settings) {

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        attacker.damage(DamageSource.thorns(target),1.0f)
        return super.postHit(stack, target, attacker)
    }

}