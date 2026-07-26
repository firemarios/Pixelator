package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import java.util.regex.Pattern;

public class ComputerAccessCamProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String request_pos = "";
		String tp_world = "";
		double tp_x = 0;
		double tp_y = 0;
		double tp_z = 0;
		double index = 0;
		index = 1;
		request_pos = ComputerGetCamFromIndexProcedure.execute(world, x, y, z, entity);
		String _splitContent5 = Pattern.quote("+");
		String _toSplit5 = request_pos;
		String[] _array5 = _toSplit5.split(_splitContent5);
		if (_array5.length != 0) {
			for (String stringiterator : _array5) {
				if (index == 1) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.computer_tp_x = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							_playerVars.markSyncDirty();
						}
					}
				} else if (index == 2) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.computer_tp_y = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							_playerVars.markSyncDirty();
						}
					}
				} else if (index == 3) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.computer_tp_z = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							_playerVars.markSyncDirty();
						}
					}
				} else if (index == 4) {
					tp_world = stringiterator;
				}
				index++;
			}
		} else {
			String stringiterator = _toSplit5;
			for (int _yourmother5 = 0; _yourmother5 < 1; _yourmother5++) {
				if (index == 1) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.computer_tp_x = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							_playerVars.markSyncDirty();
						}
					}
				} else if (index == 2) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.computer_tp_y = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							_playerVars.markSyncDirty();
						}
					}
				} else if (index == 3) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.computer_tp_z = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							_playerVars.markSyncDirty();
						}
					}
				} else if (index == 4) {
					tp_world = stringiterator;
				}
				index++;
			}
		}
		if ((tp_world).isEmpty()) {
			tp_world = TmpConvertOldVideoCamerasToNewProcedure.execute(world, x, y, z, entity);
		}
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.stored_world = tp_world;
				_playerVars.markSyncDirty();
			}
		}
		tp_x = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x + 0.5;
		tp_y = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y - 1.3;
		tp_z = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z + 0.5;
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.computer_tp_x = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x + 0.5;
				_playerVars.computer_tp_y = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y - 1.3;
				_playerVars.computer_tp_z = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z + 0.5;
				_playerVars.markSyncDirty();
			}
		}
		if (getEntityGameType(entity) == GameType.SURVIVAL) {
			{
				var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
				if (_playerVars != null) {
					_playerVars.stored_gamemode = "survival";
					_playerVars.markSyncDirty();
				}
			}
		} else if (getEntityGameType(entity) == GameType.CREATIVE) {
			{
				var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
				if (_playerVars != null) {
					_playerVars.stored_gamemode = "creative";
					_playerVars.markSyncDirty();
				}
			}
		} else if (getEntityGameType(entity) == GameType.ADVENTURE) {
			{
				var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
				if (_playerVars != null) {
					_playerVars.stored_gamemode = "adventure";
					_playerVars.markSyncDirty();
				}
			}
		}
		if (entity instanceof ServerPlayer _player)
			_player.setGameMode(GameType.SPECTATOR);
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.stored_location = entity.getX() + "+" + entity.getY() + "+" + entity.getZ() + "+" + ((Level) world).dimension().location().toString().replace(":", "");
				_playerVars.computer_tp_view_x = 30;
				_playerVars.markSyncDirty();
			}
		}
		if (world.getServer() != null) {
			LevelAccessor _origWorld = world;
			for (ServerLevel worlditerator : world.getServer().getAllLevels()) {
				world = worlditerator;
				if ((tp_world).equals(((Level) world).dimension().location().toString().replace(":", ""))) {
					if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.NORTH) {
						{
							var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
							if (_playerVars != null) {
								_playerVars.computer_tp_view_y = 180;
								_playerVars.markSyncDirty();
							}
						}
					} else if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.SOUTH) {
						{
							var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
							if (_playerVars != null) {
								_playerVars.computer_tp_view_y = 0;
								_playerVars.markSyncDirty();
							}
						}
					} else if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.WEST) {
						{
							var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
							if (_playerVars != null) {
								_playerVars.computer_tp_view_y = 90;
								_playerVars.markSyncDirty();
							}
						}
					} else if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.EAST) {
						{
							var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
							if (_playerVars != null) {
								_playerVars.computer_tp_view_y = -90;
								_playerVars.markSyncDirty();
							}
						}
					}
				}
			}
			world = _origWorld;
		}
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.computer_max_l = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y - 60;
				_playerVars.computer_max_r = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y + 60;
				_playerVars.markSyncDirty();
			}
		}
		if (world.getServer() != null) {
			LevelAccessor _origWorld = world;
			for (ServerLevel worlditerator : world.getServer().getAllLevels()) {
				world = worlditerator;
				if ((tp_world).equals(((Level) world).dimension().location().toString().replace(":", ""))) {
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("execute in " + ((Level) world).dimension().location().toString() + " run tp @s " + tp_x + " " + tp_y + " " + tp_z));
						}
					}
				}
			}
			world = _origWorld;
		}
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.viewing_cam = true;
				_playerVars.markSyncDirty();
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = getPropertyByName(blockState, "facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = getPropertyByName(blockState, "axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}