package me.fzzyhmstrs.viscerae.block

import net.minecraft.block.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class OfferingSlabBlock(settings: Settings): OfferingBlock(settings){

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return VOXEL_SHAPE
    }

    companion object{
        private val VOXEL_SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    }

}
