package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class ComputerCam3PressProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.request_index = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_page * 4 - 1;
				capability.markSyncDirty();
			});
		}
		ComputerAccessCamProcedure.execute(world, x, y, z, entity);
	}
}