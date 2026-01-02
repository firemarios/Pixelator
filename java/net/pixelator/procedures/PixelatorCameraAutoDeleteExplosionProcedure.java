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
			} else {
				PixelatorModVariables.MapVariables.get(world).index_auto_delete = 0;
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
				{
					String[] _array = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24)).split(Pattern.quote(","));
					if (_array.length != 0) {
						for (String stringiterator : _array) {
							if (stringiterator.contains("+")) {
								PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete = stringiterator;
								PixelatorModVariables.MapVariables.get(world).markSyncDirty();
							}
						}
					} else {
						String stringiterator = ((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1.8] list")).substring(24));
						for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
							if (stringiterator.contains("+")) {
								PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete = stringiterator;
								PixelatorModVariables.MapVariables.get(world).markSyncDirty();
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
								PixelatorModVariables.MapVariables.get(world).markSyncDirty();
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
								PixelatorModVariables.MapVariables.get(world).markSyncDirty();
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
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putDouble("cams", (getBlockNBTNumber(world, BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete,
								PixelatorModVariables.MapVariables.get(world).y_server_auto_delete, PixelatorModVariables.MapVariables.get(world).z_server_auto_delete), "cams") - 1));
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				text_search = x + "+" + y + "+" + z;
				text_computer = getBlockNBTString(world, BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
						PixelatorModVariables.MapVariables.get(world).z_server_auto_delete), "cams");
				{
					String[] _array = text_computer.split(Pattern.quote(","));
					if (_array.length != 0) {
						for (String stringiterator : _array) {
							if (stringiterator.contains(text_search)) {
								if (!world.isClientSide()) {
									BlockPos _bp = BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
											PixelatorModVariables.MapVariables.get(world).z_server_auto_delete);
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
									BlockPos _bp = BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
											PixelatorModVariables.MapVariables.get(world).z_server_auto_delete);
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
					BlockEntity _ent = world.getBlockEntity(BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
							PixelatorModVariables.MapVariables.get(world).z_server_auto_delete));
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
						BlockEntity _ent = world.getBlockEntity(BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
								PixelatorModVariables.MapVariables.get(world).z_server_auto_delete));
						if (_ent != null) {
							final int _slotid = 0;
							final int _amount = 1;
							_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
								if (capability instanceof IItemHandlerModifiable) {
									ItemStack _stk = capability.getStackInSlot(_slotid).copy();
									_stk.shrink(_amount);
									((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
								}
							});
						}
					}
				});
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

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}
}