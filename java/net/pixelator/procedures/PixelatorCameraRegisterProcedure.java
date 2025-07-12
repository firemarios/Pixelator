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
			PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
			_vars.cameraboundname = (entity instanceof Player _entity && _entity.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu) ? _menu.getMenuState(0, "cameraboundname", "") : "";
			_vars.syncPlayerVariables(entity);
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if ((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cameraboundname).length() != 0) {
			if (!entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cameraboundname.contains(" ")) {
				if (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_face == Direction.NORTH) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
												(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[180f,0f]}");
				}
				if (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_face == Direction.SOUTH) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
												(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[0f,0f]}");
				}
				if (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_face == Direction.WEST) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
												(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[90f,0f]}");
				}
				if (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_face == Direction.EAST) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
												(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"summon minecraft:interaction ~ ~ ~ {height:0.1,width:0.1,Rotation:[270f,0f]}");
				}
				PixelatorMod.queueServerWork(10, () -> {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL,
										new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
												(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
										Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("tag @e[type=interaction,sort=nearest,limit=1] add " + entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cameraboundname));
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
													(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"tag @e[type=interaction,sort=nearest,limit=1] add left");
					} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL,
											new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
													(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
											Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"tag @e[type=interaction,sort=nearest,limit=1] add right");
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"forceload add ~ ~");
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7aCamera bounded"), false);
					PixelatorMod.queueServerWork(10, () -> {
						{
							PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
							_vars.sfound = false;
							_vars.syncPlayerVariables(entity);
						}
						{
							PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
							_vars.sx = x - 8;
							_vars.syncPlayerVariables(entity);
						}
						for (int index0 = 0; index0 < 16; index0++) {
							{
								PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
								_vars.sy = y - 8;
								_vars.syncPlayerVariables(entity);
							}
							for (int index1 = 0; index1 < 16; index1++) {
								{
									PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
									_vars.sz = z - 8;
									_vars.syncPlayerVariables(entity);
								}
								for (int index2 = 0; index2 < 16; index2++) {
									if ((world.getBlockState(
											BlockPos.containing(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sx, entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sy, entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sz)))
											.getBlock() == PixelatorModBlocks.ROUTER_ACTIVATED.get()) {
										{
											PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
											_vars.sfound = true;
											_vars.syncPlayerVariables(entity);
										}
									}
									{
										PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
										_vars.sz = entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sz + 1;
										_vars.syncPlayerVariables(entity);
									}
								}
								{
									PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
									_vars.sy = entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sy + 1;
									_vars.syncPlayerVariables(entity);
								}
							}
							{
								PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
								_vars.sx = entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sx + 1;
								_vars.syncPlayerVariables(entity);
							}
						}
						if (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).sfound == true) {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(
										new CommandSourceStack(CommandSource.NULL,
												new Vec3((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_x + 0.5), (entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_y + 0.5),
														(entity.getData(PixelatorModVariables.PLAYER_VARIABLES).cam_z + 0.5)),
												Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										("tag @e[type=interaction,sort=nearest,limit=1] add " + "netcon"));
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