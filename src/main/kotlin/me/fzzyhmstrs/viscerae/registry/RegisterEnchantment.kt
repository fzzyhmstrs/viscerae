package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.scepter.*
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegisterEnchantment {

    private val regEnchant: MutableMap<String, Enchantment> = mutableMapOf()

    val BONE_CAGES = BoneCagesAugment(2,6,EquipmentSlot.MAINHAND).also{regEnchant["bone_cages"] = it}
    val ENNERVATE = EnnervateAugment(3,6,EquipmentSlot.MAINHAND).also{regEnchant["ennervate"] = it}
    val GORE_LANCES = GoreLancesAugment(3,11,EquipmentSlot.MAINHAND).also{regEnchant["gore_lances"] = it}
    val INFUSION = InfusionAugment(1,11,EquipmentSlot.MAINHAND).also{regEnchant["infusion"] = it}
    val IRON_SKIN = IronSkinAugment(1,15,EquipmentSlot.MAINHAND).also{regEnchant["iron_skin"] = it}
    val LEECH_LIFE = LeechLifeAugment(1,11, EquipmentSlot.MAINHAND).also{regEnchant["leech_life"] = it}
    val MARROW_SHARDS = MarrowShardsAugment(2,16, EquipmentSlot.MAINHAND).also{regEnchant["marrow_shards"] = it}
    val NETHERITE_SKIN = NetheriteSkinAugment(3,13,EquipmentSlot.MAINHAND).also{regEnchant["netherite_skin"] = it}
    val VAMPIRIC_BOLT = VampiricBoltAugment(1,25, EquipmentSlot.MAINHAND).also{regEnchant["vampiric_bolt"] = it}
    val VAMPIRIC_SLASH = VampiricSlashAugment(2,15, EquipmentSlot.MAINHAND).also{regEnchant["vampiric_slash"] = it}


    fun registerAll(){
        for (k in regEnchant.keys){
            val enchant = regEnchant[k]
            Registry.register(Registry.ENCHANTMENT, Identifier(Viscerae.MOD_ID, k), enchant)
            if (enchant is ScepterAugment){
                AugmentHelper.registerAugmentStat(enchant)
            }
        }
    }

}
