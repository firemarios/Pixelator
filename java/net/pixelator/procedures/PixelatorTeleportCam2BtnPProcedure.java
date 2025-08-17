package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;

public class PixelatorTeleportCam2BtnPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String key = "";
		if (PixelatorModVariables.cameras_decoded.size() >= 6 * (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_page - 4) {
			{
				String _setval = /*@String*/(new Object() {
					private <E> E getListElement(ArrayList<Object> objects, int index, Class<E> eClass, Object defaultValue) {
						if (index < objects.size()) {
							var element = objects.get(index);
							if (eClass.isInstance(element)) {
								return eClass.cast(element);
							}
						}
						return eClass.cast(defaultValue);
					}
				}.getListElement(PixelatorModVariables.cameras_decoded, (int) ((6 * (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_page - 4) - 1),
						String.class, ""));
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.selected_cam = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (("screen").equals((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_type)) {
				PixelatorSelectProcedure.execute(world, x, y, z, entity);
			} else if (("auto").equals((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_type)) {
				AutomaticPixelatorScreenSelectProcedure.execute(world, x, y, z, entity);
			} else if (("tablet").equals((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_type)) {
				TabletSelectProcedure.execute(world, x, y, z, entity);
			}
		}
	}
}