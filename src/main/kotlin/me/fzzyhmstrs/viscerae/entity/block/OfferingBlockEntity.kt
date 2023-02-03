package me.fzzyhmstrs.viscerae.entity.block

import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class OfferingBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(RegisterEntity.BLOODSTAINED_ALTAR_ENTITY,pos,state) {

    
    private val inv: Inventory = SimpleInventory(1)

    fun setHeldStack(stack: ItemStack){
        inv.setStack(0,stack)
    }

    fun getHeldStack(): ItemStack{
        return inv.getStack(0)
    }
    
    fun getInventory(): Inventory{
        return inv
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        inv.setStack(0,ItemStack.fromNbt(nbt.getCompound("stack")))
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        val compound = NbtCompound()
        val stack = inv.getStack(0)
        stack.writeNbt(compound)
        nbt.put("stack", compound)
    }

}
