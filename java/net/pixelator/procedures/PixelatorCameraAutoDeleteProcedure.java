package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;
import net.pixelator.PixelatorMod;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
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
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.unbounded").getString())), false);
			} else {
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
				{
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + 1,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server));
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
				PixelatorModVariables.MapVariables.get(world).to_update_cams = PixelatorModVariables.MapVariables.get(world).to_update_cams.replace((x + 0.5) + "+" + y + "+" + (z + 0.5) + ",", "");
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(PixelatorModItems.CABLE.get()));
					entityToSpawn.setPickUpDelay(0);
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

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}