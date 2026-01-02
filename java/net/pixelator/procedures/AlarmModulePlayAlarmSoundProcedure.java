package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class AlarmModulePlayAlarmSoundProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "manual_alarm") || getBlockNBTLogic(world, BlockPos.containing(x, y, z), "trigger_alarm") && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "alarm_output")) {
			world.scheduleTick(BlockPos.containing(x, y, z), world.getBlockState(BlockPos.containing(x, y, z)).getBlock(), 20);
			if (PixelatorModVariables.MapVariables.get(world).alarm_timer % 20 == 0) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:alarm")), SoundSource.BLOCKS, 10, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:alarm")), SoundSource.BLOCKS, 10, 1, false);
					}
				}
			}
			PixelatorModVariables.MapVariables.get(world).alarm_timer = PixelatorModVariables.MapVariables.get(world).alarm_timer + 20;
			PixelatorModVariables.MapVariables.get(world).markSyncDirty();
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}
}