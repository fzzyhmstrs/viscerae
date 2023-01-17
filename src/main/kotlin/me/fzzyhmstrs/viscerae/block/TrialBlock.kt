package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.entity.block.TrialBlockEntity
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TrialBlock(settings: Settings): BlockWithEntity(settings){

    companion object{
        private val ACTIVE: BooleanProperty = BooleanProperty.of("active")
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(ACTIVE)
    }

    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }
    
     override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return TrialBlockEntity(pos, state)
    }

    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        return super.onUse(state, world, pos, player, hand, hit)
    }
     
     override fun <T : BlockEntity> getTicker(
         world: World,
         state: BlockState,
         type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if(!world.isClient) checkType(
            type, RegisterEntity.TRIAL_BLOCK_ENTITY
        ) { world2: World, pos: BlockPos, state2: BlockState, blockEntity: TrialBlockEntity ->
            TrialBlockEntity.tick(
                world2,
                pos,
                state2,
                blockEntity
            )
        } else null
    }


}
