package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.regex.Pattern;
import java.util.ArrayList;

public class PixelatorCameraAutoDeleteProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double index = 0;
		String delete_text = "";
		String text_computer = "";
		String text_search = "";
		if ((executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..1]")).contains("passed")) {
			if (!(executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1] list")).contains("private")) {
				index = 0;
				delete_text = ((((((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1,limit=1] list")).substring(24)).replace("right", "")).replace("left", "")).replace("netcon", "")).replace(" ", ""))
						.replace(",", "");
				for (int index956 = 0; index956 < (int) PixelatorModVariables.cameras_decoded.size(); index956++) {
					if ((/*@String*/(new Object() {
						private <E> E getListElement(ArrayList<Object> objects, int index, Class<E> eClass, Object defaultValue) {
							if (index < objects.size()) {
								var element = objects.get(index);
								if (eClass.isInstance(element)) {
									return eClass.cast(element);
								}
							}
							return eClass.cast(defaultValue);
						}
					}.getListElement(PixelatorModVariables.cameras_decoded, (int) index, String.class, ""))).equals(delete_text)) {
						PixelatorModVariables.cameras_decoded.remove((int) index);
						PixelatorCameraListEncoderProcedure.execute(world);
						break;
					} else {
						index++;
					}
				}
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"kill @e[type=interaction,distance=..1]");
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"forceload remove ~ ~");
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.unbounded").getString())), false);
			} else {
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.index_teleport = 0;
						_playerVars.markSyncDirty();
					}
				}
				String _splitContent14 = Pattern.quote(",");
				String _toSplit14 = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24));
				String[] _array14 = _toSplit14.split(_splitContent14);
				if (_array14.length != 0) {
					for (String stringiterator : _array14) {
						if (stringiterator.contains("+")) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.server_pos = stringiterator;
									_playerVars.markSyncDirty();
								}
							}
						}
					}
				} else {
					String stringiterator = _toSplit14;
					for (int _yourmother14 = 0; _yourmother14 < 1; _yourmother14++) {
						if (stringiterator.contains("+")) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.server_pos = stringiterator;
									_playerVars.markSyncDirty();
								}
							}
						}
					}
				}
				String _splitContent20 = Pattern.quote("+");
				String _toSplit20 = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).server_pos;
				String[] _array20 = _toSplit20.split(_splitContent20);
				if (_array20.length != 0) {
					for (String stringiterator : _array20) {
						if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 0) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.x_server = new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator);
									_playerVars.world_server = "";
									_playerVars.markSyncDirty();
								}
							}
						} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 1) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.y_server = new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator);
									_playerVars.world_server = "";
									_playerVars.markSyncDirty();
								}
							}
						} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 2) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.z_server = new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator);
									_playerVars.world_server = "";
									_playerVars.markSyncDirty();
								}
							}
						} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 3) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.world_server = stringiterator;
									_playerVars.markSyncDirty();
								}
							}
						}
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport++;
					}
				} else {
					String stringiterator = _toSplit20;
					for (int _yourmother20 = 0; _yourmother20 < 1; _yourmother20++) {
						if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 0) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.x_server = new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator);
									_playerVars.world_server = "";
									_playerVars.markSyncDirty();
								}
							}
						} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 1) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.y_server = new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator);
									_playerVars.world_server = "";
									_playerVars.markSyncDirty();
								}
							}
						} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 2) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.z_server = new Object() {
										double convert(String s) {
											try {
												return Double.parseDouble(s.trim());
											} catch (Exception e) {
											}
											return 0;
										}
									}.convert(stringiterator);
									_playerVars.world_server = "";
									_playerVars.markSyncDirty();
								}
							}
						} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 3) {
							{
								var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
								if (_playerVars != null) {
									_playerVars.world_server = stringiterator;
									_playerVars.markSyncDirty();
								}
							}
						}
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport++;
					}
				}
				if (TmpConvertOldPrivateCamerasToNewProcedure.execute(world, x, y, z, entity)) {
					if (UnregisterFromServerDescreseNumberOfCamerasProcedure.execute(world, x, y, z, entity)) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(PixelatorModItems.CABLE.get()));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"kill @e[type=interaction,distance=..1]");
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.unbounded").getString())), false);
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.general.error").getString())), false);
					}
				}
			}
		}
	}

	private static String executeCommandGetResult(LevelAccessor world, Vec3 pos, String command) {
		StringBuilder result = new StringBuilder();
		if (world instanceof ServerLevel level) {
			CommandSource dataConsumer = new CommandSource() {
				@Override
				public void sendSystemMessage(Component message) {
					result.append(message.getString());
				}

				@Override
				public boolean acceptsSuccess() {
					return true;
				}

				@Override
				public boolean acceptsFailure() {
					return true;
				}

				@Override
				public boolean shouldInformAdmins() {
					return false;
				}
			};
			level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(dataConsumer, pos, Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null), command);
		}
		return result.toString();
	}
}