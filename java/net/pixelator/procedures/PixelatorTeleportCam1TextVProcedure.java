package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class PixelatorTeleportCam1TextVProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		boolean show = false;
		if (PixelatorModVariables.cameras_decoded.size() >= 6 * entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page - 5) {
			show = true;
		} else {
			show = false;
		}
		return show;
	}
}