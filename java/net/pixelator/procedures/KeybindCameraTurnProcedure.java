package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModKeyMappings;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class KeybindCameraTurnProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.isClientSide()) {
			if (PixelatorModKeyMappings.CAMERA_TURN_LEFT.isDown() && !(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y == entity
					.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_max_l)) {
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.computer_tp_view_y = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y - 2;
						_playerVars.markSyncDirty();
					}
				}
			}
			if (PixelatorModKeyMappings.CAMERA_TURN_RIGHT.isDown() && !(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y == entity
					.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_max_r)) {
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.computer_tp_view_y = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y + 2;
						_playerVars.markSyncDirty();
					}
				}
			}
			if (PixelatorModKeyMappings.CAMERA_TURN_UP.isDown() && !(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_x == 0)) {
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.computer_tp_view_x = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_x - 2;
						_playerVars.markSyncDirty();
					}
				}
			}
			if (PixelatorModKeyMappings.CAMERA_TURN_DOWN.isDown() && !(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_x == 70)) {
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.computer_tp_view_x = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_x + 2;
						_playerVars.markSyncDirty();
					}
				}
			}
		}
	}
}