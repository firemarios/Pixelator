package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

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
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.itemstack_server_bound_network = "";
				capability.markSyncDirty();
			});
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.SERVER.get()) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 5);
			if (!(getBlockNBTString(world, BlockPos.containing(x, y, z), "network_name")).isEmpty()) {
				itemstack.getOrCreateTag().putString("network_name", (getBlockNBTString(world, BlockPos.containing(x, y, z), "network_name")));
				itemstack.getOrCreateTag().putString("server_pos", (x + "+" + y + "+" + z));
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.note_block.bell")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.note_block.bell")), SoundSource.PLAYERS, 1, 1, false);
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
			if (!(executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..0.5")).contains("passed")) {
				if (!(itemstack.getOrCreateTag().getString("network_name")).isEmpty()) {
					{
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
							capability.itemstack_server_bound_network = itemstack.getOrCreateTag().getString("network_name");
							capability.itemstack_server_bound_pos = itemstack.getOrCreateTag().getString("server_pos");
							capability.markSyncDirty();
						});
					}
					itemstack.getOrCreateTag().putString("network_name", "");
					{
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
							capability.cam_x = x;
							capability.cam_y = y;
							capability.cam_z = z;
							capability.cam_face = getDirectionFromBlockState(blockstate);
							capability.markSyncDirty();
						});
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
				if ((executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ as @e[type=minecraft:interaction,distance=..0.5] at @s if entity @s[tag=private]")).contains("passed")) {
					{
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
							capability.itemstack_server_bound_network = itemstack.getOrCreateTag().getString("network_name");
							capability.markSyncDirty();
						});
					}
					itemstack.getOrCreateTag().putString("network_name", "");
					if ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list"))
							.contains(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_network)) {
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.index_teleport = 0;
								capability.markSyncDirty();
							});
						}
						{
							String[] _array = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24)).split(Pattern.quote(","));
							if (_array.length != 0) {
								for (String stringiterator : _array) {
									if (stringiterator.contains("+")) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.server_pos = stringiterator;
												capability.markSyncDirty();
											});
										}
									}
								}
							} else {
								String stringiterator = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24));
								for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
									if (stringiterator.contains("+")) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.server_pos = stringiterator;
												capability.markSyncDirty();
											});
										}
									}
								}
							}
						}
						{
							String[] _array = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).server_pos.split(Pattern.quote("+"));
							if (_array.length != 0) {
								for (String stringiterator : _array) {
									if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 0) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.x_server = new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(stringiterator);
												capability.markSyncDirty();
											});
										}
									} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 1) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.y_server = new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(stringiterator);
												capability.markSyncDirty();
											});
										}
									} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 2) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.z_server = new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(stringiterator);
												capability.markSyncDirty();
											});
										}
									}
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport++;
								}
							} else {
								String stringiterator = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).server_pos;
								for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
									if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 0) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.x_server = new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(stringiterator);
												capability.markSyncDirty();
											});
										}
									} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 1) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.y_server = new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(stringiterator);
												capability.markSyncDirty();
											});
										}
									} else if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport == 2) {
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.z_server = new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(stringiterator);
												capability.markSyncDirty();
											});
										}
									}
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).index_teleport++;
								}
							}
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("cams",
										(getBlockNBTNumber(world,
												BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
														entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
														entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server),
												"cams") - 1));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						text_search = x + "+" + y + "+" + z;
						text_computer = getBlockNBTString(world,
								BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + 1,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server),
								"cams");
						{
							String[] _array = text_computer.split(Pattern.quote(","));
							if (_array.length != 0) {
								for (String stringiterator : _array) {
									if (stringiterator.contains(text_search)) {
										if (!world.isClientSide()) {
											BlockPos _bp = BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
													entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + 1,
													entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server);
											BlockEntity _blockEntity = world.getBlockEntity(_bp);
											BlockState _bs = world.getBlockState(_bp);
											if (_blockEntity != null) {
												_blockEntity.getPersistentData().putString("cams", (text_computer.replace(text_search + ",", "")));
											}
											if (world instanceof Level _level)
												_level.sendBlockUpdated(_bp, _bs, _bs, 3);
										}
									}
								}
							} else {
								String stringiterator = text_computer;
								for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
									if (stringiterator.contains(text_search)) {
										if (!world.isClientSide()) {
											BlockPos _bp = BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
													entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + 1,
													entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server);
											BlockEntity _blockEntity = world.getBlockEntity(_bp);
											BlockState _bs = world.getBlockState(_bp);
											if (_blockEntity != null) {
												_blockEntity.getPersistentData().putString("cams", (text_computer.replace(text_search + ",", "")));
											}
											if (world instanceof Level _level)
												_level.sendBlockUpdated(_bp, _bs, _bs, 3);
										}
									}
								}
							}
						}
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
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}