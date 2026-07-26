package net.pixelator.procedures;

import net.pixelator.init.PixelatorModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class RedstoneEmitterModuleRedstoneProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = -3;
		found = false;
		for (int index35 = 0; index35 < 6; index35++) {
			sy = -3;
			for (int index36 = 0; index36 < 6; index36++) {
				sz = -3;
				for (int index37 = 0; index37 < 6; index37++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()
							|| (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
						if (getPropertyByName((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))), "redstone_output") instanceof BooleanProperty _getbp5
								&& (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getValue(_getbp5)) {
							{
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("redstone_output") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
							}
							PixelatorCameraUpdateBlocksProcedure.execute(world, x, y, z);
						} else {
							{
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("redstone_output") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
							}
							PixelatorCameraUpdateBlocksProcedure.execute(world, x, y, z);
						}
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
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