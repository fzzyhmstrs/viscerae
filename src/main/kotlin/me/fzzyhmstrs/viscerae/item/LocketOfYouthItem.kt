package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.mana_util.ManaItem
import me.fzzyhmstrs.amethyst_core.trinket_util.TrinketUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

class LocketOfYouthItem(settings: Settings): AbstractAugmentJewelryItem(settings) {

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        val nbt = stack.orCreateNbt
        if (!nbt.contains("kills")){
            nbt.putInt("kills",1)
        } else {
            val kills = nbt.getInt("kills")
            nbt.putInt("kills",kills + 1)
        }
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

}
