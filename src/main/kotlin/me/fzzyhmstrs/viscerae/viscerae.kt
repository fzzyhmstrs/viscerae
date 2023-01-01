package me.fzzyhmstrs.viscerae

import me.fzzyhmstrs.viscerae.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer

object Viscerae: ModInitializer {

    const val MOD_ID = "viscerae"

    override fun onInitialize() {
        RegisterModifier.registerAll()
        RegisterItem.registerAll()
        RegisterEntity.registerAll()
        RegisterParticle.registerParticleTypes()
    }
}


object VisceraeClient: ClientModInitializer {
    const val MOD_ID = "viscerae"

    override fun onInitializeClient() {
        RegisterRenderer.registerAll()
        RegisterParticle.registerParticleFactories()
    }
}