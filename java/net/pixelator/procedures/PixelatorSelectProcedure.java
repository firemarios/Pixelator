package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModMenus;
import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.regex.Pattern;

public class PixelatorSelectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.teleportname = ((entity instanceof Player _entity0 && _entity0.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "teleportname", "") : "").isEmpty()
						? entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).selected_cam
						: ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "teleportname", "") : "");
				capability.markSyncDirty();
			});
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		PixelatorMod.queueServerWork(1, () -> {
			if (!(executeCommandGetResult(world, new Vec3(x, y, z), ("tag @e[type=interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname + "]" + " list")))
					.contains("private")) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("effect give " + entity.getDisplayName().getString() + " minecraft:blindness 5 255 true"));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("effect give " + entity.getDisplayName().getString() + " minecraft:slowness 5 255 true"));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
									+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
									+ ",sort=random] if entity @s[tag=left] run tp " + entity.getDisplayName().getString() + " ~ ~ ~"));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
									+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
									+ ",sort=random] if entity @s[tag=right] run tp " + entity.getDisplayName().getString() + " ~ ~ ~"));
				PixelatorCameraSpawnProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
				PixelatorMod.queueServerWork(1, () -> {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] if entity @s[tag=left] run tp " + entity.getDisplayName().getString() + " ^-1 ^-2.5 ^0.3"));
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] if entity @s[tag=right] run tp " + entity.getDisplayName().getString() + " ^1 ^-2.5 ^0.3"));
				});
			} else {
				{
					entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.index_teleport = 0;
						capability.markSyncDirty();
					});
				}
				{
					String[] _array = ((executeCommandGetResult(world, new Vec3(x, y, z),
							("tag @e[type=interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname + "]" + " list"))).substring(24)).split(Pattern.quote(","));
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
						String stringiterator = ((executeCommandGetResult(world, new Vec3(x, y, z),
								("tag @e[type=interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname + "]" + " list"))).substring(24));
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
				if ((world.getBlockState(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server))).getBlock() == PixelatorModBlocks.SERVER.get()
						&& ((executeCommandGetResult(world, new Vec3(x, y, z),
								(("data get block " + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server + " "
										+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server + " "
										+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server).replace(".0", ""))))
								.contains(entity.getStringUUID())
								|| getBlockNBTLogic(world,
										BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).x_server,
												entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).y_server,
												entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).z_server),
										"everybody_teleport"))) {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("effect give " + entity.getDisplayName().getString() + " minecraft:blindness 5 255 true"));
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("effect give " + entity.getDisplayName().getString() + " minecraft:slowness 5 255 true"));
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] if entity @s[tag=left] run tp " + entity.getDisplayName().getString() + " ~ ~ ~"));
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] if entity @s[tag=right] run tp " + entity.getDisplayName().getString() + " ~ ~ ~"));
					PixelatorCameraSpawnProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
					PixelatorMod.queueServerWork(1, () -> {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
											+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
											+ ",sort=random] if entity @s[tag=left] run tp " + entity.getDisplayName().getString() + " ^-1 ^-2.5 ^0.3"));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
											+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
											+ ",sort=random] if entity @s[tag=right] run tp " + entity.getDisplayName().getString() + " ^1 ^-2.5 ^0.3"));
					});
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.teleportationmenu.error.denied_access").getString())), false);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:access_denied")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:access_denied")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
				}
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

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}
}