package me.fzzyhmstrs.viscerae.item.utils

class TierLeveler(private val multiplier: Float, private val basePerTier: Int) {

    private fun amountToNextTier(perTier: Int, tier: Int): Int{
        if (tier <= 1) return basePerTier
        return (perTier * (multiplier * (tier - 1))).toInt()
    }

    fun getTierInfo(amount:Int): Pair<Int,Int>{
        if (amount < basePerTier) return Pair(1,basePerTier)
        var tierAmount = basePerTier
        var tier = 2
        while (tierAmount < amount){
            tierAmount += amountToNextTier(basePerTier,tier)
            tier++
        }
        tier -= 1
        return Pair(tier,tierAmount)
    }

}