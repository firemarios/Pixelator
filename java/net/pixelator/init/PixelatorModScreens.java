/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.client.gui.TabletSelectorScreen;
import net.pixelator.client.gui.PixelatorSelectorScreen;
import net.pixelator.client.gui.CameraBoundScreen;
import net.pixelator.client.gui.AutomaticPixelatorScreenSelectorScreen;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PixelatorModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(PixelatorModMenus.CAMERA_BOUND.get(), CameraBoundScreen::new);
		event.register(PixelatorModMenus.PIXELATOR_SELECTOR.get(), PixelatorSelectorScreen::new);
		event.register(PixelatorModMenus.AUTOMATIC_PIXELATOR_SCREEN_SELECTOR.get(), AutomaticPixelatorScreenSelectorScreen::new);
		event.register(PixelatorModMenus.TABLET_SELECTOR.get(), TabletSelectorScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}