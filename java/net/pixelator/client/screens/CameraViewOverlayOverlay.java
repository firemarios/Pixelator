package net.pixelator.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.pixelator.procedures.CameraViewOverlayTimeProcedure;
import net.pixelator.procedures.CameraViewOverlayIndexProcedure;
import net.pixelator.procedures.CameraViewOverlayDisplayOverlayIngameProcedure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class CameraViewOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (CameraViewOverlayDisplayOverlayIngameProcedure.execute(entity)) {
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					CameraViewOverlayTimeProcedure.execute(world), 6, 8, -1, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					CameraViewOverlayIndexProcedure.execute(entity), w - 61, h - 16, -1, false);
		}
	}
}