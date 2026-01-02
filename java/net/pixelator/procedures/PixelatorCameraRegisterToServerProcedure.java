package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModMenus;
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
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import java.util.regex.Pattern;
import java.util.concurrent.atomic.AtomicReference;

public class PixelatorCameraRegisterToServerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.cameraboundname = (entity instanceof Player _entity0 && _entity0.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "cameraboundname", "") : "";
				capability.markSyncDirty();
			});
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if ((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname).length() != 0) {
			if (!entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname.contains(" ")
					|| !entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname.contains(",")
					|| !entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname.contains("_")
					|| !entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname.contains("+")) {
				if (!(executeCommandGetResult(world, new Vec3(x, y, z),
						("execute if entity @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_network + "_"
								+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname + "]")))
						.contains("passed")) {
					if (!(getEntityGameType(entity) == GameType.CREATIVE)) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(PixelatorModItems.CABLE.get());
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
						}
					}
					if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_face == Direction.NORTH) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[180f,0f]}");
					}
					if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_face == Direction.SOUTH) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[0f,0f]}");
					}
					if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_face == Direction.WEST) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[90f,0f]}");
					}
					if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_face == Direction.EAST) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[270f,0f]}");
					}
					PixelatorMod.queueServerWork(10, () -> {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("tag @e[type=interaction,sort=nearest,limit=1] add " + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_network + "_"
											+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cameraboundname));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "tag @e[type=interaction,sort=nearest,limit=1] add private");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
													(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("tag @e[type=interaction,sort=nearest,limit=1] add " + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_pos));
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands()
										.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
												new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
														(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
														(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
												Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "tag @e[type=interaction,sort=nearest,limit=1] add left");
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands()
										.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
												new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
														(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
														(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
												Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "tag @e[type=interaction,sort=nearest,limit=1] add right");
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"forceload add ~ ~");
						{
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
								capability.index_teleport = 0;
								capability.markSyncDirty();
							});
						}
						{
							String[] _array = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_pos.split(Pattern.quote(","));
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
								String stringiterator = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).itemstack_server_bound_pos;
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
						if ((itemFromBlockInventory(world,
								BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server),
								27).copy()).getItem() == PixelatorModItems.USB.get()) {
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
													"cams") + 1));
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							{
								BlockEntity _ent = world.getBlockEntity(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server));
								if (_ent != null) {
									final int _slotid = 27;
									_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
										if (capability instanceof IItemHandlerModifiable)
											((IItemHandlerModifiable) capability).setStackInSlot(_slotid, ItemStack.EMPTY);
									});
								}
							}
							PixelatorMod.queueServerWork(1, () -> {
								{
									BlockEntity _ent = world.getBlockEntity(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server));
									if (_ent != null) {
										final int _slotid = 27;
										final ItemStack _setstack = new ItemStack(PixelatorModItems.USB.get()).copy();
										_setstack.setCount(1);
										_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
											if (capability instanceof IItemHandlerModifiable)
												((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
										});
									}
								}
							});
						}
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.server.bounded").getString())), false);
						PixelatorMod.queueServerWork(10, () -> {
							{
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
									capability.sfound = false;
									capability.sx = x - 8;
									capability.markSyncDirty();
								});
							}
							for (int index0 = 0; index0 < 16; index0++) {
								{
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
										capability.sy = y - 8;
										capability.markSyncDirty();
									});
								}
								for (int index1 = 0; index1 < 16; index1++) {
									{
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
											capability.sz = z - 8;
											capability.markSyncDirty();
										});
									}
									for (int index2 = 0; index2 < 16; index2++) {
										if ((world.getBlockState(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sx,
												entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sy,
												entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sz))).getBlock() == PixelatorModBlocks.ROUTER_ACTIVATED.get()) {
											{
												entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
													capability.sfound = true;
													capability.markSyncDirty();
												});
											}
										}
										{
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
												capability.sz = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sz + 1;
												capability.markSyncDirty();
											});
										}
									}
									{
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
											capability.sy = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sy + 1;
											capability.markSyncDirty();
										});
									}
								}
								{
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
										capability.sx = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sx + 1;
										capability.markSyncDirty();
									});
								}
							}
							if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).sfound == true) {
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands()
											.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
													new Vec3((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_x + 0.5),
															(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_y + 0.5),
															(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).cam_z + 0.5)),
													Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), ("tag @e[type=interaction,sort=nearest,limit=1] add " + "netcon"));
								if (entity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.network").getString())), false);
							}
						});
					});
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.error.exist").getString())), false);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.error.char").getString())), false);
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

	private static ItemStack itemFromBlockInventory(LevelAccessor level, BlockPos pos, int slot) {
		AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> result.set(capability.getStackInSlot(slot)));
		return result.get();
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}
}