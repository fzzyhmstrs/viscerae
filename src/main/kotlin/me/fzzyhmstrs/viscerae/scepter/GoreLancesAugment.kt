package me.fzzyhmstrs.viscerae.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentPersistentEffectData
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.MiscAugment
import me.fzzyhmstrs.viscerae.registry.RegisterEnchantment
import me.fzzyhmstrs.viscerae.entity.GoreLanceEntity
import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Items
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import kotlin.math.min

class GoreLancesAugment(tier: Int, maxLvl: Int, vararg slot: EquipmentSlot): MiscAugment(tier, maxLvl, *slot),
    PersistentEffectHelper.PersistentEffect {

    override val baseEffect: AugmentEffect
        get() = super.baseEffect.withDuration(28,2,0)
            .withDamage(7.5f,0.1f)

    override fun effect(
        world: World,
        target: Entity?,
        user: LivingEntity,
        level: Int,
        hit: HitResult?,
        effect: AugmentEffect
    ): Boolean {
        val gle = GoreLanceEntity.createGoreLance(world,user, 1.8f,0.75f,effect,level)
        val bl = world.spawnEntity(gle)
        val data = AugmentPersistentEffectData(world, user, BlockPos.ORIGIN, listOf(), level, effect)
        PersistentEffectHelper.setPersistentTickerNeed(RegisterEnchantment.GORE_LANCES, 5,effect.duration(level), data)
        return bl
    }

    override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
        if (data !is AugmentPersistentEffectData) return
        val gle = GoreLanceEntity.createGoreLance(data.world,data.user, 1.8f,0.75f,data.effect,data.level)
        world.spawnEntity(gle)
    }

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(me.fzzyhmstrs.amethyst_core.scepter_util.SpellType.FURY,100,40,22,imbueLevel,me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier.HIGH_TIER, Items.EMERALD_BLOCK)
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.ENTITY_EVOKER_FANGS_ATTACK
    }
}
