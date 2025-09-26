package me.bloodred.mixin.client;

import me.bloodred.FreelookManager;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    
    @Inject(method = "turn", at = @At("HEAD"), cancellable = true)
    public void onTurn(double deltaX, double deltaY, CallbackInfo ci) {
        FreelookManager manager = FreelookManager.getInstance();
        
        if (manager.isFreelookEnabled() && (Object) this instanceof LocalPlayer) {
            manager.updateFreelookRotation(deltaX, deltaY);
            ci.cancel();
        }
    }
}