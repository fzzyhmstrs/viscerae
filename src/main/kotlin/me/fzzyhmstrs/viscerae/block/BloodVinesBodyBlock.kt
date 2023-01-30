package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.registry.RegisterBlock
import net.minecraft.block.AbstractPlantBlock
import net.minecraft.block.AbstractPlantStemBlock
import net.minecraft.block.Fertilizable
import net.minecraft.util.math.Direction

class BloodVinesBodyBlock(settings: Settings): AbstractPlantBlock(settings,Direction.DOWN, SHAPE,false), Fertilizable {

    companion object{
        val SHAPE = createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
    }

    override fun getStem(): AbstractPlantStemBlock {
        return RegisterBlock.BLOOD_VINES_PLANT
    }
}