package net.pixelator.procedures;

import net.pixelator.init.PixelatorModItems;
import net.pixelator.PixelatorMod;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class AlarmModuleInitilizationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Direction direction) {
		if (direction == null)
			return;
		double range = 0;
		double dx_positive = 0;
		double dx_negative = 0;
		double dy = 0;
		double dz = 0;
		double range_y = 0;
		dx_positive = 1;
		dx_negative = 1;
		dy = 1;
		dz = 1;
		range = 15;
		range_y = 10;
		if (direction == Direction.NORTH) {
			for (int index192 = 0; index192 < (int) range; index192++) {
				if (world.getBlockState(BlockPos.containing(x + dx_positive, y, z)).isFaceSturdy(world, BlockPos.containing(x + dx_positive, y, z), Direction.WEST)) {
					break;
				}
				dx_positive++;
			}
			for (int index193 = 0; index193 < (int) range; index193++) {
				if (world.getBlockState(BlockPos.containing(x - dx_negative, y, z)).isFaceSturdy(world, BlockPos.containing(x - dx_negative, y, z), Direction.EAST)) {
					break;
				}
				dx_negative++;
			}
			for (int index194 = 0; index194 < (int) range; index194++) {
				if (world.getBlockState(BlockPos.containing(x, y, z - dz)).isFaceSturdy(world, BlockPos.containing(x, y, z - dz), Direction.SOUTH)) {
					break;
				}
				dz++;
			}
			for (int index195 = 0; index195 < (int) range_y; index195++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		} else if (direction == Direction.SOUTH) {
			for (int index196 = 0; index196 < (int) range; index196++) {
				if (world.getBlockState(BlockPos.containing(x - dx_positive, y, z)).isFaceSturdy(world, BlockPos.containing(x - dx_positive, y, z), Direction.EAST)) {
					break;
				}
				dx_positive++;
			}
			for (int index197 = 0; index197 < (int) range; index197++) {
				if (world.getBlockState(BlockPos.containing(x + dx_negative, y, z)).isFaceSturdy(world, BlockPos.containing(x + dx_negative, y, z), Direction.WEST)) {
					break;
				}
				dx_negative++;
			}
			for (int index198 = 0; index198 < (int) range; index198++) {
				if (world.getBlockState(BlockPos.containing(x, y, z + dz)).isFaceSturdy(world, BlockPos.containing(x, y, z + dz), Direction.NORTH)) {
					break;
				}
				dz++;
			}
			for (int index199 = 0; index199 < (int) range_y; index199++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		} else if (direction == Direction.WEST) {
			for (int index200 = 0; index200 < (int) range; index200++) {
				if (world.getBlockState(BlockPos.containing(x, y, z - dx_positive)).isFaceSturdy(world, BlockPos.containing(x, y, z - dx_positive), Direction.SOUTH)) {
					break;
				}
				dx_positive++;
			}
			for (int index201 = 0; index201 < (int) range; index201++) {
				if (world.getBlockState(BlockPos.containing(x, y, z + dx_negative)).isFaceSturdy(world, BlockPos.containing(x, y, z + dx_negative), Direction.NORTH)) {
					break;
				}
				dx_negative++;
			}
			for (int index202 = 0; index202 < (int) range; index202++) {
				if (world.getBlockState(BlockPos.containing(x - dz, y, z)).isFaceSturdy(world, BlockPos.containing(x - dz, y, z), Direction.EAST)) {
					break;
				}
				dz++;
			}
			for (int index203 = 0; index203 < (int) range_y; index203++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		} else if (direction == Direction.EAST) {
			for (int index204 = 0; index204 < (int) range; index204++) {
				if (world.getBlockState(BlockPos.containing(x, y, z + dx_positive)).isFaceSturdy(world, BlockPos.containing(x, y, z + dx_positive), Direction.NORTH)) {
					break;
				}
				dx_positive++;
			}
			for (int index205 = 0; index205 < (int) range; index205++) {
				if (world.getBlockState(BlockPos.containing(x, y, z - dx_negative)).isFaceSturdy(world, BlockPos.containing(x, y, z - dx_negative), Direction.SOUTH)) {
					break;
				}
				dx_negative++;
			}
			for (int index206 = 0; index206 < (int) range; index206++) {
				if (world.getBlockState(BlockPos.containing(x + dz, y, z)).isFaceSturdy(world, BlockPos.containing(x + dz, y, z), Direction.WEST)) {
					break;
				}
				dz++;
			}
			for (int index207 = 0; index207 < (int) range_y; index207++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		}
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("dx_positive", (dx_positive - 1));
				_blockEntity.getPersistentData().putDouble("dx_negative", ((dx_negative - 1) * (-1)));
				_blockEntity.getPersistentData().putDouble("dy", (dy - 1 <= 3 ? 3 : dy - 1));
				_blockEntity.getPersistentData().putDouble("dz", (dz - 1));
				_blockEntity.getPersistentData().putBoolean("alarm_output", false);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		{
			BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
			if (_ent != null) {
				final int _slotid = 0;
				final ItemStack _setstack = new ItemStack(PixelatorModItems.USB.get()).copy();
				_setstack.setCount(1);
				_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable)
						((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
				});
			}
		}
		PixelatorMod.queueServerWork(1, () -> {
			{
				BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
				if (_ent != null) {
					final int _slotid = 0;
					final int _amount = 1;
					_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							ItemStack _stk = capability.getStackInSlot(_slotid).copy();
							_stk.shrink(_amount);
							((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _stk);
						}
					});
				}
			}
		});
	}
}