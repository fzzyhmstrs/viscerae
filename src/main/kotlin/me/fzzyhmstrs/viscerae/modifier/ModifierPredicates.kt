package me.fzzyhmstrs.ai_odyssey.modifier

import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.function.Predicate

object ModifierPredicates {

    val SOUL_PREDICATE = Predicate {id: Identifier -> soulPredicate(id)}
    private fun soulPredicate(id: Identifier): Boolean{
        val augment = Registry.ENCHANTMENT.get(id)?:return false
        return augment is SoulAugment
    }

}
