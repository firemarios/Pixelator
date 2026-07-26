package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class CommandCamerasClearProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		PixelatorModVariables.MapVariables.get(world).cameras = "";
		PixelatorModVariables.MapVariables.get(world).markSyncDirty();
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("Clearing..."), false);
		PixelatorCameraListDecoderProcedure.execute(world);
		PixelatorMod.queueServerWork(20, () -> {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Cleared"), false);
			PixelatorCameraListEncoderProcedure.execute(world);
		});
	}
}