package me.bloodred.mixin.client;

import me.bloodred.FreelookManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    
    @Shadow
    protected abstract void setRotation(float yaw, float pitch);
    
    @Inject(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setRotation(FF)V", ordinal = 1, shift = At.Shift.AFTER))
    public void onSetupAfterRotation(BlockGetter level, Entity entity, boolean detached, boolean thirdPersonReverse, float partialTick, CallbackInfo ci) {
        FreelookManager manager = FreelookManager.getInstance();
        
        if (manager.isFreelookEnabled() && entity instanceof LocalPlayer) {
            LocalPlayer player = (LocalPlayer) entity;
            
            if (manager.isFirstTime() && Minecraft.getInstance().player != null) {
                manager.updateFreelookRotation(0, 0);
                manager.setFirstTime(false);
            }
            
            this.setRotation(manager.getCameraYaw(), manager.getCameraPitch());
        }
        
        if (!manager.isFreelookEnabled() && entity instanceof LocalPlayer) {
            manager.setFirstTime(true);
        }
    }
}