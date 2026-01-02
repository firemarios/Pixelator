package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class ComputerNextPressProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.computer_page = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_page + 1;
				capability.markSyncDirty();
			});
		}
	}
}