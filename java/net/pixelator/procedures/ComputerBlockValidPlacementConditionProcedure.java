package net.pixelator.procedures;

import net.pixelator.init.PixelatorModBlocks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class ComputerBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean returnTo = false;
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == PixelatorModBlocks.SERVER.get()) {
			returnTo = true;
		} else {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("tellraw @a[distance=..6] {\"text\":\"" + "" + Component.translatable("msg.pixelator.computer.valid_placement").getString() + "\",\"color\":\"red\"}"));
			returnTo = false;
		}
		return returnTo;
	}
}