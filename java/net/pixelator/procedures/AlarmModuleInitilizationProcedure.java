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
			for (int index0 = 0; index0 < (int) range; index0++) {
				if (world.getBlockState(BlockPos.containing(x + dx_positive, y, z)).isFaceSturdy(world, BlockPos.containing(x + dx_positive, y, z), Direction.WEST)) {
					break;
				}
				dx_positive++;
			}
			for (int index1 = 0; index1 < (int) range; index1++) {
				if (world.getBlockState(BlockPos.containing(x - dx_negative, y, z)).isFaceSturdy(world, BlockPos.containing(x - dx_negative, y, z), Direction.EAST)) {
					break;
				}
				dx_negative++;
			}
			for (int index2 = 0; index2 < (int) range; index2++) {
				if (world.getBlockState(BlockPos.containing(x, y, z - dz)).isFaceSturdy(world, BlockPos.containing(x, y, z - dz), Direction.SOUTH)) {
					break;
				}
				dz++;
			}
			for (int index3 = 0; index3 < (int) range_y; index3++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		} else if (direction == Direction.SOUTH) {
			for (int index4 = 0; index4 < (int) range; index4++) {
				if (world.getBlockState(BlockPos.containing(x - dx_positive, y, z)).isFaceSturdy(world, BlockPos.containing(x - dx_positive, y, z), Direction.EAST)) {
					break;
				}
				dx_positive++;
			}
			for (int index5 = 0; index5 < (int) range; index5++) {
				if (world.getBlockState(BlockPos.containing(x + dx_negative, y, z)).isFaceSturdy(world, BlockPos.containing(x + dx_negative, y, z), Direction.WEST)) {
					break;
				}
				dx_negative++;
			}
			for (int index6 = 0; index6 < (int) range; index6++) {
				if (world.getBlockState(BlockPos.containing(x, y, z + dz)).isFaceSturdy(world, BlockPos.containing(x, y, z + dz), Direction.NORTH)) {
					break;
				}
				dz++;
			}
			for (int index7 = 0; index7 < (int) range_y; index7++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		} else if (direction == Direction.WEST) {
			for (int index8 = 0; index8 < (int) range; index8++) {
				if (world.getBlockState(BlockPos.containing(x, y, z - dx_positive)).isFaceSturdy(world, BlockPos.containing(x, y, z - dx_positive), Direction.SOUTH)) {
					break;
				}
				dx_positive++;
			}
			for (int index9 = 0; index9 < (int) range; index9++) {
				if (world.getBlockState(BlockPos.containing(x, y, z + dx_negative)).isFaceSturdy(world, BlockPos.containing(x, y, z + dx_negative), Direction.NORTH)) {
					break;
				}
				dx_negative++;
			}
			for (int index10 = 0; index10 < (int) range; index10++) {
				if (world.getBlockState(BlockPos.containing(x - dz, y, z)).isFaceSturdy(world, BlockPos.containing(x - dz, y, z), Direction.EAST)) {
					break;
				}
				dz++;
			}
			for (int index11 = 0; index11 < (int) range_y; index11++) {
				if (world.getBlockState(BlockPos.containing(x, y - dy, z)).isFaceSturdy(world, BlockPos.containing(x, y - dy, z), Direction.UP)) {
					break;
				}
				dy++;
			}
		} else if (direction == Direction.EAST) {
			for (int index12 = 0; index12 < (int) range; index12++) {
				if (world.getBlockState(BlockPos.containing(x, y, z + dx_positive)).isFaceSturdy(world, BlockPos.containing(x, y, z + dx_positive), Direction.NORTH)) {
					break;
				}
				dx_positive++;
			}
			for (int index13 = 0; index13 < (int) range; index13++) {
				if (world.getBlockState(BlockPos.containing(x, y, z - dx_negative)).isFaceSturdy(world, BlockPos.containing(x, y, z - dx_negative), Direction.SOUTH)) {
					break;
				}
				dx_negative++;
			}
			for (int index14 = 0; index14 < (int) range; index14++) {
				if (world.getBlockState(BlockPos.containing(x + dz, y, z)).isFaceSturdy(world, BlockPos.containing(x + dz, y, z), Direction.WEST)) {
					break;
				}
				dz++;
			}
			for (int index15 = 0; index15 < (int) range_y; index15++) {
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
				_blockEntity.getPersistentData().putBoolean("alarm_output", true);
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