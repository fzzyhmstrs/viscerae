package me.fzzyhmstrs.viscerae.modifier

import me.fzzyhmstrs.amethyst_core.coding_util.PerLvlF
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.amethyst_core.raycaster_util.RaycasterUtil
import me.fzzyhmstrs.amethyst_core.registry.EventRegistry
import me.fzzyhmstrs.amethyst_core.trinket_util.EffectQueue
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import me.fzzyhmstrs.viscerae.registry.RegisterStatus
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack


object ModifierConsumers {

    private val BLOOD_SLASH_POWER = PerLvlF(8f,0.2f,0f)

    //weapon modifier consumers
    val BLOOD_SLASH_USE_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            val rot = user.getRotationVec(1.0f)
            val nbt = stack.orCreateNbt
            var power = 0
            if (nbt.contains("slash_power")){
                power = nbt.getInt("slash_power")
            }
            val dmg = BLOOD_SLASH_POWER.value(power)
            nbt.putInt("slash_power",0)
    }
        
    val BLOOD_SLASH_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, _: LivingEntity, target: LivingEntity? ->
            if (target != null && !target.isAlive){
                val nbt = stack.orCreateNbt
                var power = 0
                if (nbt.contains("slash_power")){
                    power = nbt.getInt("slash_power")
                }
                nbt.putInt("slash_power",power + 1)
            }
    }
        
    val BLOODTHIRSTY_USE_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            user.damage(DamageSource.STARVE, 10f)
            EffectQueue.addStatusToQueue(user,RegisterStatus.BLOODTHIRST,160,0)
            EffectQueue.addStatusToQueue(user,StatusEffects.RESISTANCE,160,0)
            if (user is PlayerEntity){
                user.itemCooldownManager.set(stack.item,1200)
            }
    }
        
    val BLOODTHIRSTY_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (user.hasStatusEffect(RegisterStatus.BLOODTHIRST)){
                if (target != null && !target.isAlive()){
                    val effect = user.getStatusEffect(RegisterStatus.BLOODTHIRST)
                    val amp = effect?.amplifier?:0
                    val duration = effect?.duration?:0
                    if (duration > 0){
                        val duration2 = if(duration < 160) {160} else {duration}
                        user.addStatusEffect(StatusEffectInstance(RegisterStatus.BLOODTHIRST,duration2,amp + 1))
                    }
                }
            }
    }

    val DEADBLOW_USE_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target != null){
                target.damage(DamageSource.MAGIC,10f)
                target.takeKnockback(2.0,user.x - target.x,user.z - target.z)
                EffectQueue.addStatusToQueue(user,StatusEffects.SLOWNESS,100,3)
                if (user is PlayerEntity){
                    user.itemCooldownManager.set(stack.item,200)
                }
            }
    }
        
    val FRENZIED_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target != null && !target.isAlive()){
                val effect = user.getStatusEffect(RegisterStatus.BLOODLUST)
                val amp = effect?.amplifier?:0
                val duration = effect?.duration?:0
                if (duration > 0){
                    val duration2 = if(duration < 80) {80} else {duration}
                    user.addStatusEffect(StatusEffectInstance(RegisterStatus.BLOODLUST,duration2,amp + 1))
                }
            }
    }
        
    val GLUTTONOUS_TICK_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (EventRegistry.ticker_40.isReady()){
                if (stack.isDamaged && user is PlayerEntity){
                    val nbt = stack.nbt
                    if (stack.damage > 100 || (nbt != null && nbt.contains("feeding") && nbt.getBoolean("feeding"))){
                        
                    }
                }
            }
    }
        
    val INNER_FIRE_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            target?.addStatusEffect(StatusEffectInstance(RegisterStatus.BLOOD_BOIL,100,1))
    }
        
    val SOUL_BOMB_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target != null && !target.isAlive()){
                
            }
    }
        
    val SOUL_CHAINS_USE_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target != null){
                val entityList = RaycasterUtil.raycastEntityArea(4.5, user, target.pos)
            }
    }
        
    val VAMPIRIC_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target != null){
                val recentDamage = target.damageTracker.mostRecentDamage
                val recentAmount = recentDamage?.damage?:0f
                val healAmount = recentAmount * VisceraeConfig.items.vampiricSwordHealFraction
                user.heal(healAmount)
            }
    }
        
    val VITAL_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer {stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target != null && !target.isAlive()){
                
            }
    }

        
    //scepter modifier consumers

    val BLOOD_PACT_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodPactConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.isPlayer){
                it.damage(DamageSource.STARVE,0.33333F)
            }
        }
    }

    val MARROW_PACT_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> marrowPactConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun marrowPactConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.isPlayer){
                it.damage(DamageSource.STARVE,1.0F)
            }
        }
    }

    val BLOODY_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> bloodyConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun bloodyConsumer(list: List<LivingEntity>){
        if (Viscerae.vRandom.nextFloat() < 0.04) {
            list.forEach {
                if (it.isPlayer) {
                    EffectQueue.addStatusToQueue(it, StatusEffects.REGENERATION,50,1)
                }
            }
        }
    }

    val EMPOWERED_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> empoweredConsumer(list) }, AugmentConsumer.Type.BENEFICIAL)
    private fun empoweredConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it.hasStatusEffect(RegisterStatus.BLOODLUST)){
                val effect = it.getStatusEffect(RegisterStatus.BLOODLUST)
                val amp = effect?.amplifier?:0
                val duration = effect?.duration?:0
                if (duration > 0){
                    val duration2 = if(duration < 100) {100} else {duration}
                    it.addStatusEffect(StatusEffectInstance(RegisterStatus.BLOODLUST,duration2,amp + 1))
                }
            } else {
                it.addStatusEffect(
                    StatusEffectInstance(RegisterStatus.BLOODLUST, 100)
                )
            }
        }
    }
}
