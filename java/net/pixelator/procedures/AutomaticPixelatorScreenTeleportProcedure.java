package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.PixelatorMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class AutomaticPixelatorScreenTeleportProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String teleportscreen = "";
		PixelatorMod.queueServerWork(1, () -> {
			if (world instanceof Level _level0 && _level0.hasNeighborSignal(BlockPos.containing(x, y, z))) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_screen_activate")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_screen_activate")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				PixelatorMod.queueServerWork(20, () -> {
					PixelatorModVariables.teleportAddress = (executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,sort=nearest,limit=1] list")).substring(25);
					PixelatorMod.queueServerWork(10, () -> {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"tag @p add tpplayer");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("execute as @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress + ",sort=random] at @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress
											+ ",sort=random] if entity @s[tag=left] run tp " + "@p" + " ~ ~ ~"));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("execute as @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress + ",sort=random] at @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress
											+ ",sort=random] if entity @s[tag=right] run tp " + "@p" + " ~ ~ ~"));
						PixelatorMod.queueServerWork(1, () -> {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"execute as @a[tag=tpplayer] at @s run summon armor_stand ~ ~ ~ {NoGravity:1,Invisible:1,Tags:[\"tparmorstand\"]}");
							PixelatorModVariables.tptext1 = ((executeCommandGetResult(world, new Vec3(x, y, z), "execute as @e[tag=tparmorstand] at @s run tp @s ~ ~ ~")).substring(26)).replaceAll(",", "");
							PixelatorModVariables.tpx = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(PixelatorModVariables.tptext1.substring(0, PixelatorModVariables.tptext1.indexOf(" ", 0) - 1)) - 0.5;
							PixelatorModVariables.tptext2 = PixelatorModVariables.tptext1.substring(PixelatorModVariables.tptext1.indexOf(" ", 0) + 1);
							PixelatorModVariables.tpy = Math.round(new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(PixelatorModVariables.tptext2.substring(0, PixelatorModVariables.tptext2.indexOf(" ", 0) - 1)) - 0.5);
							PixelatorModVariables.tptext3 = PixelatorModVariables.tptext2.substring(PixelatorModVariables.tptext2.indexOf(" ", 0) + 1);
							PixelatorModVariables.tpz = new Object() {
								double convert(String s) {
									try {
										return Double.parseDouble(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(PixelatorModVariables.tptext3) - 0.5;
							PixelatorCameraSpawnAutomaticProcedure.execute(world, PixelatorModVariables.tpx, PixelatorModVariables.tpy, PixelatorModVariables.tpz);
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										("execute as @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress + ",sort=random] at @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress
												+ ",sort=random] if entity @s[tag=left] run tp " + "@p" + " ^-1 ^-2.5 ^0.3"));
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										("execute as @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress + ",sort=random] at @e[type=minecraft:interaction,tag=" + PixelatorModVariables.teleportAddress
												+ ",sort=random] if entity @s[tag=right] run tp " + "@p" + " ^1 ^-2.5 ^0.3"));
							PixelatorMod.queueServerWork(1, () -> {
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											"kill @e[type=armor_stand,tag=tparmorstand]");
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "tag @a remove tpplayer");
							});
						});
					});
				});
			}
		});
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