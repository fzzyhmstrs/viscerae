package me.fzzyhmstrs.viscerae.config

import me.fzzyhmstrs.amethyst_core.coding_util.SyncedConfigHelper
import me.fzzyhmstrs.amethyst_core.coding_util.SyncedConfigHelper.readOrCreate
import me.fzzyhmstrs.viscerae.Viscerae
import me.fzzyhmstrs.viscerae.tool.SpiteOfTheBloodWitchToolMaterial
import net.minecraft.network.PacketByteBuf


object VisceraeConfig: SyncedConfigHelper.SyncedConfig {

    var items: Items

    init{
        items = readOrCreate("items_v0.json", base = Viscerae.MOD_ID){ Items() }
    }

    class Items(){
        var bloodWitchDurability: Int = 50
        var tier1ScepterDurability: Int = 200
        var tier2ScepterDurability: Int = 500
        var baseRegenRateTicks: Long = SpiteOfTheBloodWitchToolMaterial.baseCooldown()
        var vampiricSwordHealFraction: Float = 0.1f
    }

    override fun initConfig() {
        TODO("Not yet implemented")
    }

    override fun readFromServer(buf: PacketByteBuf) {
        TODO("Not yet implemented")
    }

    override fun writeToClient(buf: PacketByteBuf) {
        TODO("Not yet implemented")
    }

}