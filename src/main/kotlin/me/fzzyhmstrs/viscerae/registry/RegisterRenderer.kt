package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.amethyst_core.entity_util.MissileEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRendererFactory

object RegisterRenderer {

    fun registerAll() {
        EntityRendererRegistry.register(
            RegisterEntity.VAMPIRIC_MISSILE_ENTITY
        ) { context: EntityRendererFactory.Context ->
            MissileEntityRenderer(
                context,
                0.1f,
                0.1f,
                0.1f,
                0.6666F,
                1.2F
            )
        }
    }
}