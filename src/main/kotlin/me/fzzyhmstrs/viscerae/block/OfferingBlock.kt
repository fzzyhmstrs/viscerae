package me.fzzyhmstrs.viscerae.block

import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

open class OfferingBlock(settings: Settings): BlockWithEntity(settings){

    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return TODO()
    }

}
