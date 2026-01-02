package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class TeleportationMenuNextPageProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page == PixelatorModVariables.MapVariables.get(world).teleport_max_pages)) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.teleport_page = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleport_page + 1;
					capability.markSyncDirty();
				});
			}
		}
	}
}