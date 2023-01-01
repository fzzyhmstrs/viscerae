package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.scepter.VampiricBoltAugment
import me.fzzyhmstrs.viscerae.scepter.VampiricSlashAugment
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegisterEnchantment {

    private val regEnchant: MutableMap<String, Enchantment> = mutableMapOf()

    val VAMPIRIC_BOLT = VampiricBoltAugment(1,3, EquipmentSlot.MAINHAND).also{regEnchant["vampiric_bolt"] = it}
    val VAMPIRIC_SLASH = VampiricSlashAugment(1,2, EquipmentSlot.MAINHAND).also{regEnchant["vampiric_slash"] = it}


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