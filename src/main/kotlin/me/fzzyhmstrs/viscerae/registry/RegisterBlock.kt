package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.block.*
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegisterBlock {
    
    internal val regBlock: MutableMap<String, Block> = mutableMapOf()
    
    //Special blocks
    val BLOODSTAINED_ALTAR = BloodstainedAltarBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["bloodstained_altar"] = it }
    val OFFERING_PEDESTAL = OfferingBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["offering_pedestal"] = it }
    val OFFERING_SLAB = OfferingSlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["offering_slab"] = it }
    val STONY_ALTAR = AltarBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["stony_altar"] = it }
    val TRIAL_STONE = TrialBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).requiresTool().strength(5000F,3600000.0f).sounds(BlockSoundGroup.METAL)).also { regBlock["trial_stone"] = it }
    
    //bloodstone
    val BLOODSTONE_BLACKSTONE_ORE = OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE),UniformIntProvider(1,2)).also { regBlock["bloodstone_blackstone_ore"] = it }
    val BLOODSTONE_DEEPSLATE_ORE = OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.DEEPSLATE),UniformIntProvider(1,2)).also { regBlock["bloodstone_deepslate_ore"] = it }
    val BLOODSTONE_ORE = OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(1.5f, 6.0f).sounds(BlockSoundGroup.STONE),UniformIntProvider(1,2)).also { regBlock["bloodstone_ore"] = it }
    val BLOODSTONE_BLOCK = Block(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)).also { regBlock["bloodstone_block"] = it }
    val GILDED_BLOODSTONE_BLOCK = Block(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.GILDED_BLACKSTONE)).also { regBlock["gilded_bloodstone_block"] = it }
    
    //Bloodwood
    val BLOODWOOD_LEAVES = LeavesBlock() //todo
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
    val TEMPLE_STONE_STAIRS = StairsBlock(TEMPLE_STONE_BRICKS.defaultState,FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_stairs"] = it }
    val TEMPLE_STONE_WALL = WallBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["temple_stone_wall"] = it }
    
    fun registerAll() {
        for (k in regBlock.keys) {
            registerBlock(k,regBlock[k])
        }
    }

    private fun registerBlock(path: String, block:Block?){
        Registry.register(Registry.BLOCK, Identifier(Viscerae.MOD_ID, path), block)
        Registry.register(Registry.ITEM, Identifier(Viscerae.MOD_ID,path), BlockItem(block,FabricItemSettings()))
    }
    
    private fun never(): Boolean {
        return false
    }
    
}
