package me.fzzyhmstrs.viscerae.entity.block

import me.fzzyhmstrs.fzzy_core.registry.EventRegistry
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BloodstainedAltarEntity(pos: BlockPos, state: BlockState): BlockEntity(RegisterEntity.BLOODSTAINED_ALTAR_ENTITY,pos,state) {
    
    val ownerUuid: UUID? = null
    
    override fun writeNbt(nbt: NbtCompound) {}
    
    override fun readNbt(nbt: NbtCompound) {}
    
    companion object{

        fun tick(world: World, pos: BlockPos, state2: BlockState, blockEntity: TrialBlockEntity){
            if (!EventRegistry.ticker_30.isReady()) return
            if (blockEntity.ownerUuid == null) return
        }
    }

}
