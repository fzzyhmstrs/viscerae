package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.DefaultScepterItem
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.world.World

open class CustomScepterItem(material: ScepterToolMaterial, settings: Settings): DefaultScepterItem(material, settings) {
    override val fallbackId: Identifier = Identifier("amethyst_imbuement","magic_missile")

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
        if (entity !is PlayerEntity) return
        if (world.isClient && selected){
            val rnd = world.random.nextInt(particleChance())
            if (rnd < 1){
                emitParticles(world, MinecraftClient.getInstance(), entity)
            }
        }
    }

}