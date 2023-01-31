package me.fzzyhmstrs.viscerae.mixins;

import me.fzzyhmstrs.viscerae.interfaces.TrialTracking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements TrialTracking {

    @Unique
    private int viscerae_trialsWon = 0;

    @Override
    public void winTrial() {
        viscerae_trialsWon += 1;
    }

    @Override
    public void setTrialsWon(int trialsWon) {
        viscerae_trialsWon = trialsWon;
    }

    @Override
    public int trialsWon() {
        return viscerae_trialsWon;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void viscerae_writeTrialsToNbt(NbtCompound nbt, CallbackInfo ci){
        writeTrialNbt(nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void viscerae_readTrialsFromNbt(NbtCompound nbt, CallbackInfo ci){
        readTrialNbt(nbt);
    }

}
