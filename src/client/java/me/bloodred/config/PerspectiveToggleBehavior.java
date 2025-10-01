package me.bloodred.config;

public enum PerspectiveToggleBehavior {
    DO_NOTHING("Do Nothing"),
    DEACTIVATE_FREELOOK("Deactivate Freelook");
    
    private final String displayName;
    
    PerspectiveToggleBehavior(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}