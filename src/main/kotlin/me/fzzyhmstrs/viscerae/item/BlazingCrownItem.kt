package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.fzzy_core.trinket_util.EffectQueue
import me.fzzyhmstrs.gear_core.interfaces.DamageTracking
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
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
    ): Float {
        val dmg = super.onWearerDamaged(stack, wearer, attacker, source, amount)
        if (attacker != null && wearer.world.random.nextFloat() < VisceraeConfig.items.blazingCrownBoilChance){
            EffectQueue.addStatusToQueue(attacker,RegisterStatus.BLOOD_BOIL,100,0)
        }
        return dmg
    }

}
