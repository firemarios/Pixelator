package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.regex.Pattern;
import java.util.ArrayList;

public class PixelatorCameraAutoDeleteExplosionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String delete_text = "";
		double index = 0;
		if ((new Object() {
			public String getResult(LevelAccessor world, Vec3 pos, String _command) {
				StringBuilder _result = new StringBuilder();
				if (world instanceof ServerLevel _level) {
					CommandSource _dataConsumer = new CommandSource() {
						@Override
						public void sendSystemMessage(Component message) {
							_result.append(message.getString());
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
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
				}
				return _result.toString();
			}
		}.getResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..1]")).contains("passed")) {
			if (!((new Object() {
				public String getResult(LevelAccessor world, Vec3 pos, String _command) {
					StringBuilder _result = new StringBuilder();
					if (world instanceof ServerLevel _level) {
						CommandSource _dataConsumer = new CommandSource() {
							@Override
							public void sendSystemMessage(Component message) {
								_result.append(message.getString());
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
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
					}
					return _result.toString();
				}
			}.getResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1] list")).contains("private"))) {
				index = 0;
				delete_text = ((((((new Object() {
					public String getResult(LevelAccessor world, Vec3 pos, String _command) {
						StringBuilder _result = new StringBuilder();
						if (world instanceof ServerLevel _level) {
							CommandSource _dataConsumer = new CommandSource() {
								@Override
								public void sendSystemMessage(Component message) {
									_result.append(message.getString());
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
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
						}
						return _result.toString();
					}
				}.getResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1,limit=1] list")).substring(24)).replace("right", "")).replace("left", "")).replace("netcon", "")).replace(" ", "")).replace(",", "");
				for (int index0 = 0; index0 < (int) PixelatorModVariables.cameras_decoded.size(); index0++) {
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
				PixelatorModVariables.MapVariables.get(world).syncData(world);
				{
					String[] _array = ((new Object() {
						public String getResult(LevelAccessor world, Vec3 pos, String _command) {
							StringBuilder _result = new StringBuilder();
							if (world instanceof ServerLevel _level) {
								CommandSource _dataConsumer = new CommandSource() {
									@Override
									public void sendSystemMessage(Component message) {
										_result.append(message.getString());
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
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
							}
							return _result.toString();
						}
					}.getResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24)).split(Pattern.quote(","));
					if (_array.length != 0) {
						for (String stringiterator : _array) {
							if (stringiterator.contains("+")) {
								PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete = stringiterator;
								PixelatorModVariables.MapVariables.get(world).syncData(world);
							}
						}
					} else {
						String stringiterator = ((new Object() {
							public String getResult(LevelAccessor world, Vec3 pos, String _command) {
								StringBuilder _result = new StringBuilder();
								if (world instanceof ServerLevel _level) {
									CommandSource _dataConsumer = new CommandSource() {
										@Override
										public void sendSystemMessage(Component message) {
											_result.append(message.getString());
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
									_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
								}
								return _result.toString();
							}
						}.getResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24));
						for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
							if (stringiterator.contains("+")) {
								PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete = stringiterator;
								PixelatorModVariables.MapVariables.get(world).syncData(world);
							}
						}
					}
				}
				{
					String[] _array = PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete.split(Pattern.quote("+"));
					if (_array.length != 0) {
						for (String stringiterator : _array) {
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
								PixelatorModVariables.MapVariables.get(world).syncData(world);
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
								PixelatorModVariables.MapVariables.get(world).syncData(world);
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
								PixelatorModVariables.MapVariables.get(world).syncData(world);
							}
							PixelatorModVariables.MapVariables.get(world).index_auto_delete++;
						}
					} else {
						String stringiterator = PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete;
						for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
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
								PixelatorModVariables.MapVariables.get(world).syncData(world);
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
								PixelatorModVariables.MapVariables.get(world).syncData(world);
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
								PixelatorModVariables.MapVariables.get(world).syncData(world);
							}
							PixelatorModVariables.MapVariables.get(world).index_auto_delete++;
						}
					}
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
							PixelatorModVariables.MapVariables.get(world).z_server_auto_delete);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putDouble("cams", ((new Object() {
							public double getValue(LevelAccessor world, BlockPos pos, String tag) {
								BlockEntity blockEntity = world.getBlockEntity(pos);
								if (blockEntity != null)
									return blockEntity.getPersistentData().getDouble(tag);
								return -1;
							}
						}.getValue(world, BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
								PixelatorModVariables.MapVariables.get(world).z_server_auto_delete), "cams")) - 1));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(PixelatorModItems.CABLE.get()));
					entityToSpawn.setPickUpDelay(0);
					_level.addFreshEntity(entityToSpawn);
				}
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"kill @e[type=interaction,distance=..1]");
			}
		}
	}
}