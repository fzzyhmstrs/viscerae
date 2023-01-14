package me.fzzyhmstrs.viscerae.entity.block

import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TrialBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(RegisterEntity.TRIAL_BLOCK_ENTITY,pos,state) {


    companion object{

        fun tick(world: World, pos: BlockPos, state2: BlockState, blockEntity: TrialBlockEntity){

        }

    }

}