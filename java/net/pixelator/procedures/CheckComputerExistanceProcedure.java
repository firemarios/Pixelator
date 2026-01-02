package net.pixelator.procedures;

import net.pixelator.init.PixelatorModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class CheckComputerExistanceProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		boolean return_logic = false;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.COMPUTER.get()) {
			return_logic = true;
		} else {
			return_logic = false;
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.computer.not_exist").getString())), false);
		}
		return return_logic;
	}
}