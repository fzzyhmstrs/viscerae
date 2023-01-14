package me.fzzyhmstrs.viscerae.entity.block

import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class OfferingBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(RegisterEntity.BLOODSTAINED_ALTAR_ENTITY,pos,state) {

    private var stack: ItemStack = ItemStack.EMPTY

    fun setHeldStack(stack: ItemStack){
        this.stack = stack
    }

    fun getHeldStack(): ItemStack{
        return stack
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        val stack = ItemStack.fromNbt(nbt.getCompound("stack"))
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        val compound = NbtCompound()
        stack.writeNbt(compound)
        nbt.put("stack", compound)
    }

}