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
    val TRIAL_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5000F,3600000.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["trial_stone"] = it }
    
    //temple stone
    val CHISELED_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["chiseled_temple_stone"] = it }
    val NOTCHED_TEMPLE_STONE = Block(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.STONE)).also { regBlock["notched_temple_stone"] = it }
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
    
}
