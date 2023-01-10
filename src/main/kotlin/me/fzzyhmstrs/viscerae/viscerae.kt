package me.fzzyhmstrs.viscerae

import me.fzzyhmstrs.viscerae.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import kotlin.random.Random

object Viscerae: ModInitializer {

    const val MOD_ID = "viscerae"
    val vRandom = Random(System.currentTimeMillis())

    override fun onInitialize() {
        RegisterModifier.registerAll()
        RegisterItem.registerAll()
        RegisterBlock.registerAll()
        RegisterEntity.registerAll()
        RegisterEnchantment.registerAll()
        RegisterParticle.registerParticleTypes()
        RegisterStatus.registerAll()
        RegisterNetworking.registerServer()
    }
}


object VisceraeClient: ClientModInitializer {
    const val MOD_ID = "viscerae"

    override fun onInitializeClient() {
        RegisterRenderer.registerAll()
        RegisterParticle.registerParticleFactories()
        RegisterNetworking.registerClient()
    }
}
