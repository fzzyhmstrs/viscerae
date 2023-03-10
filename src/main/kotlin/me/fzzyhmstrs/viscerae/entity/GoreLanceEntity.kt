package me.fzzyhmstrs.viscerae.entity

import me.fzzyhmstrs.amethyst_core.entity_util.MissileEntity
import me.fzzyhmstrs.amethyst_core.entity_util.ModifiableEffectEntity
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.viscerae.registry.RegisterEnchantment
import me.fzzyhmstrs.viscerae.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Box
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.min

class GoreLanceEntity(entityType: EntityType<out GoreLanceEntity?>, world: World): PersistentProjectileEntity(entityType, world), ModifiableEffectEntity {

    constructor(world: World, owner: LivingEntity, yaw: Float, speed: Float, divergence: Float, x: Double, y: Double, z: Double) : this(
        RegisterEntity.GORE_LANCE_ENTITY,world){
        this.owner = owner
        this.setVelocity(owner,
            owner.pitch,
            yaw,
            0.0f,
            speed,
            divergence)
        this.setPosition(x,y,z)
        this.setRotation(yaw, owner.pitch)
    }

    constructor(world: World, owner: LivingEntity, speed: Float, divergence: Float, pos: Vec3d, rot: Vec3d): this(
        RegisterEntity.GORE_LANCE_ENTITY,world){
        this.owner = owner
        this.setVelocity(rot.x,rot.y,rot.z,speed,divergence)
        this.setPosition(pos)
    }

    override var entityEffects: AugmentEffect = AugmentEffect().withDamage(7.5F)

    override fun passEffects(ae: AugmentEffect, level: Int) {
        super.passEffects(ae, level)
        entityEffects.setDamage(ae.damage(level))
        entityEffects.setAmplifier(ae.amplifier(level))
        entityEffects.setRange(ae.range(level))
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
                entity.heal(0.25f)
                entityEffects.accept(entity, AugmentConsumer.Type.BENEFICIAL)
                applyDamageEffects(entity as LivingEntity?, entity2)
                if (entity2 is LivingEntity) {
                    entityEffects.accept(entity2, AugmentConsumer.Type.HARMFUL)
                }
            }
        }
        discard()
    }

    override fun asItemStack(): ItemStack {
        return ItemStack.EMPTY
    }

    companion object{
        fun createGoreLance(world: World, user: LivingEntity, speed: Float, div: Float,yaw: Float, effects: AugmentEffect, level: Int): GoreLanceEntity {
            val fbe = GoreLanceEntity(
                world, user,yaw, speed, div,
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
