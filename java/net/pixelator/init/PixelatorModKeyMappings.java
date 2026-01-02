/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import org.lwjgl.glfw.GLFW;

import net.pixelator.network.ExitCameraViewMessage;
import net.pixelator.network.CameraPropertiesMessage;
import net.pixelator.PixelatorMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PixelatorModKeyMappings {
	public static final KeyMapping EXIT_CAMERA_VIEW = new KeyMapping("key.pixelator.exit_camera_view", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.gameplay") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ExitCameraViewMessage(0, 0));
				ExitCameraViewMessage.pressAction(Minecraft.getInstance().player, 0, 0);
				EXIT_CAMERA_VIEW_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - EXIT_CAMERA_VIEW_LASTPRESS);
				PixelatorMod.PACKET_HANDLER.sendToServer(new ExitCameraViewMessage(1, dt));
				ExitCameraViewMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping CAMERA_TURN_LEFT = new KeyMapping("key.pixelator.camera_turn_left", GLFW.GLFW_KEY_A, "key.categories.gameplay");
	public static final KeyMapping CAMERA_TURN_RIGHT = new KeyMapping("key.pixelator.camera_turn_right", GLFW.GLFW_KEY_D, "key.categories.gameplay");
	public static final KeyMapping CAMERA_TURN_UP = new KeyMapping("key.pixelator.camera_turn_up", GLFW.GLFW_KEY_W, "key.categories.gameplay");
	public static final KeyMapping CAMERA_TURN_DOWN = new KeyMapping("key.pixelator.camera_turn_down", GLFW.GLFW_KEY_S, "key.categories.gameplay");
	public static final KeyMapping CAMERA_PROPERTIES = new KeyMapping("key.pixelator.camera_properties", GLFW.GLFW_KEY_I, "key.categories.gameplay") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraPropertiesMessage(0, 0));
				CameraPropertiesMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	private static long EXIT_CAMERA_VIEW_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(EXIT_CAMERA_VIEW);
		event.register(CAMERA_TURN_LEFT);
		event.register(CAMERA_TURN_RIGHT);
		event.register(CAMERA_TURN_UP);
		event.register(CAMERA_TURN_DOWN);
		event.register(CAMERA_PROPERTIES);
	}

	@Mod.EventBusSubscriber(Dist.CLIENT)
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				EXIT_CAMERA_VIEW.consumeClick();
				CAMERA_PROPERTIES.consumeClick();
			}
		}
	}
}