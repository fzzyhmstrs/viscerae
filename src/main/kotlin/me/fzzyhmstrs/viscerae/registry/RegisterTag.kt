package me.fzzyhmstrs.viscerae.registry

import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object RegisterTag {

    val BLOODSTONE_GEMS_TAG: TagKey<Item> = TagKey.of(Registry.ITEM_KEY,Identifier("c","bloodstone_gems"))

    val BASIC_TRIAL_KEYS: TagKey<Item> = TagKey.of(Registry.ITEM_KEY,Identifier("viscerae","basic_trial_keys"))
    val ADVANCED_TRIAL_KEYS: TagKey<Item> = TagKey.of(Registry.ITEM_KEY,Identifier("viscerae","advanced_trial_keys"))
}
