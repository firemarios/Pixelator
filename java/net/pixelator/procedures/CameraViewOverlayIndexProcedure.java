package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class CameraViewOverlayIndexProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Camera " + (("" + Math.round(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).request_index)).length() == 1
				? "0" + Math.round(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).request_index)
				: Math.round(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).request_index));
	}
}