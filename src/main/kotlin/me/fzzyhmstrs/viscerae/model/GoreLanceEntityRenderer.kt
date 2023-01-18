package me.fzzyhmstrs.viscerae.model

import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.entity.GoreLanceEntity
import me.fzzyhmstrs.viscerae.entity.MarrowShardEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.ProjectileEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3f

class GoreLanceEntityRenderer(context: EntityRendererFactory.Context): ProjectileEntityRenderer<GoreLanceEntity>(context) {
    private val TEXTURE = Identifier(Viscerae.MOD_ID,"textures/entity/gore_lance.png")

    override fun getTexture(entity: GoreLanceEntity): Identifier {
        return TEXTURE
    }

    override fun render(
        persistentProjectileEntity: GoreLanceEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        matrixStack.push()
        matrixStack.multiply(
            Vec3f.POSITIVE_Y.getDegreesQuaternion(
                MathHelper.lerp(
                    g,
                    (persistentProjectileEntity as PersistentProjectileEntity).prevYaw,
                    (persistentProjectileEntity as Entity).yaw
                ) - 90.0f
            )
        )
        matrixStack.multiply(
            Vec3f.POSITIVE_Z.getDegreesQuaternion(
                MathHelper.lerp(
                    g,
                    (persistentProjectileEntity as PersistentProjectileEntity).prevPitch,
                    (persistentProjectileEntity as Entity).pitch
                )
            )
        )
        val s = (persistentProjectileEntity as PersistentProjectileEntity).shake.toFloat() - g
        if (s > 0.0f) {
            val t = -MathHelper.sin(s * 3.0f) * s
            matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(t))
        }
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(45.0f))
        matrixStack.scale(0.05625f, 0.05625f, 0.05625f)
        matrixStack.translate(-4.0, 0.0, 0.0)
        val vertexConsumer =
            vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(getTexture(persistentProjectileEntity)))
        val entry = matrixStack.peek()
        val matrix4f = entry.positionMatrix
        val matrix3f = entry.normalMatrix
        vertex(matrix4f, matrix3f, vertexConsumer, -0, -2, -2, 0.0f, 0.15625f, -1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, 2, -2, 0.0f, 0.3125f, -1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, 2, -2, 0.0f, 0.15625f, 1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, i)
        vertex(matrix4f, matrix3f, vertexConsumer, -0, -2, -2, 0.0f, 0.3125f, 1, 0, 0, i)
        for (u in 0..1) {
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0f))
            vertex(matrix4f, matrix3f, vertexConsumer, -9, -2, 0, 0.0f, 0.0f, 0, 1, 0, i)
            vertex(matrix4f, matrix3f, vertexConsumer, 9, -2, 0, 0.75f, 0.0f, 0, 1, 0, i)
            vertex(matrix4f, matrix3f, vertexConsumer, 9, 2, 0, 0.75f, 0.15625f, 0, 1, 0, i)
            vertex(matrix4f, matrix3f, vertexConsumer, -9, 2, 0, 0.0f, 0.15625f, 0, 1, 0, i)
        }
        for (u in 0..1) {
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0f))
            vertex(matrix4f, matrix3f, vertexConsumer, -9, -2, 0, 0.0f, 0.3125f, 0, 1, 0, i)
            vertex(matrix4f, matrix3f, vertexConsumer, 9, -2, 0, 0.75f, 0.3125f, 0, 1, 0, i)
            vertex(matrix4f, matrix3f, vertexConsumer, 9, 2, 0, 0.75f, 0.46875f, 0, 1, 0, i)
            vertex(matrix4f, matrix3f, vertexConsumer, -9, 2, 0, 0.0f, 0.46875f, 0, 1, 0, i)
        }
        matrixStack.pop()
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }
}
