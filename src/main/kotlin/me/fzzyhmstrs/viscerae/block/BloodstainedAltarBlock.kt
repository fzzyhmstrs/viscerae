package me.fzzyhmstrs.viscerae.block

import net.minecraft.block.*

class BloodstainedAltarBlock(settings: Settings): BlockWithEntity(settings){

  @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return VOXEL_SHAPE
    }
  
    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }
    
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return TODO()
    }
    
    companion object{
        private val VOXEL_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 15.0, 12.0, 15.0)
    }

}
