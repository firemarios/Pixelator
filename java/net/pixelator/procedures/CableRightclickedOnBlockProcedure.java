package net.pixelator.procedures;

import net.pixelator.world.inventory.CameraBoundToServerMenu;
import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;
import net.pixelator.init.PixelatorModBlocks;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.regex.Pattern;

import io.netty.buffer.Unpooled;

public class CableRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		String text_search = "";
		String text_computer = "";
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.itemstack_server_bound_network = "";
				_playerVars.markSyncDirty();
			}
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.SERVER.get()) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 5);
			if (!(getBlockNBTString(world, BlockPos.containing(x, y, z), "network_name")).isEmpty()) {
				itemstack.getOrCreateTag().putString("network_name", (getBlockNBTString(world, BlockPos.containing(x, y, z), "network_name")));
				itemstack.getOrCreateTag().putString("server_pos", (x + "+" + y + "+" + z + "+" + ((Level) world).dimension().location().toString().replace(":", "")));
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bell")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bell")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.cable.connected_server").getString())), true);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.cable.error.config_server_network").getString())), false);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()
				|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 5);
			if (!(executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..1]")).contains("passed")) {
				if (!(itemstack.getOrCreateTag().getString("network_name")).isEmpty()) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.itemstack_server_bound_network = itemstack.getOrCreateTag().getString("network_name");
							_playerVars.itemstack_server_bound_pos = itemstack.getOrCreateTag().getString("server_pos");
							_playerVars.markSyncDirty();
						}
					}
					itemstack.getOrCreateTag().putString("network_name", "");
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.cam_x = x;
							_playerVars.cam_y = y;
							_playerVars.cam_z = z;
							_playerVars.cam_face = getDirectionFromBlockState(blockstate);
							_playerVars.markSyncDirty();
						}
					}
					if (entity instanceof ServerPlayer _ent) {
						BlockPos _bpos = BlockPos.containing(x, y, z);
						NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
							@Override
							public Component getDisplayName() {
								return Component.literal("CameraBoundToServer");
							}

							@Override
							public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
								return new CameraBoundToServerMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
							}
						}, _bpos);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.cable.error.click_on_server_first").getString())), false);
				}
			} else {
				if ((executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ as @e[type=minecraft:interaction,distance=..1] at @s if entity @s[tag=private]")).contains("passed")) {
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.itemstack_server_bound_network = itemstack.getOrCreateTag().getString("network_name");
							_playerVars.markSyncDirty();
						}
					}
					itemstack.getOrCreateTag().putString("network_name", "");
					if ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list"))
							.contains(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_network)) {
						{
							var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
							if (_playerVars != null) {
								_playerVars.index_teleport = 0;
								_playerVars.markSyncDirty();
							}
						}
						String _splitContent45 = Pattern.quote(",");
						String _toSplit45 = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24));
						String[] _array45 = _toSplit45.split(_splitContent45);
						if (_array45.length != 0) {
							for (String stringiterator : _array45) {
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
							String stringiterator = _toSplit45;
							for (int _yourmother45 = 0; _yourmother45 < 1; _yourmother45++) {
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
						String _splitContent51 = Pattern.quote("+");
						String _toSplit51 = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).server_pos;
						String[] _array51 = _toSplit51.split(_splitContent51);
						if (_array51.length != 0) {
							for (String stringiterator : _array51) {
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
							String stringiterator = _toSplit51;
							for (int _yourmother51 = 0; _yourmother51 < 1; _yourmother51++) {
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
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "kill @e[type=interaction,distance=..1]");
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
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
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