package me.fzzyhmstrs.viscerae

import com.mojang.logging.LogUtils
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import me.fzzyhmstrs.viscerae.entity.block.Trials
import me.fzzyhmstrs.viscerae.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import kotlin.random.Random

object Viscerae: ModInitializer {

    const val MOD_ID = "viscerae"
    val vRandom = Random(System.currentTimeMillis())
    val LOGGER = LoggerFactory.getLogger("viscerae")

    override fun onInitialize() {
        VisceraeConfig.initConfig()
        RegisterModifier.registerAll()
        RegisterItem.registerAll()
        RegisterBlock.registerAll()
        RegisterEntity.registerAll()
        RegisterEnchantment.registerAll()
        RegisterParticle.registerParticleTypes()
        RegisterStatus.registerAll()
        RegisterNetworking.registerServer()
        Trials.registerTrialsLoader()
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
