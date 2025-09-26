package me.bloodred;

import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;
import net.minecraft.client.player.LocalPlayer;

public class FreelookManager {
    private static FreelookManager instance;
    
    private boolean freelookEnabled = false;
    private boolean toggleMode = false;
    private CameraType originalPerspective = null;
    private float cameraYaw = 0.0f;
    private float cameraPitch = 0.0f;
    private boolean firstTime = true;
    
    private float sensitivity = 1.0f;
    private boolean invertY = false;
    
    private FreelookManager() {}
    
    public static FreelookManager getInstance() {
        if (instance == null) {
            instance = new FreelookManager();
        }
        return instance;
    }
    
    public void toggleFreelook() {
        toggleFreelook(true);
    }
    
    public void enableFreelookHold() {
        if (!freelookEnabled) {
            toggleFreelook(false);
        }
    }
    
    public void disableFreelookHold() {
        if (freelookEnabled && !toggleMode) {
            disableFreelook();
        }
    }
    
    private void toggleFreelook(boolean isToggleMode) {
        if (!freelookEnabled) {
            startFreelook(isToggleMode);
        } else {
            disableFreelook();
        }
    }
    
    private void startFreelook(boolean isToggleMode) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        
        if (player == null) return;
        
        freelookEnabled = true;
        toggleMode = isToggleMode;
        firstTime = true;
        
        originalPerspective = mc.options.getCameraType();
        
        if (originalPerspective == CameraType.FIRST_PERSON) {
            mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
        }
    }
    
    private void disableFreelook() {
        freelookEnabled = false;
        toggleMode = false;
        firstTime = true;
        
        Minecraft mc = Minecraft.getInstance();
        
        if (originalPerspective != null) {
            mc.options.setCameraType(originalPerspective);
        }
        
        originalPerspective = null;
    }
    
    public void updateFreelookRotation(double deltaX, double deltaY) {
        if (!freelookEnabled) return;
        
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        
        if (player == null) return;
        
        if (firstTime) {
            cameraYaw = player.getYRot();
            cameraPitch = player.getXRot();
            firstTime = false;
        }
        
        float mouseSensitivity = mc.options.sensitivity().get().floatValue() * sensitivity;
        
        float pitchDelta = (float) (deltaY * 0.15 * mouseSensitivity);
        float yawDelta = (float) (deltaX * 0.15 * mouseSensitivity);
        
        if (invertY) {
            pitchDelta = -pitchDelta;
        }
        
        cameraPitch = Math.max(-90.0f, Math.min(90.0f, cameraPitch + pitchDelta));
        cameraYaw += yawDelta;
    }
    
    public boolean isFreelookEnabled() {
        return freelookEnabled;
    }
    
    public float getCameraYaw() {
        return cameraYaw;
    }
    
    public float getCameraPitch() {
        return cameraPitch;
    }
    
    public boolean isFirstTime() {
        return firstTime;
    }
    
    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
    
    public void setSensitivity(float sensitivity) {
        this.sensitivity = Math.max(0.1f, Math.min(3.0f, sensitivity));
    }
    
    public float getSensitivity() {
        return sensitivity;
    }
    
    public void setInvertY(boolean invertY) {
        this.invertY = invertY;
    }
    
    public boolean isInvertY() {
        return invertY;
    }
}