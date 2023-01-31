package me.fzzyhmstrs.viscerae.interfaces;

import net.minecraft.nbt.NbtCompound;

public interface TrialTracking {

    void winTrial();

    void setTrialsWon(int trialsWon);

    int trialsWon();

    default void writeTrialNbt(NbtCompound nbt){
        nbt.putInt("Trials_Won",trialsWon());
    }

    default void readTrialNbt(NbtCompound nbt){
        if (nbt.contains("Trials_Won")){
            setTrialsWon(nbt.getInt("Trials_Won"));
        }
    }

}
