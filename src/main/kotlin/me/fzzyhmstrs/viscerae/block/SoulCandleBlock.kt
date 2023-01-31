package me.fzzyhmstrs.viscerae.block

import net.minecraft.block.BlockState
import net.minecraft.block.CandleBlock
import java.util.function.ToIntFunction

@Suppress("PrivatePropertyName")
class SoulCandleBlock(settings: Settings): CandleBlock(settings) {
    companion object {
        val STATE_TO_LUMINANCE =
            ToIntFunction { state: BlockState ->
                if (state.get(LIT) != false)
                    (4 * state.get(
                        CANDLES
                    )) - 1 else 0
            }

    }
}
