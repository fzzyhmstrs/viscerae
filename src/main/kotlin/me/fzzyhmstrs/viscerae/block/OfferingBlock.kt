package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.entity.block.OfferingBlockEntity
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class OfferingBlock(settings: Settings): BlockWithEntity(settings){

    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return OfferingBlockEntity(pos, state)
    }

    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val entity = world.getBlockEntity(pos)
        if (entity == null || entity !is OfferingBlockEntity) return super.onUse(state, world, pos, player, hand, hit)
        val stack = player.getStackInHand(hand)
        if (!stack.isEmpty && entity.getHeldStack().isEmpty){
            val stack2 = stack.copy()
            entity.setHeldStack(stack2)
            stack.count = 0
            world.playSound(null,pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM,SoundCategory.BLOCKS,1.0f,1.0f)
            return ActionResult.SUCCESS
        } else if (stack.isEmpty && !entity.getHeldStack().isEmpty){
            val stack2 = entity.getHeldStack().copy()
            player.setStackInHand(hand,stack2)
            world.playSound(null,pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM,SoundCategory.BLOCKS,1.0f,1.0f)
            return ActionResult.SUCCESS
        }
        return super.onUse(state, world, pos, player, hand, hit)
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
        val entity = world.getBlockEntity(pos)
        if (!(entity == null || entity !is OfferingBlockEntity)){
            if (!entity.getHeldStack().isEmpty){
                val itemEntity = ItemEntity(world,pos.x+0.5,pos.y + 1.0, pos.z + 0.5, entity.getHeldStack().copy())
                itemEntity.setToDefaultPickupDelay()
                world.spawnEntity(itemEntity)
            }
        }
        super.onBreak(world, pos, state, player)
    }

}
