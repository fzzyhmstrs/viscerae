package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.mana_util.ManaItem
import me.fzzyhmstrs.amethyst_core.trinket_util.EffectQueue
import me.fzzyhmstrs.amethyst_core.trinket_util.TrinketUtil
import me.fzzyhmstrs.viscerae.registry.RegisterStatus
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

class BlazingCrownItem(settings: Settings): JaggedCrownItem(settings) {

    override fun onWearerDamaged(
        stack: ItemStack,
        wearer: LivingEntity,
        attacker: LivingEntity?,
        source: DamageSource,
        amount: Float
    ) {
        super.onWearerDamaged(stack, wearer, attacker, source, amount)
        if (attacker != null && wearer.world.random.nextFloat() < 0.333333f){
            EffectQueue.addStatusToQueue(attacker,RegisterStatus.BLOOD_BOIL,100,0)
        }
    }

}