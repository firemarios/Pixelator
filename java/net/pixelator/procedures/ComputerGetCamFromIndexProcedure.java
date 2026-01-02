package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.util.regex.Pattern;

public class ComputerGetCamFromIndexProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		double index = 0;
		String return_pos = "";
		index = 1;
		{
			String[] _array = (getBlockNBTString(world, BlockPos.containing(x, y, z), "cams")).split(Pattern.quote(","));
			if (_array.length != 0) {
				for (String stringiterator : _array) {
					if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).request_index == index) {
						return_pos = stringiterator;
					}
					index++;
				}
			} else {
				String stringiterator = (getBlockNBTString(world, BlockPos.containing(x, y, z), "cams"));
				for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
					if (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).request_index == index) {
						return_pos = stringiterator;
					}
					index++;
				}
			}
		}
		return return_pos;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}
}