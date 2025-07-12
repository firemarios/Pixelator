package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModMenus;
import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class PixelatorCameraRegisterProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			String _setval = (entity instanceof Player _entity && _entity.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "cameraboundname", "") : "";
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.cameraboundname = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cameraboundname).length() != 0) {
			if (!((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cameraboundname).contains(" ")) {
				if (((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_face) == Direction.NORTH) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
										new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[180f,0f]}");
				}
				if (((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_face) == Direction.SOUTH) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
										new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[0f,0f]}");
				}
				if (((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_face) == Direction.WEST) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
										new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[90f,0f]}");
				}
				if (((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_face) == Direction.EAST) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands()
								.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
										new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[270f,0f]}");
				}
				PixelatorMod.queueServerWork(10, () -> {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
												((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("tag @e[type=interaction,sort=nearest,limit=1] add " + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cameraboundname));
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
													((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
													((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "tag @e[type=interaction,sort=nearest,limit=1] add left");
					} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands()
									.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
											new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
													((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
													((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "tag @e[type=interaction,sort=nearest,limit=1] add right");
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"forceload add ~ ~");
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7aCamera bounded"), false);
					PixelatorMod.queueServerWork(10, () -> {
						{
							boolean _setval = false;
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.sfound = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = x - 8;
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.sx = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						for (int index0 = 0; index0 < 16; index0++) {
							{
								double _setval = y - 8;
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.sy = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							for (int index1 = 0; index1 < 16; index1++) {
								{
									double _setval = z - 8;
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.sz = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
								for (int index2 = 0; index2 < 16; index2++) {
									if ((world.getBlockState(BlockPos.containing((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sx,
											(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sy,
											(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sz))).getBlock() == PixelatorModBlocks.ROUTER_ACTIVATED.get()) {
										{
											boolean _setval = true;
											entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
												capability.sfound = _setval;
												capability.syncPlayerVariables(entity);
											});
										}
									}
									{
										double _setval = (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sz + 1;
										entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.sz = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
								}
								{
									double _setval = (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sy + 1;
									entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.sy = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
							{
								double _setval = (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sx + 1;
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.sx = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
						}
						if ((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).sfound == true) {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands()
										.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL,
												new Vec3(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_x + 0.5),
														((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_y + 0.5),
														((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).cam_z + 0.5)),
												Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), ("tag @e[type=interaction,sort=nearest,limit=1] add " + "netcon"));
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal("\u00A7aCamera connected to network"), false);
						}
					});
				});
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cError: Do not include spaces"), false);
			}
		}
	}
}