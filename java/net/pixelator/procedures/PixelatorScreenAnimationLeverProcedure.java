package net.pixelator.procedures;

import net.pixelator.PixelatorMod;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class PixelatorScreenAnimationLeverProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean activated = false;
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "activated")) {
			PixelatorMod.queueServerWork(30, () -> {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("activated", false);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			});
			activated = true;
		} else {
			activated = false;
		}
		return activated;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}
}