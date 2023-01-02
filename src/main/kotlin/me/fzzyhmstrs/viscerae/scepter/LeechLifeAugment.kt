package me.fzzyhmstrs.viscerae.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.MinorSupportAugment
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.registry.RegisterItem
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class LeechLifeAugment(tier: Int, maxLvl: Int, vararg slot: EquipmentSlot): MinorSupportAugment(tier, maxLvl, *slot) {

    override val baseEffect: AugmentEffect
        get() = super.baseEffect
            .withDamage(4.5F,0.5F)
            .withRange(4.5,0.5)
            .withAmplifier(2)

    override fun supportEffect(
        world: World,
        target: Entity?,
        user: LivingEntity,
        level: Int,
        effects: AugmentEffect
    ): Boolean {
        return if(target != null) {
            if (target is LivingEntity) {
                val bl = if(target.isUndead) {
                    target.damage(DamageSource.magic(user,user),effects.damage(level) * effects.amplifier(level))
                } else {
                    target.damage(DamageSource.magic(user,user),effects.damage(level))
                }
                if (bl) {
                    effects.accept(target,AugmentConsumer.Type.HARMFUL)
                    effects.accept(user, AugmentConsumer.Type.BENEFICIAL)
                    if (user is ServerPlayerEntity){
                        sendParticles(target.pos, user)
                    }
                    generateParticles(world,target.pos)
                    world.playSound(null, target.blockPos, soundEvent(), SoundCategory.PLAYERS, 0.8F, 1.3F)
                }
                bl
            } else {
                false
            }
        } else {
            false
        }
    }

    private fun sendParticles(pos: Vec3d, user: ServerPlayerEntity){
        val buf = PacketByteBufs.create()
        buf.writeDouble(pos.x)
        buf.writeDouble(pos.y)
        buf.writeDouble(pos.z)
        ServerPlayNetworking.send(user, LEECH_PARTICLES, buf)
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.ENTITY_ELDER_GUARDIAN_HURT
    }

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.FURY,20,8,10, imbueLevel, LoreTier.LOW_TIER, RegisterItem.BLOODSTONE)
    }

    companion object{
        private val LEECH_PARTICLES = Identifier(Viscerae.MOD_ID,"leech_particles")
        fun registerClient(){
            ClientPlayNetworking.registerGlobalReceiver(LEECH_PARTICLES){client,_,buf,_ ->
                val world = client.world ?: return@registerGlobalReceiver
                val posX = buf.readDouble()
                val posY = buf.readDouble()
                val posZ = buf.readDouble()
                client.execute {
                    generateParticles(world, Vec3d(posX, posY, posZ))
                }
            }
        }
        private fun generateParticles(world: World, pos: Vec3d){
            val random = Viscerae.vRandom
            TODO()
        }
    }
}
