package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.block.*
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.block.sapling.OakSaplingGenerator
import net.minecraft.entity.EntityType
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.registry.Registry
import net.minecraft.world.BlockView
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.trunk.BendingTrunkPlacer

object RegisterBlock {
    
    internal val regBlock: MutableMap<String, Block> = mutableMapOf()
    
    //Special blocks
    val BLOODSTAINED_ALTAR = BloodstainedAltarBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["bloodstained_altar"] = it }
    val OFFERING_PEDESTAL = OfferingBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["offering_pedestal"] = it }
    val OFFERING_SLAB = OfferingSlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["offering_slab"] = it }
    val STONY_ALTAR = AltarBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["stony_altar"] = it }
    val TRIAL_STONE = TrialBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).requiresTool().strength(5000F,3600000.0f).sounds(BlockSoundGroup.METAL)).also { regBlock["trial_stone"] = it }
    
    //bloodstone
    val BLOODSTONE_BLACKSTONE_ORE = OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE),UniformIntProvider.create(1,2)).also { regBlock["bloodstone_blackstone_ore"] = it }
    val BLOODSTONE_DEEPSLATE_ORE = OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.DEEPSLATE),UniformIntProvider.create(1,2)).also { regBlock["bloodstone_deepslate_ore"] = it }
    val BLOODSTONE_ORE = OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE), UniformIntProvider.create(1,2)).also { regBlock["bloodstone_ore"] = it }
    val BLOODSTONE_BLOCK = Block(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)).also { regBlock["bloodstone_block"] = it }
    val GILDED_BLOODSTONE_BLOCK = Block(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["gilded_bloodstone_block"] = it }
    
    //Bloodwood
    val BLOODWOOD_LEAVES = LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
        .allowsSpawning { _: BlockState, _: BlockView, _: BlockPos, type: EntityType<*> -> canSpawnOnLeaves(type) }
        .suffocates{ _: BlockState, _: BlockView, _: BlockPos -> never() }
        .blockVision { _: BlockState, _: BlockView, _: BlockPos -> never() }).also { regBlock["bloodwood_leaves"] = it }
    val BLOODWOOD_LEAVES_FLOWERING = LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
        .allowsSpawning { _: BlockState, _: BlockView, _: BlockPos, type: EntityType<*> -> canSpawnOnLeaves(type) }
        .suffocates{ _: BlockState, _: BlockView, _: BlockPos -> never() }
        .blockVision { _: BlockState, _: BlockView, _: BlockPos -> never() }).also { regBlock["bloodwood_leaves_flowering"] = it }
    val BLOODWOOD_SAPLING = SaplingBlock(BloodwoodSaplingGenerator(),FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)).also { regBlock["bloodwood_sapling"] = it }
    val BLOODWOOD_BUTTON = WoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)).also { regBlock["bloodwood_button"] = it }
    val BLOODWOOD_DOOR = DoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.DARK_RED).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()).also { regBlock["bloodwood_door"] = it }
    val BLOODWOOD_PLANKS = Block(FabricBlockSettings.of(Material.WOOD, MapColor.DARK_RED).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)).also { regBlock["bloodwood_planks"] = it }
    val BLOODWOOD_FENCE = FenceBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["bloodwood_fence"] = it }
    val BLOODWOOD_FENCE_GATE = FenceBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["bloodwood_fence_gate"] = it }
    val BLOODWOOD_LOG = PillarBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["bloodwood_log"] = it }
    val BLOODWOOD_LOG_STRIPPED = PillarBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["stripped_bloodwood_log"] = it }
    val BLOODWOOD_WOOD = PillarBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["bloodwood_wood"] = it }
    val BLOODWOOD_WOOD_STRIPPED = PillarBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["stripped_bloodwood_wood"] = it }
    val BLOODWOOD_PRESSURE_PLATE = PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,FabricBlockSettings.of(Material.WOOD, MapColor.DARK_RED).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)).also { regBlock["bloodwood_pressure_plate"] = it }
    val BLOODWOOD_SLAB = SlabBlock(FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["bloodwood_slab"] = it }
    val BLOODWOOD_STAIRS = StairsBlock(BLOODWOOD_PLANKS.defaultState,FabricBlockSettings.copyOf(BLOODWOOD_PLANKS)).also { regBlock["bloodwood_stairs"] = it }
    val BLOODWOOD_TRAPDOOR = TrapdoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.DARK_RED).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning { _: BlockState, _: BlockView, _: BlockPos, _: EntityType<*> -> never() }).also { regBlock["bloodwood_trapdoor"] = it }
    
    //temple stone
    val CHISELED_DARK_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["chiseled_dark_temple_stone"] = it }
    val CHISELED_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["chiseled_temple_stone"] = it }
    val DARK_TEMPLE_PILLAR = PillarBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["dark_temple_pillar"] = it }
    val DARK_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["dark_temple_stone"] = it }
    val DARK_TEMPLE_STONE_BRICKS = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["dark_temple_stone_bricks"] = it }
    val DARK_TEMPLE_STONE_BRICK_SLAB = SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["dark_temple_stone_brick_slab"] = it }
    val DARK_TEMPLE_STONE_BRICK_STAIRS = StairsBlock(DARK_TEMPLE_STONE_BRICKS.defaultState,FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["dark_temple_stone_brick_stairs"] = it }
    val DARK_TEMPLE_STONE_BRICK_WALL = WallBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["dark_temple_stone_brick_wall"] = it }
    val DARK_TEMPLE_STONE_SLAB = SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["dark_temple_stone_slab"] = it }
    val DARK_TEMPLE_STONE_STAIRS = StairsBlock(DARK_TEMPLE_STONE.defaultState,FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["dark_temple_stone_stairs"] = it }
    val DARK_TEMPLE_STONE_WALL = WallBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["dark_temple_stone_wall"] = it }
    val NOTCHED_DARK_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["notched_dark_temple_stone"] = it }
    val NOTCHED_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["notched_temple_stone"] = it }
    val POLISHED_DARK_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["polished_dark_temple_stone"] = it }
    val POLISHED_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["polished_temple_stone"] = it }
    val TEMPLE_PILLAR = PillarBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_pillar"] = it }
    val TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone"] = it }
    val TEMPLE_STONE_BRICKS = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_bricks"] = it }
    val TEMPLE_STONE_BRICK_SLAB = SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_brick_slab"] = it }
    val TEMPLE_STONE_BRICK_STAIRS = StairsBlock(TEMPLE_STONE_BRICKS.defaultState,FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_brick_stairs"] = it }
    val TEMPLE_STONE_BRICK_WALL = WallBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_brick_wall"] = it }
    val TEMPLE_STONE_SLAB = SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_slab"] = it }
    val TEMPLE_STONE_STAIRS = StairsBlock(TEMPLE_STONE.defaultState,FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_stairs"] = it }
    val TEMPLE_STONE_WALL = WallBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_wall"] = it }
    
    //biome blocks
    val DEATHCAP_MUSHROOM = DeathcapMushroomBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance { _ -> 1 }
        .postProcess { _, _, _ -> always() }).also { regBlock["deathcap_mushroom"] = it }
    val DEATHCAP_MUSHROOM_BLOCK = MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN)).also { regBlock["deathcap_mushroom_block"] = it }
    val RAZOR_GRASS = FernBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)).also { regBlock["razor_grass"] = it }
    val UMBRAL_GRANITE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["razor_grass"] = it }
    val UMBRAL_MUSHROOM = UmbralMushroomBlock(FabricBlockSettings.of(Material.PLANT).nonOpaque().breakInstantly()).also { regBlock["umbral_mushroom"] = it }
    val UMBRAL_MUSHROOM_BLOCK = MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.DARK_RED)).also { regBlock["umbral_mushroom_block"] = it }

    val BLOODWOOD_TREE = ConfiguredFeatures.register(
        "viscerae:bloodwood_tree",
        Feature.TREE,
        TreeFeatureConfig.Builder(
            BlockStateProvider.of(Blocks.OAK_LOG),
            BendingTrunkPlacer(4, 2, 0, 3, UniformIntProvider.create(1, 2)),
            WeightedBlockStateProvider(
                DataPool.builder<BlockState>().add(BLOODWOOD_LEAVES.defaultState, 5)
                    .add(BLOODWOOD_LEAVES_FLOWERING.defaultState, 1)
            ),
            RandomSpreadFoliagePlacer(
                ConstantIntProvider.create(3),
                ConstantIntProvider.create(0),
                ConstantIntProvider.create(2),
                50
            ),
            TwoLayersFeatureSize(1, 0, 1)
        ).dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT)).forceDirt().build()
    )

    fun registerAll() {

        for (k in regBlock.keys) {
            registerBlock(k,regBlock[k])
        }
    }

    private fun registerBlock(path: String, block:Block?){
        Registry.register(Registry.BLOCK, Identifier(Viscerae.MOD_ID, path), block)
        Registry.register(Registry.ITEM, Identifier(Viscerae.MOD_ID,path), BlockItem(block,FabricItemSettings()))
    }

    private fun always(): Boolean {
        return true
    }
    
    private fun never(): Boolean {
        return false
    }

    private fun canSpawnOnLeaves(type: EntityType<*>): Boolean {
        return type === EntityType.OCELOT || type === EntityType.PARROT
    }
}
