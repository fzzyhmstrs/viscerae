package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.registry.RegisterBlock
import net.minecraft.block.AbstractPlantStemBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random

class BloodVinesHeadBlock(settings: Settings): AbstractPlantStemBlock(settings,Direction.DOWN, SHAPE,false,0.1) {

    companion object{
        val SHAPE = createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
    }

    override fun getPlant(): Block {
        return RegisterBlock.BLOOD_VINES
    }

    override fun getGrowthLength(random: Random): Int {
        return 1
    }

    override fun chooseStemState(state: BlockState): Boolean {
        return state.isAir
    }
}