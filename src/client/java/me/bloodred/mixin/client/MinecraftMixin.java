package me.bloodred.mixin.client;

import me.bloodred.FreelookManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    
    @Shadow
    public Options options;
    
    @Inject(at = @At("HEAD"), method = "handleKeybinds")
    private void onHandleKeybinds(CallbackInfo ci) {
        FreelookManager manager = FreelookManager.getInstance();
        
        // Check if perspective toggle key was pressed while freelook is toggled
        if (manager.isFreelookEnabled() && manager.isToggleMode()) {
            while (this.options.keyTogglePerspective.consumeClick()) {
                manager.handlePerspectiveToggle();
                // Consume the key press to prevent normal perspective toggle
            }
        }
    }
}