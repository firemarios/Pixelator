package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class PixelatorTeleportNextBtnProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		boolean show = false;
		if (PixelatorModVariables.MapVariables.get(world).teleport_max_pages == entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page) {
			show = false;
		} else {
			show = true;
		}
		return show;
	}
}