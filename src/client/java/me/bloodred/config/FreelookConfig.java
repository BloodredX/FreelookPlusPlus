package me.bloodred.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FreelookConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("freelookplusplus.json");
    
    public float sensitivity = 1.0f;
    public boolean invertY = false;
    public boolean smoothTransition = true;
    public boolean autoThirdPerson = true;
    
    public static FreelookConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                return GSON.fromJson(json, FreelookConfig.class);
            } catch (IOException e) {
                System.err.println("Failed to load Freelook++ config: " + e.getMessage());
            }
        }
        
        FreelookConfig config = new FreelookConfig();
        config.save();
        return config;
    }
    
    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            String json = GSON.toJson(this);
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            System.err.println("Failed to save Freelook++ config: " + e.getMessage());
        }
    }
    
    public float getSensitivity() {
        return Math.max(0.1f, Math.min(3.0f, sensitivity));
    }
    
    public void setSensitivity(float sensitivity) {
        this.sensitivity = Math.max(0.1f, Math.min(3.0f, sensitivity));
    }
    
    public boolean isInvertY() {
        return invertY;
    }
    
    public void setInvertY(boolean invertY) {
        this.invertY = invertY;
    }
    
    public boolean isSmoothTransition() {
        return smoothTransition;
    }
    
    public void setSmoothTransition(boolean smoothTransition) {
        this.smoothTransition = smoothTransition;
    }
    
    public boolean isAutoThirdPerson() {
        return autoThirdPerson;
    }
    
    public void setAutoThirdPerson(boolean autoThirdPerson) {
        this.autoThirdPerson = autoThirdPerson;
    }
}