package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.entity.MarrowShardEntity
import me.fzzyhmstrs.viscerae.entity.VampiricBoltEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
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

    fun registerAll(){

    }
}