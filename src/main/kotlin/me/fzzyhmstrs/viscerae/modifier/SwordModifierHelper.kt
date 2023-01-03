package me.fzzyhmstrs.viscerae.modifier

import me.fzzyhmstrs.amethyst_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.AbstractModifierHelper
import net.minecraft.item.ItemStack

object SwordModifierHelper: AbstractModifierHelper<SwordModifier>() {

    override val fallbackData: AbstractModifier<SwordModifier>.CompiledModifiers
        get() = TODO("Not yet implemented")

    override fun gatherActiveModifiers(stack: ItemStack) {
        TODO("Not yet implemented")
    }
}