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
		block = getDirectionFromBlockState(blockstate);
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

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = getPropertyByName(blockState, "facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = getPropertyByName(blockState, "axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}