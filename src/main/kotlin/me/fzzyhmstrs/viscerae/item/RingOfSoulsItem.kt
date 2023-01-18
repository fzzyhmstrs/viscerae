package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.mana_util.ManaItem
import me.fzzyhmstrs.amethyst_core.trinket_util.TrinketUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld

class RingOfSoulsItem(settings: Settings): AbstractAugmentJewelryItem(settings) {

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        incrementKillCount(stack)
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

}