package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.PixelatorMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class PixelatorCameraListDecoderProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		String cameras = "";
		String camera_tmp = "";
		ArrayList<Object> to_remove = new ArrayList<>();
		boolean less_than_6 = false;
		double index = 0;
		double pages = 0;
		double list_lenght = 0;
		cameras = PixelatorModVariables.MapVariables.get(world).cameras;
		cameras = cameras.replace("\"", "");
		PixelatorModVariables.cameras_decoded.clear();
		while (true) {
			if ((cameras).length() == 0 || (cameras).length() == 1 && cameras.contains(",")) {
				break;
			}
			camera_tmp = cameras.substring(0, cameras.indexOf(",", 0));
			PixelatorModVariables.cameras_decoded.add(camera_tmp);
			cameras = cameras.substring(1 + cameras.indexOf(",", 0));
		}
		PixelatorMod.LOGGER.info(("Registered cameras: " + PixelatorModVariables.cameras_decoded));
		PixelatorMod.LOGGER.info("Decoding Done");
		less_than_6 = false;
		list_lenght = PixelatorModVariables.cameras_decoded.size();
		while (!less_than_6) {
			if (list_lenght <= 6) {
				less_than_6 = true;
				PixelatorModVariables.MapVariables.get(world).teleport_max_pages = pages + 1;
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
			}
			list_lenght = list_lenght - 6;
			pages++;
		}
		PixelatorMod.LOGGER.info(("Max Pages set to " + pages));
	}
}