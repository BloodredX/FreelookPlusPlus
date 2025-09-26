package me.bloodred;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class FreelookKeyBindings {
    public static final String CATEGORY = "category.freelookplusplus.general";
    
    public static final KeyMapping FREELOOK_KEY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
        "key.freelookplusplus.freelook",
        GLFW.GLFW_KEY_LEFT_ALT,
        CATEGORY
    ));
    
    public static final KeyMapping FREELOOK_TOGGLE = KeyBindingHelper.registerKeyBinding(new KeyMapping(
        "key.freelookplusplus.toggle",
        GLFW.GLFW_KEY_F6,
        CATEGORY
    ));
}