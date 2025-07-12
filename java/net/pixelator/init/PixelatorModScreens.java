/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.client.gui.TabletSelectorScreen;
import net.pixelator.client.gui.PixelatorSelectorScreen;
import net.pixelator.client.gui.CameraBoundScreen;
import net.pixelator.client.gui.AutomaticPixelatorScreenSelectorScreen;

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
			MenuScreens.register(PixelatorModMenus.AUTOMATIC_PIXELATOR_SCREEN_SELECTOR.get(), AutomaticPixelatorScreenSelectorScreen::new);
			MenuScreens.register(PixelatorModMenus.TABLET_SELECTOR.get(), TabletSelectorScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}