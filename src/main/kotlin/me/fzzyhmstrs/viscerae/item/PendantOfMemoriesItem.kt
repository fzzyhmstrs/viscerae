package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.mana_util.ManaItem
import me.fzzyhmstrs.amethyst_core.trinket_util.TrinketUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

class PendantOfMemoriesItem(settings: Settings): AbstractAugmentJewelryItem(settings), ManaItem {

    override fun intermittentTick(stack: ItemStack, entity: LivingEntity) {
        if (entity.world.random.nextFloat() < 0.1){
            this.healDamage(1,stack)
        }
        if (entity is PlayerEntity) {
            val stacks: MutableList<ItemStack> = mutableListOf()
            for (stack2 in entity.inventory.main) {
                if (stack2.item is ManaItem && stack2.isDamaged) {
                    stacks.add(stack2)
                }
            } // iterate over the inventory and look for items that are interfaced with "ManaItem"
            for (stack2 in entity.inventory.offHand) {
                if (stack2.item is ManaItem && stack2.isDamaged) {
                    stacks.add(stack2)
                }
            }
            for (stack2 in entity.inventory.armor) {
                if (stack2.item is ManaItem && stack2.isDamaged) {
                    stacks.add(stack2)
                }
            }
            val stacks2 = TrinketUtil.getTrinketStacks(entity)
            stacks2.forEach {
                if (it.item is ManaItem && it.isDamaged) {
                    stacks.add(it)
                }
            }
            if (stacks.isNotEmpty()){
                val i = entity.world.random.nextInt(stacks.size)
                if (stack.damage < stack.maxDamage - 1)
                this.healDamage(1,stacks[i])
                this.manaDamage(stack,entity.world,entity,1)
            }
        }
        super.intermittentTick(stack, entity)
    }

    override fun getRepairTime(): Int {
        return 0
    }

}