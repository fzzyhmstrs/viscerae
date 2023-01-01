package me.fzzyhmstrs.viscerae.entity

import me.fzzyhmstrs.amethyst_core.entity_util.MissileEntity
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World

class VampiricBoltEntity(entityType: EntityType<out VampiricBoltEntity?>, world: World): MissileEntity(entityType, world) {

    constructor(world: World, owner: LivingEntity, speed: Float, divergence: Float, x: Double, y: Double, z: Double) : this(
        RegisterEntity.VAMPIRIC_MISSILE_ENTITY,world){
        this.owner = owner
        this.setVelocity(owner,
            owner.pitch,
            owner.yaw,
            0.0f,
            speed,
            divergence)
        this.setPosition(x,y,z)
        this.setRotation(owner.yaw, owner.pitch)
    }

    override var entityEffects: AugmentEffect = AugmentEffect().withDamage(3.0F).withAmplifier(2)

    override fun passEffects(ae: AugmentEffect, level: Int) {
        super.passEffects(ae, level)
        ae.addDuration(ae.amplifier(level))
    }
    
    override fun onEntityHit(entityHitResult: EntityHitResult) {
        if (world.isClient) {
            return
        }
        val entity = owner
        if (entity is LivingEntity) {
            val entity2 = entityHitResult.entity
            val bl = entity2.damage(
                DamageSource.thrownProjectile(this,owner),
                entityEffects.damage(0)
            )
            if (bl) {
                entityEffects.accept(entity, AugmentConsumer.Type.BENEFICIAL)
                entity.heal(entityEffects.amplifier(0).toFloat())
                applyDamageEffects(entity as LivingEntity?, entity2)
                if (entity2 is LivingEntity) {
                    entityEffects.accept(entity2, AugmentConsumer.Type.HARMFUL)
                }
            }
        }
        discard()
    }

    override fun getParticleType(): ParticleEffect {
        return ParticleTypes.ASH
    }

    companion object{
        fun createVampiricBolt(world: World, user: LivingEntity, speed: Float, div: Float, effects: AugmentEffect, level: Int): VampiricBoltEntity {
            val fbe = VampiricBoltEntity(
                world, user, speed, div,
                user.x - (user.width + 0.5f) * 0.5 * MathHelper.sin(user.bodyYaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(
                    user.pitch * (Math.PI.toFloat() / 180)
                ),
                user.eyeY - 0.6 - 0.8 * MathHelper.sin(user.pitch * (Math.PI.toFloat() / 180)),
                user.z + (user.width + 0.5f) * 0.5 * MathHelper.cos(user.bodyYaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(
                    user.pitch * (Math.PI.toFloat() / 180)
                ),
            )
            fbe.passEffects(effects, level)
            return fbe
        }
    }

}
