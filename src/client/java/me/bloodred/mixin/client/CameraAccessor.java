package me.bloodred.mixin.client;

import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Camera.class)
public interface CameraAccessor {
    @Accessor("xRot")
    void setXRot(float xRot);
    
    @Accessor("yRot")
    void setYRot(float yRot);
    
    @Accessor("xRot")
    float getXRot();
    
    @Accessor("yRot")
    float getYRot();
}