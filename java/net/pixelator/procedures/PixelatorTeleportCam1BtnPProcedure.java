package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;

public class PixelatorTeleportCam1BtnPProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String key = "";
		if (PixelatorModVariables.cameras_decoded.size() >= 6 * entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page - 5) {
			{
				var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
				if (_playerVars != null) {
					_playerVars.selected_cam = /*@String*/(new Object() {
						private <E> E getListElement(ArrayList<Object> objects, int index, Class<E> eClass, Object defaultValue) {
							if (index < objects.size()) {
								var element = objects.get(index);
								if (eClass.isInstance(element)) {
									return eClass.cast(element);
								}
							}
							return eClass.cast(defaultValue);
						}
					}.getListElement(PixelatorModVariables.cameras_decoded, (int) ((6 * entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page - 5) - 1), String.class, ""));
					_playerVars.markSyncDirty();
				}
			}
			if (("screen").equals(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_type)) {
				PixelatorSelectProcedure.execute(world, x, y, z, entity);
			} else if (("auto").equals(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_type)) {
				AutomaticPixelatorScreenSelectProcedure.execute(world, x, y, z, entity);
			} else if (("tablet").equals(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_type)) {
				TabletSelectProcedure.execute(world, x, y, z, entity);
			}
		}
	}
}