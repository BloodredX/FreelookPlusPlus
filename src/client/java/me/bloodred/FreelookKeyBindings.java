package me.bloodred;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class FreelookKeyBindings {

    private static final KeyMapping.Category FREELOOK_CATEGORY = new KeyMapping.Category(ResourceLocation.parse("freelookplusplus:general"));
    
    public static final KeyMapping FREELOOK_KEY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
        "key.freelookplusplus.freelook",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_LEFT_ALT,
        FREELOOK_CATEGORY
    ));
    
    public static final KeyMapping FREELOOK_TOGGLE = KeyBindingHelper.registerKeyBinding(new KeyMapping(
        "key.freelookplusplus.toggle",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_F6,
        FREELOOK_CATEGORY
    ));
    
    public static final KeyMapping FREELOOK_CONFIG = KeyBindingHelper.registerKeyBinding(new KeyMapping(
        "key.freelookplusplus.config",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_F7,
        FREELOOK_CATEGORY
    ));
    

    public static void initialize() {

        System.out.println("Initializing Freelook++ keybindings:");
        System.out.println("- Freelook key: " + FREELOOK_KEY.getName());
        System.out.println("- Toggle key: " + FREELOOK_TOGGLE.getName());
        System.out.println("- Config key: " + FREELOOK_CONFIG.getName());
        System.out.println("Keybindings initialized successfully!");
    }
}