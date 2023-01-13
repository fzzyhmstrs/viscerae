package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TrialBlock(settings: Settings): BlockWithEntity(settings){

    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }
    
     override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return TODO()
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
