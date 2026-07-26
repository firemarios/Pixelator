package net.pixelator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class PixelatorCameraUpdateBlocksProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = -3;
		found = false;
		for (int index32 = 0; index32 < 6; index32++) {
			sy = -3;
			for (int index33 = 0; index33 < 6; index33++) {
				sz = -3;
				for (int index34 = 0; index34 < 6; index34++) {
					world.scheduleTick(BlockPos.containing(x + sx, y + sy, z + sz), world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)).getBlock(), 2);
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
	}
}