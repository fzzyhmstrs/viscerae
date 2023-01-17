package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.Viscerae
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object RegisterTag {

    val BLOODSTONE_GEMS_TAG: TagKey<Item> = TagKey.of(RegistryKeys.ITEM,Identifier("c","bloodstone_gems"))    

}
