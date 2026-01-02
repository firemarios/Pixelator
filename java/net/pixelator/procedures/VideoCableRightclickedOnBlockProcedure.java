package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;
import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import java.util.regex.Pattern;
import java.util.concurrent.atomic.AtomicReference;

public class VideoCableRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double server_x = 0;
		double server_y = 0;
		double server_z = 0;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get() || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
			if ((executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ as @e[type=minecraft:interaction,distance=..1.8] at @s if entity @s[tag=private]")).contains("passed")) {
				if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == PixelatorModItems.VIDEO_CABLE.get())) {
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
					server_x = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server;
					server_y = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + 1;
					server_z = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server;
					if (CheckComputerExistanceProcedure.execute(world, server_x, server_y, server_z, entity)) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(server_x, server_y, server_z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putString("cams", (getBlockNBTString(world, BlockPos.containing(server_x, server_y, server_z), "cams") + "" + (x + "+" + y + "+" + z) + ","));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						{
							BlockEntity _ent = world.getBlockEntity(BlockPos.containing(server_x, server_y, server_z));
							if (_ent != null) {
								final int _slotid = 0;
								final ItemStack _setstack = new ItemStack(PixelatorModItems.USB.get()).copy();
								_setstack.setCount(1);
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable)
										((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
								});
							}
						}
						if (entity instanceof Player _player)
							_player.getCooldowns().addCooldown(itemstack.getItem(), 5);
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera_module.installed").getString())), false);
						{
							BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
							if (_ent != null) {
								final int _slotid = 1;
								final ItemStack _setstack = new ItemStack(PixelatorModItems.VIDEO_CABLE.get()).copy();
								_setstack.setCount(1);
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable)
										((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
								});
							}
						}
						if (!(getEntityGameType(entity) == GameType.CREATIVE)) {
							if (entity instanceof Player _player) {
								ItemStack _stktoremove = new ItemStack(PixelatorModItems.VIDEO_CABLE.get());
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
							}
						}
						PixelatorMod.queueServerWork(1, () -> {
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + 1,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server));
								if (_ent != null) {
									final int _slotid = 0;
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, ItemStack.EMPTY);
									});
								}
							}
						});
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera_module.already_exists").getString())), false);
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

	private static ItemStack itemFromBlockInventory(LevelAccessor level, BlockPos pos, int slot) {
		AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> result.set(capability.getStackInSlot(slot)));
		return result.get();
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
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
}