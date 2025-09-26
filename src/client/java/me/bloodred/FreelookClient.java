package me.bloodred;

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
		
		FreelookKeyBindings.FREELOOK_KEY.getClass();
		FreelookKeyBindings.FREELOOK_TOGGLE.getClass();
		
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null || client.screen != null) return;
			
			FreelookManager manager = FreelookManager.getInstance();
			
			while (FreelookKeyBindings.FREELOOK_TOGGLE.consumeClick()) {
				manager.toggleFreelook();
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