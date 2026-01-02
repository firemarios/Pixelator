package net.pixelator.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class AutomaticPixelatorScreenAnimationActivateProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean activated = false;
		if (new Object() {
			public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getBoolean(tag);
				return false;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "activated")) {
			activated = true;
		} else {
			activated = false;
		}
		return activated;
	}
}