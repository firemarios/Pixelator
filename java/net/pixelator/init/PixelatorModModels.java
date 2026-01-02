/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.client.model.Modelpixelator_screen;
import net.pixelator.client.model.Modelpixelator_camera;
import net.pixelator.client.model.Modelautomatic_pixelator_screen_activated;
import net.pixelator.client.model.Modelautomatic_pixelator_screen;
import net.pixelator.client.model.ModelRouterActivated;
import net.pixelator.client.model.ModelRouter;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class PixelatorModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelautomatic_pixelator_screen.LAYER_LOCATION, Modelautomatic_pixelator_screen::createBodyLayer);
		event.registerLayerDefinition(Modelautomatic_pixelator_screen_activated.LAYER_LOCATION, Modelautomatic_pixelator_screen_activated::createBodyLayer);
		event.registerLayerDefinition(Modelpixelator_camera.LAYER_LOCATION, Modelpixelator_camera::createBodyLayer);
		event.registerLayerDefinition(Modelpixelator_screen.LAYER_LOCATION, Modelpixelator_screen::createBodyLayer);
		event.registerLayerDefinition(ModelRouter.LAYER_LOCATION, ModelRouter::createBodyLayer);
		event.registerLayerDefinition(ModelRouterActivated.LAYER_LOCATION, ModelRouterActivated::createBodyLayer);
	}
}