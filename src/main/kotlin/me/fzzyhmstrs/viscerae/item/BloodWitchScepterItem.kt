package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import me.fzzyhmstrs.fzzy_core.coding_util.PlayerParticlesV2.scepterParticlePos
import me.fzzyhmstrs.viscerae.registry.RegisterParticle
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

class BloodWitchScepterItem(material: ScepterToolMaterial, settings: Settings): CustomScepterItem(material, settings) {

    @Environment(EnvType.CLIENT)
    override fun emitParticles(world: World, client: MinecraftClient, user: LivingEntity) {
        val particlePos = scepterParticlePos(client, user)
        world.addParticle(RegisterParticle.FALLING_BLOOD,particlePos.x, particlePos.y, particlePos.z, 0.0, -0.01, 0.0)
        super.emitParticles(world,client, user)
    }

    override fun particleChance(): Int {
        return 20
    }

}