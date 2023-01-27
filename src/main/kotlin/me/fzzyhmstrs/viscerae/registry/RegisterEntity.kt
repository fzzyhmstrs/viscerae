package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.entity.BoneCageEntity
import me.fzzyhmstrs.viscerae.entity.GoreLanceEntity
import me.fzzyhmstrs.viscerae.entity.MarrowShardEntity
import me.fzzyhmstrs.viscerae.entity.VampiricBoltEntity
import me.fzzyhmstrs.viscerae.entity.block.BloodstainedAltarEntity
import me.fzzyhmstrs.viscerae.entity.block.OfferingBlockEntity
import me.fzzyhmstrs.viscerae.entity.block.TrialBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

object RegisterEntity {

    val VAMPIRIC_MISSILE_ENTITY: EntityType<VampiricBoltEntity> = Registry.register(
        Registry.ENTITY_TYPE,
        Identifier(Viscerae.MOD_ID, "vampiric_missile_entity"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<VampiricBoltEntity>, world: World ->
            VampiricBoltEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.3125f, 0.3125f)).build()
    )

    val MARROW_SHARD_ENTITY: EntityType<MarrowShardEntity> = Registry.register(
        Registry.ENTITY_TYPE,
        Identifier(Viscerae.MOD_ID, "marrow_shard_entity"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<MarrowShardEntity>, world: World ->
            MarrowShardEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.3125f, 0.3125f)).build()
    )

    val GORE_LANCE_ENTITY: EntityType<GoreLanceEntity> = Registry.register(
        Registry.ENTITY_TYPE,
        Identifier(Viscerae.MOD_ID, "gore_lance_entity"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<GoreLanceEntity>, world: World ->
            GoreLanceEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.3125f, 0.3125f)).build()
    )

    val BONE_CAGE_ENTITY: EntityType<BoneCageEntity> = Registry.register(
        Registry.ENTITY_TYPE,
        Identifier(Viscerae.MOD_ID, "bone_cage_entity"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<BoneCageEntity>, world: World ->
            BoneCageEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.7f, 1.0f)).trackRangeChunks(6).trackedUpdateRate(2).build()
    )

    /////////////////////////////////////

    val TRIAL_BLOCK_ENTITY: BlockEntityType<TrialBlockEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        Viscerae.MOD_ID + ":trial_stone_entity",
        FabricBlockEntityTypeBuilder.create({ pos: BlockPos, state: BlockState ->
            TrialBlockEntity(
                pos,
                state
            )
        },RegisterBlock.TRIAL_STONE).build(null))

    val BLOODSTAINED_ALTAR_ENTITY: BlockEntityType<BloodstainedAltarEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        Viscerae.MOD_ID + ":bloodstained_altar_entity",
        FabricBlockEntityTypeBuilder.create({ pos: BlockPos, state: BlockState ->
            BloodstainedAltarEntity(
                pos,
                state
            )
        },RegisterBlock.BLOODSTAINED_ALTAR).build(null))

    val OFFERING_BLOCK_ENTITY: BlockEntityType<OfferingBlockEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        Viscerae.MOD_ID + ":offering_block_entity",
        FabricBlockEntityTypeBuilder.create({ pos: BlockPos, state: BlockState ->
            OfferingBlockEntity(
                pos,
                state
            )
        },RegisterBlock.OFFERING_PEDESTAL, RegisterBlock.OFFERING_SLAB).build(null))

    fun registerAll(){

    }
}