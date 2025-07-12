package net.pixelator.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class PixelatorCameraAutoDeleteProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((new Object() {
			public String getResult(LevelAccessor world, Vec3 pos, String _command) {
				StringBuilder _result = new StringBuilder();
				if (world instanceof ServerLevel _level) {
					CommandSource _dataConsumer = new CommandSource() {
						@Override
						public void sendSystemMessage(Component message) {
							_result.append(message.getString());
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
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
				}
				return _result.toString();
			}
		}.getResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..2]")).contains("passed")) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"kill @e[type=interaction,distance=..2]");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"forceload remove ~ ~");
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cCamera unbounded"), false);
		}
	}
}