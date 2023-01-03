package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.item.CustomScepterItem
import me.fzzyhmstrs.viscerae.item.PendantOfMemoriesItem
import me.fzzyhmstrs.viscerae.item.SpiteOfTheBloodWitchScepterItem
import me.fzzyhmstrs.viscerae.item.VampiricSwordItem
import me.fzzyhmstrs.viscerae.tool.BloodWitchToolMaterial
import me.fzzyhmstrs.viscerae.tool.BloodstoneToolMaterial
import me.fzzyhmstrs.viscerae.tool.VampiricToolMaterial
import me.fzzyhmstrs.viscerae.tool.VisceraeScepterToolMaterial
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.SwordItem
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry

object RegisterItem {

    private val regItem: MutableMap<String, Item> = mutableMapOf()

    //basic materials
    val BLOODSTONE = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.COMMON)).also{ regItem["bloodstone"] = it}
    val BLOODY_GEM = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.COMMON)).also{ regItem["bloody_gem"] = it}
    val CHICKEN_BONE = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)).also{ regItem["chicken_bone"] = it}
    val CORRUPTED_HEART = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)).also{ regItem["corrupted_heart"] = it}
    val VISCERA = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.COMMON)).also{ regItem["viscera"] = it}

    //swords and tools
    val BLOODSTONE_SWORD = SwordItem(BloodstoneToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_sword"] = it}
    val VAMPIRIC_BLADE = VampiricSwordItem(VampiricToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["vampiric_blade"] = it}
    val INSATIABLE_HUNGER = SwordItem(BloodstoneToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_sword"] = it}

    //trinkets
    val PENDANT_OF_MEMORIES = PendantOfMemoriesItem(FabricItemSettings().maxDamage(250).group(ItemGroup.MISC)).also{ regItem["pendant_of_memories"] = it}
    
    //altar offerings
    val RUNE_OF_UNDEATH = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC)).also{ regItem["rune_of_undeath"] = it}
    val TALISMAN_OF_BONES = Item(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["talisman_of_bones"] = it}

    val BLOODY_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(RegisterModifier.BLOODY))
        .also{ regItem["bloody_scepter"] = it}
        
    val GRASPING_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(RegisterModifier.BLOODY))
        .also{ regItem["grasping_scepter"] = it}

    val VISCERAL_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(RegisterModifier.EMPOWERED, ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["visceral_scepter"] = it}

    val VAMPIRIC_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VAMPIRIC_BLADE),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withAugments(listOf(RegisterEnchantment.LEECH_LIFE))
        .withModifiers(listOf(RegisterModifier.BLOODY))
        .also{ regItem["vampiric_scepter"] = it}
        
    val SCEPTER_OF_THE_PACT = CustomScepterItem(VisceraeScepterToolMaterial(VAMPIRIC_BLADE),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withAugments(listOf(RegisterEnchantment.LEECH_LIFE))
        .withModifiers(listOf(RegisterModifier.BLOOD_PACT, RegisterModifier.BLOOD_MAGIC))
        .also{ regItem["scepter_of_the_pact"] = it}

    val SPITE_OF_THE_BLOOD_WITCH = SpiteOfTheBloodWitchScepterItem(
        BloodWitchToolMaterial,
        FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC))
        .withModifiers(listOf(RegisterModifier.BLOOD_PACT,RegisterModifier.BLOOD_MAGIC, ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["spite_of_the_blood_witch"] = it}

    fun registerAll(){
        for (k in regItem.keys){
            Registry.register(Registry.ITEM, Identifier(Viscerae.MOD_ID,k), regItem[k])
        }
    }
}
