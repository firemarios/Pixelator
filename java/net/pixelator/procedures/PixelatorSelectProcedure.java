package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModMenus;
import net.pixelator.PixelatorMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class PixelatorSelectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			String _setval = (entity instanceof Player _entity && _entity.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "teleportname", "") : "";
			entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.teleportname = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		PixelatorMod.queueServerWork(1, () -> {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("execute as @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
								+ ",sort=random] at @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
								+ ",sort=random] if entity @s[tag=left] run tp " + entity.getDisplayName().getString() + " ~ ~ ~"));
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("execute as @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
								+ ",sort=random] at @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
								+ ",sort=random] if entity @s[tag=right] run tp " + entity.getDisplayName().getString() + " ~ ~ ~"));
			PixelatorCameraSpawnProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
			PixelatorMod.queueServerWork(1, () -> {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("execute as @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
									+ ",sort=random] at @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
									+ ",sort=random] if entity @s[tag=left] run tp " + entity.getDisplayName().getString() + " ^-1 ^-2.5 ^0.3"));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("execute as @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
									+ ",sort=random] at @e[type=minecraft:interaction,tag=" + (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new PixelatorModVariables.PlayerVariables())).teleportname
									+ ",sort=random] if entity @s[tag=right] run tp " + entity.getDisplayName().getString() + " ^1 ^-2.5 ^0.3"));
			});
		});
	}
}