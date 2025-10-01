package me.bloodred.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class FreelookConfigScreen extends Screen {
    private final Screen parent;
    private final FreelookConfig config;
    
    private SensitivitySlider sensitivitySlider;
    private CycleButton<Boolean> invertYButton;
    private CycleButton<Boolean> smoothTransitionButton;
    private CycleButton<Boolean> autoThirdPersonButton;
    private CycleButton<PerspectiveToggleBehavior> perspectiveToggleBehaviorButton;
    
    public FreelookConfigScreen(Screen parent, FreelookConfig config) {
        super(Component.literal("Freelook++ Configuration"));
        this.parent = parent;
        this.config = config;
    }
    
    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 60;
        int spacing = 30;
        
        sensitivitySlider = new SensitivitySlider(centerX - 100, startY, 200, 20, config);
        addRenderableWidget(sensitivitySlider);
        
        invertYButton = CycleButton.onOffBuilder(config.isInvertY())
            .create(centerX - 100, startY + spacing, 200, 20,
                Component.literal("Invert Y Axis"),
                (button, value) -> config.setInvertY(value));
        addRenderableWidget(invertYButton);
        
        smoothTransitionButton = CycleButton.onOffBuilder(config.isSmoothTransition())
            .create(centerX - 100, startY + spacing * 2, 200, 20,
                Component.literal("Smooth Transition"),
                (button, value) -> config.setSmoothTransition(value));
        addRenderableWidget(smoothTransitionButton);
        
        autoThirdPersonButton = CycleButton.onOffBuilder(config.isAutoThirdPerson())
            .create(centerX - 100, startY + spacing * 3, 200, 20,
                Component.literal("Auto Third Person"),
                (button, value) -> config.setAutoThirdPerson(value));
        addRenderableWidget(autoThirdPersonButton);
        
        perspectiveToggleBehaviorButton = CycleButton.<PerspectiveToggleBehavior>builder(value -> Component.translatable("config.freelookplusplus.perspective_toggle_behavior." + value.name().toLowerCase()))
            .withValues(PerspectiveToggleBehavior.values())
            .withInitialValue(config.getPerspectiveToggleBehavior())
            .create(centerX - 100, startY + spacing * 4, 200, 20,
                Component.translatable("config.freelookplusplus.perspective_toggle_behavior"),
                (button, value) -> config.setPerspectiveToggleBehavior(value));
        addRenderableWidget(perspectiveToggleBehaviorButton);
        
        addRenderableWidget(Button.builder(Component.literal("Done"), button -> {
            config.save();
            onClose();
        }).bounds(centerX - 100, startY + spacing * 6, 200, 20).build());
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        
        guiGraphics.drawCenteredString(font, this.title, this.width / 2, 20, 0xFFFFFF);
        
        guiGraphics.drawString(font, "Mouse Sensitivity", this.width / 2 - 100, 45, 0xFFFFFF);
        guiGraphics.drawString(font, "Invert Y Axis", this.width / 2 - 100, 75, 0xFFFFFF);
        guiGraphics.drawString(font, "Smooth Transition", this.width / 2 - 100, 105, 0xFFFFFF);
        guiGraphics.drawString(font, "Auto Third Person", this.width / 2 - 100, 135, 0xFFFFFF);
        guiGraphics.drawString(font, Component.translatable("config.freelookplusplus.perspective_toggle_behavior").getString(), this.width / 2 - 100, 165, 0xFFFFFF);
    }
    
    @Override
    public void onClose() {
        if (minecraft != null) {
            minecraft.setScreen(parent);
        }
    }
    
    private static class SensitivitySlider extends AbstractSliderButton {
        private final FreelookConfig config;
        
        public SensitivitySlider(int x, int y, int width, int height, FreelookConfig config) {
            super(x, y, width, height, Component.empty(), (config.getSensitivity() - 0.1) / 2.9);
            this.config = config;
            updateMessage();
        }
        
        @Override
        protected void updateMessage() {
            float sensitivity = (float) (0.1 + value * 2.9);
            setMessage(Component.literal(String.format("%.1fx", sensitivity)));
        }
        
        @Override
        protected void applyValue() {
            float sensitivity = (float) (0.1 + value * 2.9);
            config.setSensitivity(sensitivity);
        }
    }
}