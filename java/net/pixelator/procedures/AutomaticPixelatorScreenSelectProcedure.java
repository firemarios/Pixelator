package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModMenus;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class AutomaticPixelatorScreenSelectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			PixelatorModVariables.PlayerVariables _vars = entity.getData(PixelatorModVariables.PLAYER_VARIABLES);
			_vars.automaticscreenteleportname = (entity instanceof Player _entity && _entity.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu) ? _menu.getMenuState(0, "automaticscreenteleportname", "") : "";
			_vars.syncPlayerVariables(entity);
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (!((entity.getData(PixelatorModVariables.PLAYER_VARIABLES).automaticscreenteleportname).length() == 0)) {
			if (!entity.getData(PixelatorModVariables.PLAYER_VARIABLES).automaticscreenteleportname.contains(" ")) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"kill @e[type=minecraft:interaction,sort=nearest,limit=1,distance=..1]");
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"summon minecraft:interaction ~.5 ~.5 ~.5 {width:0.1,height:0.1}");
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("tag @e[type=minecraft:interaction,sort=nearest,limit=1] add " + "t" + entity.getData(PixelatorModVariables.PLAYER_VARIABLES).automaticscreenteleportname));
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cError: Do not include spaces"), false);
			}
		}
	}
}