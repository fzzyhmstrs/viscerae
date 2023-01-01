package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.ai_odyssey.particle.DrippingBloodFactory
import me.fzzyhmstrs.ai_odyssey.particle.FallingBloodFactory
import me.fzzyhmstrs.ai_odyssey.particle.LandingBloodFactory
import me.fzzyhmstrs.viscerae.Viscerae
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.client.particle.GlowParticle
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegisterParticle {

    val DRIPPING_BLOOD = FabricParticleTypes.simple()
    val FALLING_BLOOD = FabricParticleTypes.simple()
    val LANDING_BLOOD = FabricParticleTypes.simple()
    val VAMPIRIC = FabricParticleTypes.simple()

    fun registerParticleTypes(){
        Registry.register(Registry.PARTICLE_TYPE, Identifier(Viscerae.MOD_ID,"dripping_blood"), DRIPPING_BLOOD)
        Registry.register(Registry.PARTICLE_TYPE, Identifier(Viscerae.MOD_ID,"falling_blood"), FALLING_BLOOD)
        Registry.register(Registry.PARTICLE_TYPE, Identifier(Viscerae.MOD_ID,"landing_blood"), LANDING_BLOOD)
        Registry.register(Registry.PARTICLE_TYPE, Identifier(Viscerae.MOD_ID,"vampiric"), VAMPIRIC)
    }

    fun registerParticleFactories(){
        ParticleFactoryRegistry.getInstance().register(DRIPPING_BLOOD) { spriteProvider ->
            DrippingBloodFactory(spriteProvider)
        }
        ParticleFactoryRegistry.getInstance().register(FALLING_BLOOD) { spriteProvider ->
            FallingBloodFactory(spriteProvider)
        }
        ParticleFactoryRegistry.getInstance().register(LANDING_BLOOD) { spriteProvider ->
            LandingBloodFactory(spriteProvider)
        }
        ParticleFactoryRegistry.getInstance().register(VAMPIRIC) { spriteProvider ->
            GlowParticle.ElectricSparkFactory(spriteProvider)
        }
    }
}