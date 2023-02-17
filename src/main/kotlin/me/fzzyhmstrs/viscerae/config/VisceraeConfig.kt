package me.fzzyhmstrs.viscerae.config

import com.google.gson.GsonBuilder
import me.fzzyhmstrs.fzzy_core.coding_util.SyncedConfigHelper.gson
import me.fzzyhmstrs.fzzy_core.coding_util.SyncedConfigHelper.readOrCreate
import me.fzzyhmstrs.fzzy_core.registry.SyncedConfigRegistry
import me.fzzyhmstrs.fzzy_core.coding_util.SyncedConfigHelper
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.tool.SpiteOfTheBloodWitchToolMaterial
import net.minecraft.network.PacketByteBuf


object VisceraeConfig: SyncedConfigHelper.SyncedConfig {

    var items: Items
    var kills: Kills

    init{
        items = readOrCreate("items_v0.json", base = Viscerae.MOD_ID){ Items() }
        kills = readOrCreate("kills_v0.json", base = Viscerae.MOD_ID){ Kills() }
    }

    class Items{
        var bloodWitchDurability: Int = 50
        var tier1ScepterDurability: Int = 200
        var tier2ScepterDurability: Int = 500
        var baseRegenRateTicks: Long = SpiteOfTheBloodWitchToolMaterial.baseCooldown()
        var vampiricSwordHealFraction: Float = 0.1f
        var blazingCrownBoilChance: Float = 0.333333f
        var sorrowCrownActiveDuration: Int = 80
        var ringOfSoulsMaxTier: Int = 10
    }

    class Kills{
        var sorrowCrownKillsTo50PercentDefense: Int = 10000
        var sorrowCrownKillsTo50PercentRegret: Int = 25000
        var ringOfSoulsBaseKillsPerTier: Int = 250
        var ringOfSoulsKillTierMultiplier: Float = 2f

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
