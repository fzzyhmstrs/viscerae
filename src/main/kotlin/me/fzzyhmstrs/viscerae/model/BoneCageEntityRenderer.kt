package me.fzzyhmstrs.viscerae.model


import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.entity.BoneCageEntity
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3f

@Suppress("PrivatePropertyName", "SpellCheckingInspection")
class BoneCageEntityRenderer(context: EntityRendererFactory.Context): EntityRenderer<BoneCageEntity>(context) {
    private val TEXTURE = Identifier(Viscerae.MOD_ID,"textures/entity/bone_cage.png")
    private val model: EvokerFangsEntityModel<BoneCageEntity> = EvokerFangsEntityModel(context.getPart(
        EntityModelLayers.EVOKER_FANGS
    ))

    override fun render(
        boneCageEntity: BoneCageEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        val h = boneCageEntity.getAnimationProgress(g)
        if (h == 0.0f) {
            return
        }
        var j = 2.0f
        if (h > 0.9f) {
            j *= (1.0f - h) / 0.1f
        }
        matrixStack.push()
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0f - boneCageEntity.yaw))
        matrixStack.scale(-j, -j, j)
        matrixStack.translate(0.0, -0.626, 0.0)
        matrixStack.scale(0.5f, 0.5f, 0.5f)
        model.setAngles(boneCageEntity, h, 0.0f, 0.0f, boneCageEntity.yaw, boneCageEntity.pitch)
        val vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(TEXTURE))
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f)
        matrixStack.pop()
        super.render(boneCageEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }

    override fun getTexture(entity: BoneCageEntity?): Identifier {
        return TEXTURE
    }
}