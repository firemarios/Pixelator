package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class PixelatorTeleportCam2TextVProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		boolean show = false;
		if (PixelatorModVariables.cameras_decoded.size() >= 6 * (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_page - 4) {
			show = true;
		} else {
			show = false;
		}
		return show;
	}
}