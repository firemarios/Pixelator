package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class PixelatorTeleportPreviousBtnProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		boolean show = false;
		if (1 == (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleport_page) {
			show = false;
		} else {
			show = true;
		}
		return show;
	}
}