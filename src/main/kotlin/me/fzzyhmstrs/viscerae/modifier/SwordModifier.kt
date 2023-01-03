package me.fzzyhmstrs.viscerae.modifier

import me.fzzyhmstrs.amethyst_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierDefaults
import net.minecraft.util.Identifier

class SwordModifier(modifierId: Identifier = ModifierDefaults.BLANK_ID): AbstractModifier<SwordModifier>(modifierId) {
    override fun plus(other: SwordModifier): SwordModifier {
        TODO("Not yet implemented")
    }

    override fun compiler(): Compiler {
        TODO("Not yet implemented")
    }

    override fun getTranslationKey(): String {
        TODO("Not yet implemented")
    }
}