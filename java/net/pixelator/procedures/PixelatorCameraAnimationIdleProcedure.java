package net.pixelator.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class PixelatorCameraAnimationIdleProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean idle = false;
		if (!getBlockNBTLogic(world, BlockPos.containing(x, y, z), "spawning")) {
			idle = true;
		} else {
			idle = false;
		}
		return idle;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}
}