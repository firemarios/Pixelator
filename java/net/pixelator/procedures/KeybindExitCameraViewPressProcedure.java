package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.entity.Entity;

public class KeybindExitCameraViewPressProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.shift_press = true;
				_playerVars.markSyncDirty();
			}
		}
	}
}