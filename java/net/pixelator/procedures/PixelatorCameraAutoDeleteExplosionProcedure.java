package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;
import net.pixelator.PixelatorMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.regex.Pattern;
import java.util.ArrayList;

public class PixelatorCameraAutoDeleteExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double index = 0;
		String delete_text = "";
		String text_computer = "";
		String text_search = "";
		if ((executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..1]")).contains("passed")) {
			if (!(executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1] list")).contains("private")) {
				index = 0;
				delete_text = ((((((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1,limit=1] list")).substring(24)).replace("right", "")).replace("left", "")).replace("netcon", "")).replace(" ", ""))
						.replace(",", "");
				for (int index958 = 0; index958 < (int) PixelatorModVariables.cameras_decoded.size(); index958++) {
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
			} else {
				PixelatorModVariables.MapVariables.get(world).index_auto_delete = 0;
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
				String _splitContent12 = Pattern.quote(",");
				String _toSplit12 = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24));
				String[] _array12 = _toSplit12.split(_splitContent12);
				if (_array12.length != 0) {
					for (String stringiterator : _array12) {
						if (stringiterator.contains("+")) {
							PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete = stringiterator;
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
						}
					}
				} else {
					String stringiterator = _toSplit12;
					for (int _yourmother12 = 0; _yourmother12 < 1; _yourmother12++) {
						if (stringiterator.contains("+")) {
							PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete = stringiterator;
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
						}
					}
				}
				String _splitContent18 = Pattern.quote("+");
				String _toSplit18 = PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete;
				String[] _array18 = _toSplit18.split(_splitContent18);
				if (_array18.length != 0) {
					for (String stringiterator : _array18) {
						if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 0) {
							PixelatorModVariables.MapVariables.get(world).x_server_auto_delete = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							PixelatorModVariables.world_server_auto_delete = "";
						} else if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 1) {
							PixelatorModVariables.MapVariables.get(world).y_server_auto_delete = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							PixelatorModVariables.world_server_auto_delete = "";
						} else if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 2) {
							PixelatorModVariables.MapVariables.get(world).z_server_auto_delete = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							PixelatorModVariables.world_server_auto_delete = "";
						} else if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 3) {
							PixelatorModVariables.world_server_auto_delete = stringiterator;
						}
						PixelatorModVariables.MapVariables.get(world).index_auto_delete++;
					}
				} else {
					String stringiterator = _toSplit18;
					for (int _yourmother18 = 0; _yourmother18 < 1; _yourmother18++) {
						if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 0) {
							PixelatorModVariables.MapVariables.get(world).x_server_auto_delete = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							PixelatorModVariables.world_server_auto_delete = "";
						} else if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 1) {
							PixelatorModVariables.MapVariables.get(world).y_server_auto_delete = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							PixelatorModVariables.world_server_auto_delete = "";
						} else if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 2) {
							PixelatorModVariables.MapVariables.get(world).z_server_auto_delete = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(stringiterator);
							PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							PixelatorModVariables.world_server_auto_delete = "";
						} else if (PixelatorModVariables.MapVariables.get(world).index_auto_delete == 3) {
							PixelatorModVariables.world_server_auto_delete = stringiterator;
						}
						PixelatorModVariables.MapVariables.get(world).index_auto_delete++;
					}
				}
				if (TmpConvertOldPrivateCamerasToNewAutoProcedure.execute(world, x, y, z)) {
					if (UnregisterFromServerDescreseNumberOfCamerasAutoProcedure.execute(world, x, y, z)) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(PixelatorModItems.CABLE.get()));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"kill @e[type=interaction,distance=..1]");
					} else {
						PixelatorMod.LOGGER.error((Component.translatable("msg.pixelator.general.error").getString()));
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