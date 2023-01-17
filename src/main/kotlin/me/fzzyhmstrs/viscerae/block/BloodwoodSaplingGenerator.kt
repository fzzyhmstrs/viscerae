package me.fzzyhmstrs.viscerae.block

import me.fzzyhmstrs.viscerae.registry.RegisterBlock
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.util.math.random.Random
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.TreeConfiguredFeatures

class BloodwoodSaplingGenerator(): SaplingGenerator() {

    override fun getTreeFeature(random: Random, bees: Boolean): RegistryEntry<out ConfiguredFeature<*, *>>? {
        return RegisterBlock.BLOODWOOD_TREE
    }
}