package me.fzzyhmstrs.ai_odyssey.particle

import me.fzzyhmstrs.viscerae.registry.RegisterParticle
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.fluid.Fluids
import net.minecraft.particle.DefaultParticleType

@Environment(value = EnvType.CLIENT)
class DrippingBloodFactory(private val spriteProvider: SpriteProvider): ParticleFactory<DefaultParticleType> {

    override fun createParticle(
        parameters: DefaultParticleType,
        world: ClientWorld,
        x: Double,
        y: Double,
        z: Double,
        velocityX: Double,
        velocityY: Double,
        velocityZ: Double
    ): Particle {
        val player = MinecraftClient.getInstance().player
        val bloodDrip = if (player != null){
            CustomBlockLeakParticle.CustomPlayerFollowDripping(world, x, y, z, Fluids.WATER,
                RegisterParticle.FALLING_BLOOD, player)
        } else {
            CustomBlockLeakParticle.CustomDripping(world, x, y, z, Fluids.WATER, RegisterParticle.FALLING_BLOOD)
        }
        bloodDrip.setColor(0.5f, 0.0f, 0.0f)
        bloodDrip.setSprite(spriteProvider)

        return bloodDrip
    }
}

@Environment(value = EnvType.CLIENT)
class FallingBloodFactory(private val spriteProvider: SpriteProvider): ParticleFactory<DefaultParticleType> {

    override fun createParticle(
        parameters: DefaultParticleType,
        world: ClientWorld,
        x: Double,
        y: Double,
        z: Double,
        velocityX: Double,
        velocityY: Double,
        velocityZ: Double
    ): Particle {
        val bloodFall = CustomBlockLeakParticle.CustomContinuousFalling(world, x, y, z, Fluids.WATER, RegisterParticle.LANDING_BLOOD)
        bloodFall.setColor(0.5f, 0.0f, 0.0f)
        bloodFall.setSprite(spriteProvider)

        return bloodFall
    }
}

@Environment(value = EnvType.CLIENT)
class LandingBloodFactory(private val spriteProvider: SpriteProvider): ParticleFactory<DefaultParticleType> {

    override fun createParticle(
        parameters: DefaultParticleType,
        world: ClientWorld,
        x: Double,
        y: Double,
        z: Double,
        velocityX: Double,
        velocityY: Double,
        velocityZ: Double
    ): Particle {
        val bloodLanding = CustomBlockLeakParticle.CustomLanding(world, x, y, z, Fluids.WATER)
        bloodLanding.setColor(0.5f, 0.0f, 0.0f)
        bloodLanding.setSprite(spriteProvider)

        return bloodLanding
    }
}