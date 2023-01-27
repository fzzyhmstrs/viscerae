package me.fzzyhmstrs.viscerae.block

import net.minecraft.block.MushroomPlantBlock
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import java.util.function.Supplier

class DeathcapMushroomBlock(settings: Settings): MushroomPlantBlock(settings,{ TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM }) {
}