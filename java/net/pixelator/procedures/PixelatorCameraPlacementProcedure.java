package net.pixelator.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class PixelatorCameraPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		Direction block = Direction.NORTH;
		boolean placable = false;
		block = new Object() {
			public Direction getDirection(BlockState _bs) {
				Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
				if (_prop instanceof DirectionProperty _dp)
					return _bs.getValue(_dp);
				_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
				return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) _bs.getValue(_ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
			}
		}.getDirection(blockstate);
		if (block == Direction.NORTH) {
			if (!world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude()) {
				placable = false;
				PixelatorCameraAutoDeleteExplosionProcedure.execute(world, x, y, z);
			} else {
				placable = true;
			}
		} else if (block == Direction.SOUTH) {
			if (!world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude()) {
				placable = false;
				PixelatorCameraAutoDeleteExplosionProcedure.execute(world, x, y, z);
			} else {
				placable = true;
			}
		} else if (block == Direction.WEST) {
			if (!world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude()) {
				placable = false;
				PixelatorCameraAutoDeleteExplosionProcedure.execute(world, x, y, z);
			} else {
				placable = true;
			}
		} else if (block == Direction.EAST) {
			if (!world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude()) {
				placable = false;
				PixelatorCameraAutoDeleteExplosionProcedure.execute(world, x, y, z);
			} else {
				placable = true;
			}
		}
		return placable;
	}
}