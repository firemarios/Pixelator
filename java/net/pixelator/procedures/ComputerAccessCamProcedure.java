package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
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
		double tp_x = 0;
		double tp_y = 0;
		double tp_z = 0;
		double index = 0;
		index = 1;
		request_pos = ComputerGetCamFromIndexProcedure.execute(world, x, y, z, entity);
		{
			String[] _array = request_pos.split(Pattern.quote("+"));
			if (_array.length != 0) {
				for (String stringiterator : _array) {
					if (index == 1) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.computer_tp_x = new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(stringiterator) + 0.5;
								capability.markSyncDirty();
							});
						}
					} else if (index == 2) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.computer_tp_y = new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(stringiterator) - 1.3;
								capability.markSyncDirty();
							});
						}
					} else if (index == 3) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.computer_tp_z = new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(stringiterator) + 0.5;
								capability.markSyncDirty();
							});
						}
					}
					index++;
				}
			} else {
				String stringiterator = request_pos;
				for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
					if (index == 1) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.computer_tp_x = new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(stringiterator) + 0.5;
								capability.markSyncDirty();
							});
						}
					} else if (index == 2) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.computer_tp_y = new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(stringiterator) - 1.3;
								capability.markSyncDirty();
							});
						}
					} else if (index == 3) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.computer_tp_z = new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(stringiterator) + 0.5;
								capability.markSyncDirty();
							});
						}
					}
					index++;
				}
			}
		}
		tp_x = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x;
		tp_y = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y;
		tp_z = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z;
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.viewing_cam = true;
				capability.markSyncDirty();
			});
		}
		if (getEntityGameType(entity) == GameType.SURVIVAL) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.stored_gamemode = "survival";
					capability.markSyncDirty();
				});
			}
		} else if (getEntityGameType(entity) == GameType.CREATIVE) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.stored_gamemode = "creative";
					capability.markSyncDirty();
				});
			}
		} else if (getEntityGameType(entity) == GameType.ADVENTURE) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.stored_gamemode = "adventure";
					capability.markSyncDirty();
				});
			}
		}
		if (entity instanceof ServerPlayer _player)
			_player.setGameMode(GameType.SPECTATOR);
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.stored_location = entity.getX() + "+" + entity.getY() + "+" + entity.getZ();
				capability.computer_tp_view_x = 30;
				capability.markSyncDirty();
			});
		}
		if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.NORTH) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.computer_tp_view_y = 180;
					capability.markSyncDirty();
				});
			}
		} else if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.SOUTH) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.computer_tp_view_y = 0;
					capability.markSyncDirty();
				});
			}
		} else if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.WEST) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.computer_tp_view_y = 90;
					capability.markSyncDirty();
				});
			}
		} else if ((getDirectionFromBlockState((world.getBlockState(BlockPos.containing(tp_x, tp_y + 1.3, tp_z))))) == Direction.EAST) {
			{
				entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.computer_tp_view_y = -90;
					capability.markSyncDirty();
				});
			}
		}
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.computer_max_l = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y - 60;
				capability.computer_max_r = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_view_y + 60;
				capability.markSyncDirty();
			});
		}
		{
			Entity _ent = entity;
			if (!_ent.level().isClientSide() && _ent.getServer() != null) {
				_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
						_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), ("tp @s " + tp_x + " " + tp_y + " " + tp_z));
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
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}
}