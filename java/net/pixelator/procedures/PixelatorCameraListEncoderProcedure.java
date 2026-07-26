package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.PixelatorMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class PixelatorCameraListEncoderProcedure {
	@SubscribeEvent
	public static void onWorldUnload(net.minecraftforge.event.level.LevelEvent.Unload event) {
		execute(event, event.getLevel());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		double index = 0;
		double list_lenght = 0;
		double pages = 0;
		String cameras = "";
		String camera_tmp = "";
		boolean less_than_6 = false;
		for (int index7 = 0; index7 < (int) PixelatorModVariables.cameras_decoded.size(); index7++) {
			cameras = (!(cameras).isEmpty() ? cameras : "") + "" + /*@String*/(new Object() {
				private <E> E getListElement(ArrayList<Object> objects, int index, Class<E> eClass, Object defaultValue) {
					if (index < objects.size()) {
						var element = objects.get(index);
						if (eClass.isInstance(element)) {
							return eClass.cast(element);
						}
					}
					return eClass.cast(defaultValue);
				}
			}.getListElement(PixelatorModVariables.cameras_decoded, (int) index, String.class, "")) + ",";
			index++;
		}
		PixelatorModVariables.MapVariables.get(world).cameras = cameras;
		PixelatorModVariables.MapVariables.get(world).markSyncDirty();
		PixelatorMod.LOGGER.info("Encoding Done");
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