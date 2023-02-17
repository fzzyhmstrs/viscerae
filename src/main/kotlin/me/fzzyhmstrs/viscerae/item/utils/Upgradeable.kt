package me.fzzyhmstrs.viscerae.item.utils

import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

interface Upgradeable {
    fun upgrade(stack: ItemStack, world: World, owner: LivingEntity)
    fun canUpgrade(stack: ItemStack, world: World): Boolean
}