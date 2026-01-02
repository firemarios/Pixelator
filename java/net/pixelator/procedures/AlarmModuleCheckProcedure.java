package net.pixelator.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class AlarmModuleCheckProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Direction direction) {
		if (direction == null)
			return;
		double dx_negative = 0;
		double dx_positive = 0;
		double dy = 0;
		double dz = 0;
		String command_result = "";
		String data = "";
		world.scheduleTick(BlockPos.containing(x, y, z), world.getBlockState(BlockPos.containing(x, y, z)).getBlock(), 10);
		dx_negative = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dx_negative");
		dx_positive = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dx_positive");
		dy = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dy") * (-1);
		dz = getBlockNBTNumber(world, BlockPos.containing(x, y, z), "dz");
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "motion_detection")) {
			data = ",distance=1..,type=!interaction,type=!item]";
		} else if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "player_detection")) {
			data = ",distance=1..,type=player]";
		} else {
			data = ",distance=1000..]";
		}
		if (direction == Direction.NORTH) {
			command_result = executeCommandGetResult(world, new Vec3(x, y, z), ("execute positioned ^" + dx_negative + " ^0 ^0 if entity @e[dx=" + (dx_positive - dx_negative) + ",dy=" + dy + ",dz=" + dz * (-1) + data));
		} else if (direction == Direction.SOUTH) {
			command_result = executeCommandGetResult(world, new Vec3(x, y, z), ("execute positioned ^" + dx_positive * (-1) + " ^0 ^0 if entity @e[dx=" + (dx_positive - dx_negative) + ",dy=" + dy + ",dz=" + dz + data));
		} else if (direction == Direction.WEST) {
			command_result = executeCommandGetResult(world, new Vec3(x, y, z), ("execute positioned ^0 ^0 ^" + dx_positive * (-1) + " if entity @e[dx=" + dz * (-1) + ",dy=" + dy + ",dz=" + (dx_positive - dx_negative) + data));
		} else if (direction == Direction.EAST) {
			command_result = executeCommandGetResult(world, new Vec3(x, y, z), ("execute positioned ^0 ^0 ^" + dx_negative + " if entity @e[dx=" + dz + ",dy=" + dy + ",dz=" + (dx_positive - dx_negative) + data));
		}
		if (command_result.contains("passed")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("trigger_alarm", true);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putBoolean("trigger_alarm", false);
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}

	private static String executeCommandGetResult(LevelAccessor world, Vec3 pos, String command) {
		StringBuilder result = new StringBuilder();
		if (world instanceof ServerLevel level) {
			CommandSource dataConsumer = new CommandSource() {
				@Override
				public void sendSystemMessage(Component message) {
					result.append(message.getString());
				}

				@Override
				public boolean acceptsSuccess() {
					return true;
				}

				@Override
				public boolean acceptsFailure() {
					return true;
				}

				@Override
				public boolean shouldInformAdmins() {
					return false;
				}
			};
			level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(dataConsumer, pos, Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null), command);
		}
		return result.toString();
	}
}