package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class ComputerCam3LProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "Cam" + Math.round(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_page * 4 - 1);
	}
}