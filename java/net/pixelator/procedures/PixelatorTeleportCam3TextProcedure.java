package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

import java.util.ArrayList;

public class PixelatorTeleportCam3TextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String key = "";
		if (PixelatorModVariables.cameras_decoded.size() >= 6 * entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page - 3) {
			key = /*@String*/(new Object() {
				private <E> E getListElement(ArrayList<Object> objects, int index, Class<E> eClass, Object defaultValue) {
					if (index < objects.size()) {
						var element = objects.get(index);
						if (eClass.isInstance(element)) {
							return eClass.cast(element);
						}
					}
					return eClass.cast(defaultValue);
				}
			}.getListElement(PixelatorModVariables.cameras_decoded, (int) ((6 * entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page - 3) - 1), String.class, ""));
		}
		return key;
	}
}