package me.fzzyhmstrs.viscerae.config

import com.google.gson.GsonBuilder
import me.fzzyhmstrs.amethyst_core.coding_util.SyncedConfigHelper
import me.fzzyhmstrs.amethyst_core.coding_util.SyncedConfigHelper.gson
import me.fzzyhmstrs.amethyst_core.coding_util.SyncedConfigHelper.readOrCreate
import me.fzzyhmstrs.amethyst_core.registry.SyncedConfigRegistry
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.tool.SpiteOfTheBloodWitchToolMaterial
import net.minecraft.network.PacketByteBuf


object VisceraeConfig: SyncedConfigHelper.SyncedConfig {

    var items: Items

    init{
        items = readOrCreate("items_v0.json", base = Viscerae.MOD_ID){ Items() }
    }

    class Items{
        var bloodWitchDurability: Int = 50
        var tier1ScepterDurability: Int = 200
        var tier2ScepterDurability: Int = 500
        var baseRegenRateTicks: Long = SpiteOfTheBloodWitchToolMaterial.baseCooldown()
        var vampiricSwordHealFraction: Float = 0.1f
        var blazingCrownBoilChance: Float = 0.333333f
    }

    override fun initConfig() {
        SyncedConfigRegistry.registerConfig(Viscerae.MOD_ID,this)
    }

    override fun readFromServer(buf: PacketByteBuf) {
        items = gson.fromJson(buf.readString(),Items::class.java)
    }

    override fun writeToClient(buf: PacketByteBuf) {
        val gson = GsonBuilder().create()
        buf.writeString(gson.toJson(items))
    }

}
