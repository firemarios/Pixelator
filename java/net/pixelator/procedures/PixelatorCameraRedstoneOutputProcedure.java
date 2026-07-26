package net.pixelator.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;

public class PixelatorCameraRedstoneOutputProcedure {
	public static double execute(BlockState blockstate) {
		boolean found = false;
		double return_number = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (getPropertyByName(blockstate, "redstone_output") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			return_number = 15;
		} else {
			return_number = 0;
		}
		return return_number;
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