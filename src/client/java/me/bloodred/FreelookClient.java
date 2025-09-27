package me.bloodred;

import me.bloodred.config.FreelookConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreelookClient implements ClientModInitializer {
	public static final String MOD_ID = "freelookplusplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Freelook++ client");
		
		LOGGER.info("Registering keybindings...");
		FreelookKeyBindings.initialize();
		LOGGER.info("Keybindings registered successfully");
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null || client.screen != null) return;
			
			FreelookManager manager = FreelookManager.getInstance();
			
			while (FreelookKeyBindings.FREELOOK_TOGGLE.consumeClick()) {
				manager.toggleFreelook();
			}
			
			while (FreelookKeyBindings.FREELOOK_CONFIG.consumeClick()) {
				client.setScreen(new FreelookConfigScreen(client.screen, manager.getConfig()));
			}
			
			boolean freelookKeyPressed = FreelookKeyBindings.FREELOOK_KEY.isDown();
			if (freelookKeyPressed && !manager.isFreelookEnabled()) {
				manager.enableFreelookHold();
			} else if (!freelookKeyPressed) {
				manager.disableFreelookHold();
			}
		});
		
		LOGGER.info("Freelook++ client initialized successfully");
	}
}