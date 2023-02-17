package me.fzzyhmstrs.viscerae.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierInitializer
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.config.VisceraeConfig
import me.fzzyhmstrs.viscerae.item.utils.TierLeveler
import me.fzzyhmstrs.viscerae.item.utils.Upgradeable
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.world.World
import kotlin.math.min

class RingOfSoulsItem(settings: Settings): AbstractAugmentJewelryItem(settings), KillTracking, Upgradeable, Modifiable {

    private val tierLeveler = TierLeveler(VisceraeConfig.kills.ringOfSoulsKillTierMultiplier,VisceraeConfig.kills.ringOfSoulsBaseKillsPerTier)

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, context)
        val nbt = stack.nbt ?: return
        if (nbt.contains("tier") && nbt.contains("amount")){
            val tier = nbt.getInt("tier")
            if (tier == VisceraeConfig.items.ringOfSoulsMaxTier){
                tooltip.add(AcText.translatable("item.viscerae.ring_of_souls.tier_max",VisceraeConfig.items.ringOfSoulsMaxTier).formatted(Formatting.GOLD))
            } else {
                val amount = nbt.getInt("amount")
                val kills = getKillCount(stack)
                tooltip.add(AcText.translatable("item.viscerae.ring_of_souls.tier",tier,kills,amount).formatted(Formatting.GOLD))
            }
        } else {
            tooltip.add(AcText.translatable("item.viscerae.ring_of_souls.tier","1","0",VisceraeConfig.kills.ringOfSoulsBaseKillsPerTier).formatted(Formatting.GOLD))
        }
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        incrementKillCount(stack)
        val (tier, amount) = tierLeveler.getTierInfo(getKillCount(stack))
        val nbt = stack.orCreateNbt
        nbt.putInt("tier", min(tier,VisceraeConfig.items.ringOfSoulsMaxTier))
        nbt.putInt("amount",amount)
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

    override fun upgrade(stack: ItemStack, world: World, owner: LivingEntity) {
        TODO("Not yet implemented")
    }

    override fun canUpgrade(stack: ItemStack, world: World): Boolean {
        TODO("Not yet implemented")
    }

    override fun getModifierInitializer(): ModifierInitializer {
        return EquipmentModifierHelper
    }

    override fun defaultModifiers(): List<Identifier> {
        return listOf(SOULS_MODIFIER.modifierId)
    }

    override fun addModifierTooltip(stack: ItemStack, tooltip: MutableList<Text>, context: TooltipContext) {
        EquipmentModifierHelper.addModifierTooltip(stack, tooltip, context)
    }

    companion object{
        private val SOUL_MODIFIER_SCEPTER = AugmentModifier(Identifier(Viscerae.MOD_ID,"souls_modifier_scepter_tier_1")).withDamage(0.5f).also { ModifierRegistry.register(it) }
        private val SOULS_MODIFIER = EquipmentModifier(Identifier(Viscerae.MOD_ID,"souls_modifier_tier_1"), persistent = true, randomSelectable = false).withModifiers(
            SOUL_MODIFIER_SCEPTER.modifierId).also { ModifierRegistry.register(it) }
        init{
            if (VisceraeConfig.items.ringOfSoulsMaxTier > 1){
                for (i in 2..VisceraeConfig.items.ringOfSoulsMaxTier){
                    val soulModifierScepterTierX = AugmentModifier(Identifier(Viscerae.MOD_ID,"souls_modifier_scepter_tier_$i")).withDamage(0.5f*i)
                    ModifierRegistry.register(soulModifierScepterTierX)
                    val soulModifierTierX = EquipmentModifier(Identifier(Viscerae.MOD_ID,"souls_modifier_tier_$i"), persistent = true, randomSelectable = false).withModifiers(
                        soulModifierScepterTierX.modifierId)
                    ModifierRegistry.register(soulModifierTierX)
                }
            }
        }
    }

}