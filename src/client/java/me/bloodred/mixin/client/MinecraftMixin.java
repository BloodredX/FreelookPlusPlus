package me.bloodred.mixin.client;

import me.bloodred.FreelookManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    
    @Inject(at = @At("HEAD"), method = "handleKeybinds")
    private void onHandleKeybinds(CallbackInfo ci) {
        FreelookManager manager = FreelookManager.getInstance();
    }
}