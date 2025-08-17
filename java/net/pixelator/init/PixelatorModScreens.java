/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.client.gui.ServerPropertiesScreen;
import net.pixelator.client.gui.PixelatorSelectorScreen;
import net.pixelator.client.gui.PixelatorCameraSearchScreen;
import net.pixelator.client.gui.CameraBoundToServerScreen;
import net.pixelator.client.gui.CameraBoundScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PixelatorModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(PixelatorModMenus.CAMERA_BOUND.get(), CameraBoundScreen::new);
			MenuScreens.register(PixelatorModMenus.PIXELATOR_SELECTOR.get(), PixelatorSelectorScreen::new);
			MenuScreens.register(PixelatorModMenus.PIXELATOR_CAMERA_SEARCH.get(), PixelatorCameraSearchScreen::new);
			MenuScreens.register(PixelatorModMenus.SERVER_PROPERTIES.get(), ServerPropertiesScreen::new);
			MenuScreens.register(PixelatorModMenus.CAMERA_BOUND_TO_SERVER.get(), CameraBoundToServerScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}