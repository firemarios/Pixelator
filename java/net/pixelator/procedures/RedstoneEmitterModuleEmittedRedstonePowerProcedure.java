package net.pixelator.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneEmitterModuleEmittedRedstonePowerProcedure {
	public static double execute(BlockState blockstate) {
		double return_number = 0;
		if (blockstate.getBlock().getStateDefinition().getProperty("redstone_output") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			return_number = 15;
		} else {
			return_number = 0;
		}
		return return_number;
	}
}