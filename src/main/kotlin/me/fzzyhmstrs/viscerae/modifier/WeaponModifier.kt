package me.fzzyhmstrs.viscerae.modifier

import me.fzzyhmstrs.amethyst_core.coding_util.PerLvlF
import me.fzzyhmstrs.amethyst_core.coding_util.PerLvlI
import me.fzzyhmstrs.amethyst_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierDefaults
import net.minecraft.util.Identifier

class WeaponModifier(modifierId: Identifier = ModifierDefaults.BLANK_ID,val persistant: Boolean = false, val randomSelectable: Boolean = false): AbstractModifier<SwordModifier>(modifierId) {
    
    private var damage: PerLvlF = PerLvlF()
    private var speed: PerLvlF = PerLvlF()
    private var durability: PerLvlI = PerLvlI()
    private var reach: PerLvlF = PerLvlF()
    
    override fun plus(other: WeaponModifier): WeaponModifier {
        this.damage = this.damage.plus(other.damage)
        this.speed = this.speed.plus(other.speed)
        this.durability = this.durability.plus(other.durability)
        this.reach = this.reach.plus(other.reach)
        reaturn this
    }
    
    fun withDamage(dmg: Float = 0.0F, dmgPercent: Float = 0.0f): WeaponModifier {
        this.damage = this.damage.plus(PerLvlF(dmg,0.0f,dmgPercent))
        return this
    }
    fun withSpeed(spd: Float = 0.0F,spdPercent: Float = 0.0F): WeaponModifier {
        this.speed = this.speed.plus(PerLvlF(spd,0.0f,spdPercent))
        return this
    }
    fun withDurability(dur: Int = 0, durPercent = 0): WeaponModifier {
        this.durability = this.durability.plus(PerLvlI(dur,0,durPercent))
        return this
    }
    fun withReach(reach: Float = 0.0F, reachPercent: Float = 0.0f): WeaponModifier {
        this.reach = this.reach.plus(PerLvlI(reach,0.0f,reachPercent))
        return this
    }
    
    fun withDescendant(modifier: WeaponModifer): WeaponModifier {
        addDescendant(modifier)
        return this
    }

    override fun compiler(): Compiler {
        return Compiler(mutableListOf(), WeaponModifier())
    }

    override fun getTranslationKey(): String {
        return "weapon.modifier.${modifierId}"
    }
}
