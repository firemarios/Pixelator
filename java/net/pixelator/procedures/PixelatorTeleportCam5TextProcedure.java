package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

import java.util.ArrayList;

public class PixelatorTeleportCam5TextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String key = "";
		if (PixelatorModVariables.cameras_decoded.size() >= 6 * (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_page - 1) {
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
			}.getListElement(PixelatorModVariables.cameras_decoded, (int) ((6 * (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_page - 1) - 1), String.class,
					""));
		}
		return key;
	}
}