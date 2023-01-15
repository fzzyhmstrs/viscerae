package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.amethyst_imbuement.item.CustomAxeItem
import me.fzzyhmstrs.amethyst_imbuement.item.CustomHoeItem
import me.fzzyhmstrs.amethyst_imbuement.item.CustomPickaxeItem
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.armor.BloodstoneArmorMaterial
import me.fzzyhmstrs.viscerae.armor.InsatiableArmorMaterial
import me.fzzyhmstrs.viscerae.item.*
import me.fzzyhmstrs.viscerae.tool.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.*
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
    val ENSOULED_GEM = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)).also{ regItem["ensouled_gem"] = it}
    val LIFEBLOOD_OF_THE_PACT = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC)).also{ regItem["lifeblood_of_the_pact"] = it}
    val VISCERA = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.COMMON)).also{ regItem["viscera"] = it}

    //tools
    val BLOODSTONE_AXE = CustomAxeItem(BloodstoneToolMaterial,5.0f,-3.0f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_axe"] = it}
    val BLOODSTONE_HOE = CustomHoeItem(BloodstoneToolMaterial,0,0.0f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_hoe"] = it}
    val BLOODSTONE_PICKAXE = CustomPickaxeItem(BloodstoneToolMaterial,1,-2.8f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_pickaxe"] = it}
    val BLOODSTONE_SHOVEL = ShovelItem(BloodstoneToolMaterial,1.5f,-3.0f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_shovel"] = it}
    val BLOODSTONE_SWORD = SwordItem(BloodstoneToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_sword"] = it}
    val VAMPIRIC_BLADE = VampiricSwordItem(VampiricToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["vampiric_blade"] = it}
    val INSATIABLE_HUNGER = SwordItem(BloodstoneToolMaterial,3,-2.4f,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_sword"] = it}
    
    //equipment
    val BLOODSTONE_HELMET = ArmorItem(BloodstoneArmorMaterial, EquipmentSlot.HEAD,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_helmet"] = it}
    val BLOODSTONE_CHESTPLATE = ArmorItem(BloodstoneArmorMaterial,EquipmentSlot.CHEST,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_chestplate"] = it}
    val BLOODSTONE_LEGGINGS = ArmorItem(BloodstoneArmorMaterial,EquipmentSlot.LEGS,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_leggings"] = it}
    val BLOODSTONE_BOOTS = ArmorItem(BloodstoneArmorMaterial,EquipmentSlot.FEET,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_boots"] = it}
    val INSATIABLE_GAZE = ArmorItem(InsatiableArmorMaterial,EquipmentSlot.HEAD,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["instatiable_gaze"] = it}
    val INSATIABLE_ENVY = ArmorItem(InsatiableArmorMaterial,EquipmentSlot.CHEST,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["insatiable_envy"] = it}
    val INSATIABLE_STRIDE = ArmorItem(InsatiableArmorMaterial,EquipmentSlot.LEGS,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["insatiable_stride"] = it}
    val INSATIABLE_TREAD = ArmorItem(InsatiableArmorMaterial,EquipmentSlot.FEET,FabricItemSettings().group(ItemGroup.COMBAT)).also{ regItem["bloodstone_tread"] = it}
    
    //trinkets
    val BLOODY_BAND = BloodyBandItem(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["bloody_band"] = it}
    val RING_OF_SOULS = RingOfSoulsItem(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["ring_of_souls"] = it}
    val CROWN_OF_SORROWS = SorrowCrownItem(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["crown_of_sorrows"] = it}
    val JAGGED_CROWN = JaggedCrownItem(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["jagged_crown"] = it}
    val BLAZING_CROWN = BlazingCrownItem(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["blazing_crown"] = it}
    val PENDANT_OF_MEMORIES = PendantOfMemoriesItem(FabricItemSettings().maxDamage(250).group(ItemGroup.MISC)).also{ regItem["pendant_of_memories"] = it}
    val LOCKET_OF_YOUTH = LocketOfYouthItem(FabricItemSettings().maxDamage(250).group(ItemGroup.MISC)).also{ regItem["locket_of_youth"] = it}
    
    //altar offerings
    val DEPRAVED_POPPET = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE)).also{ regItem["depraved_poppet"] = it}
    val GILDED_FOOT = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE)).also{ regItem["gilded_foot"] = it}
    val GRISLY_POPPET = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE)).also{ regItem["grisly_poppet"] = it}
    val RUNE_OF_ENERGY = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE)).also{ regItem["rune_of_energy"] = it}
    val RUNE_OF_POWER = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE)).also{ regItem["rune_of_power"] = it}
    val RUNE_OF_UNDEATH = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.EPIC)).also{ regItem["rune_of_undeath"] = it}
    val RUNE_OF_VITALITY = Item(FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE)).also{ regItem["rune_of_vitality"] = it}
    val TALISMAN_OF_BONES = Item(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["talisman_of_bones"] = it}
    val SOUL_CANDLE = Item(FabricItemSettings().group(ItemGroup.MISC)).also{ regItem["soul_candle"] = it}

    val BLOODY_SCEPTER = CustomScepterItem(VisceraeT1ScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(RegisterModifier.BLOODY))
        .also{ regItem["bloody_scepter"] = it}
        
    val GRASPING_SCEPTER = CustomScepterItem(VisceraeT1ScepterToolMaterial(VISCERA),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON))
        .withAugments(listOf(RegisterEnchantment.VAMPIRIC_BOLT))
        .withModifiers(listOf(ModifierRegistry.LESSER_REACH))
        .also{ regItem["grasping_scepter"] = it}

    val ENSOULED_SCEPTER = CustomScepterItem(VisceraeT2ScepterToolMaterial(VAMPIRIC_BLADE),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withModifiers(listOf(RegisterModifier.SPIRITED))
        .also{ regItem["ensouled_scepter"] = it}

    val VISCERAL_SCEPTER = VisceralScepterItem(VisceralScepterToolMaterial,FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withAugments(listOf(RegisterEnchantment.MARROW_SHARDS))
        .withModifiers(listOf(RegisterModifier.VISCERAL, ModifierRegistry.LESSER_REACH))
        .also{ regItem["visceral_scepter"] = it}

    val VAMPIRIC_SCEPTER = CustomScepterItem(VisceraeT2ScepterToolMaterial(VAMPIRIC_BLADE),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withAugments(listOf(RegisterEnchantment.LEECH_LIFE))
        .withModifiers(listOf(RegisterModifier.BLOODY, ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["vampiric_scepter"] = it}
        
    val SCEPTER_OF_THE_PACT = CustomScepterItem(VisceraeT2ScepterToolMaterial(VAMPIRIC_BLADE),FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE))
        .withModifiers(listOf(RegisterModifier.BLOOD_PACT, RegisterModifier.BLOOD_MAGIC))
        .also{ regItem["scepter_of_the_pact"] = it}

    val PACT_OF_THE_BLOOD_WITCH = BloodWitchScepterItem(
        SpiteOfTheBloodWitchToolMaterial,
        FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC))
        .withModifiers(listOf(RegisterModifier.BLOOD_PACT,RegisterModifier.BLOOD_MAGIC, ModifierRegistry.LESSER_ATTUNED))
        .also{ regItem["pact_of_the_blood_witch"] = it}

    val SOUL_OF_THE_BLOOD_WITCH = BloodWitchScepterItem(
        SoulOfTheBloodWitchToolMaterial,
        FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC))
        .withModifiers(listOf(RegisterModifier.SPIRITED, ModifierRegistry.LESSER_REACH))
        .also{ regItem["soul_of_the_blood_witch"] = it}

    fun registerAll(){
        for (k in regItem.keys){
            Registry.register(Registry.ITEM, Identifier(Viscerae.MOD_ID,k), regItem[k])
        }
    }
}
