package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.entity.block.BloodstainedAltarEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

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
        return BloodstainedAltarEntity(pos, state)
    }
    
    companion object{
        private val VOXEL_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0)
    }

}
