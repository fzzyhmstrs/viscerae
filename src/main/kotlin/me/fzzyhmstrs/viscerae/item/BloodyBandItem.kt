package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import me.fzzyhmstrs.viscerae.modifier.ModifierConsumers
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld

class BloodyBandItem(settings: Settings): AbstractAugmentJewelryItem(settings), KillTracking {

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        ModifierConsumers.BLOODY_CONSUMER.consumer.accept(listOf(wearer))
    }

}