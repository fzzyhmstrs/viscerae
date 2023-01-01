package me.fzzyhmstrs.ai_odyssey.particle

import me.fzzyhmstrs.amethyst_core.coding_util.PlayerParticles
import me.fzzyhmstrs.amethyst_core.coding_util.PlayerParticlesV2
import me.fzzyhmstrs.amethyst_imbuement.item.ScepterItem
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteBillboardParticle
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluid
import net.minecraft.particle.ParticleEffect
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

@Environment(value = EnvType.CLIENT)
open class CustomBlockLeakParticle(world: ClientWorld, x: Double, y: Double, z: Double, private val fluid: Fluid): SpriteBillboardParticle(world, x, y, z) {

    init{
        this.setBoundingBoxSpacing(0.01f,0.01f)
        gravityStrength = 0.06f
    }

    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE
    }

    override fun tick() {
        prevPosX = x
        prevPosY = y
        prevPosZ = z
        updateAge()
        if (dead) {
            return
        }
        velocityY -= gravityStrength.toDouble()
        val moveVec = movementAmount()
        this.move(moveVec.x, moveVec.y, moveVec.z)
        this.updateVelocity()
        if (dead) {
            return
        }
        velocityX *= 0.98
        velocityY *= 0.98
        velocityZ *= 0.98
        val blockPos = BlockPos(x, y, z)
        val fluidState = world.getFluidState(blockPos)
        if (fluidState.fluid === fluid && y < (blockPos.y.toFloat() + fluidState.getHeight(
                world,
                blockPos
            )).toDouble()
        ) {
            markDead()
        }
    }

    open fun updateAge() {
        if (maxAge-- <= 0) {
            markDead()
        }
    }

    open fun updateVelocity() {
    }

    open fun movementAmount(): Vec3d{
        return Vec3d(velocityX, velocityY, velocityZ)
    }

    @Environment(value = EnvType.CLIENT)
    open class CustomDripping(world: ClientWorld, x: Double, y: Double, z: Double, fluid: Fluid, private val nextParticle: ParticleEffect):
        CustomBlockLeakParticle(world, x, y, z, fluid) {

        init{
            gravityStrength *= 0.02f
            maxAge = 40
        }

        override fun updateAge() {
            if (maxAge-- <= 0) {
                markDead()
                world.addParticle(nextParticle, x, y, z, velocityX, velocityY, velocityZ)
            }
        }

        override fun updateVelocity() {
            velocityX *= 0.02
            velocityY *= 0.02
            velocityZ *= 0.02
        }
    }

    @Environment(value = EnvType.CLIENT)
    open class CustomFalling(world: ClientWorld, x: Double, y: Double, z: Double, fluid: Fluid, maximumAge: Int = (64 / world.random.nextDouble() * 0.8 + 0.2).toInt()):
        CustomBlockLeakParticle(world, x, y, z, fluid) {

        init{
            maxAge = maximumAge
        }

        override fun updateVelocity() {
            if (onGround) {
                markDead()
            }
        }
    }

    @Environment(value = EnvType.CLIENT)
    open class CustomContinuousFalling(world: ClientWorld, x: Double, y: Double, z: Double,
                                       fluid: Fluid, private val nextParticle: ParticleEffect,
                                       maximumAge: Int = (64 / world.random.nextDouble() * 0.8 + 0.2).toInt()):
        CustomBlockLeakParticle(world, x, y, z, fluid) {

        init{
            maxAge = maximumAge
        }

        override fun updateVelocity() {
            if (onGround) {
                markDead()
            }
            world.addParticle(this.nextParticle, x, y, z, 0.0, 0.0, 0.0)
        }
    }

    @Environment(value = EnvType.CLIENT)
    open class CustomPlayerFollowDripping(world: ClientWorld, x: Double, y: Double, z: Double,
                                          fluid: Fluid, private val nextParticle: ParticleEffect, private val player: PlayerEntity):
        CustomBlockLeakParticle(world, x, y, z, fluid) {

        private var moveVelocity: Vec3d = Vec3d.ZERO
        private var prevDisplayPos: Vec3d

        init{
            gravityStrength *= 0.02f
            maxAge = 40
            prevDisplayPos = this.getDisplayPos(player)
        }

        override fun tick() {
            val newDisplayPos = getDisplayPos(player)
            moveVelocity = newDisplayPos.subtract(prevDisplayPos)
            prevDisplayPos = newDisplayPos
            super.tick()
        }

        override fun updateAge() {
            if (maxAge-- <= 0) {
                markDead()
                val moveVec = movementAmount()
                world.addParticle(nextParticle, x, y, z, moveVec.x, moveVec.y, moveVec.z)
            }
        }

        override fun updateVelocity() {
            velocityX *= 0.02
            velocityY *= 0.02
            velocityZ *= 0.02
        }

        override fun movementAmount(): Vec3d {
            return super.movementAmount().add(moveVelocity)
        }

        open fun getDisplayPos(player: PlayerEntity): Vec3d{
            return PlayerParticlesV2.scepterParticlePos(MinecraftClient.getInstance(), player)
        }
    }

    @Environment(value = EnvType.CLIENT)
    open class CustomLanding(world: ClientWorld, x: Double, y: Double, z: Double, fluid: Fluid, maximumAge: Int = (16 / world.random.nextDouble() * 0.8 + 0.2).toInt()):
        CustomBlockLeakParticle(world, x, y, z, fluid) {

        init{
            maxAge = maximumAge
        }

    }
}