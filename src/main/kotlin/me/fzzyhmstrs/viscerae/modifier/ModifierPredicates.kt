package me.fzzyhmstrs.viscerae.modifier

import me.fzzyhmstrs.amethyst_core.AC
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.SummonEntityAugment
import net.minecraft.enchantment.Enchantment
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.function.Predicate

object ModifierPredicates {

    val EMPOWERED_AUGMENTS: TagKey<Enchantment> = TagKey.of(Registry.ENCHANTMENT_KEY, Identifier(AC.MOD_ID,"empowered_augments"))
    val SOUL_AUGMENTS: TagKey<Enchantment> = TagKey.of(Registry.ENCHANTMENT_KEY, Identifier(AC.MOD_ID,"soul_augments"))

    val SOUL_PREDICATE = Predicate {id: Identifier -> soulPredicate(id)}
    private fun soulPredicate(id: Identifier): Boolean{
        val augment = Registry.ENCHANTMENT.get(id)?:return false
        val opt = Registry.ENCHANTMENT.getEntry(Registry.ENCHANTMENT.getRawId(augment))
        var bl = false
        opt.ifPresent { entry -> bl = entry.isIn(SOUL_AUGMENTS) }
        return bl
    }

    val EMPOWERED_PREDICATE = Predicate {id: Identifier -> empoweredPredicate(id)}
    private fun empoweredPredicate(id: Identifier): Boolean{
        val augment = Registry.ENCHANTMENT.get(id)?:return false
        val opt = Registry.ENCHANTMENT.getEntry(Registry.ENCHANTMENT.getRawId(augment))
        var bl = false
        opt.ifPresent { entry -> bl = entry.isIn(EMPOWERED_AUGMENTS) }
        return bl
    }

    val SUMMONERS_PREDICATE = Predicate {id: Identifier -> Registry.ENCHANTMENT.get(id) is SummonEntityAugment }

}
