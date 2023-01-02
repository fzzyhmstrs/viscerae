package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.item.CustomScepterItem
import me.fzzyhmstrs.viscerae.item.PendantOfMemoriesItem
import me.fzzyhmstrs.viscerae.item.SpiteOfTheBloodWitchScepterItem
import me.fzzyhmstrs.viscerae.item.VampiricSwordItem
import me.fzzyhmstrs.viscerae.tool.BloodWitchToolMaterial
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

    val BLOODSTONE = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)).also{ regItem["bloodstone"] = it}
    val VISCERA = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)).also{ regItem["viscera"] = it}

    val PENDANT_OF_MEMORIES = PendantOfMemoriesItem(FabricItemSettings().maxDamage(250).group(ItemGroup.MISC))
    val VAMPIRIC_SWORD = VampiricSwordItem(VampiricToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["vampiric_sword"] = it}

    val BLOODY_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(RegisterModifier.BLOODY,ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["bloody_scepter"] = it}

    val VISCERAL_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(RegisterModifier.BLOODY,ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["visceral_scepter"] = it}

    val VAMPIRIC_SCEPTER = CustomScepterItem(VisceraeScepterToolMaterial(VAMPIRIC_SWORD),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.LEECH_LIFE))
        .withModifiers(listOf(RegisterModifier.BLOODY,ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["visceral_scepter"] = it}

    val SPITE_OF_THE_BLOOD_WITCH = SpiteOfTheBloodWitchScepterItem(
        BloodWitchToolMaterial,
        FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC)
    )
        .withModifiers(listOf(RegisterModifier.BLOOD_PACT,RegisterModifier.BLOOD_MAGIC, ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["spite_of_the_blood_witch"] = it}

    fun registerAll(){
        for (k in regItem.keys){
            Registry.register(Registry.ITEM, Identifier(Viscerae.MOD_ID,k), regItem[k])
        }
    }
}