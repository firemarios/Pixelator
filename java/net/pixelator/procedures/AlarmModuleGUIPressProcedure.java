package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicReference;

public class AlarmModuleGUIPressProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean return_logic = false;
		if ((itemFromBlockInventory(world,
				BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z),
				7).copy()).getItem() == PixelatorModItems.ALARM_MODULE.get()) {
			if (getBlockNBTLogic(world,
					BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z),
					"alarm")) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("alarm", false);
						_blockEntity.getPersistentData().putBoolean("manual_alarm", false);
						_blockEntity.getPersistentData().putBoolean("trigger_alarm", false);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				PixelatorModVariables.MapVariables.get(world).to_update_cams = PixelatorModVariables.MapVariables.get(world).to_update_cams
						.replace(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x + "+"
								+ (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3) + "+"
								+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z + ",", "");
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
			} else {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("alarm", true);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				world.scheduleTick(
						BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z),
						world.getBlockState(BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
								entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z)).getBlock(),
						1);
				PixelatorModVariables.MapVariables.get(world).to_update_cams = PixelatorModVariables.MapVariables.get(world).to_update_cams + ""
						+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x + "+"
						+ (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3) + "+"
						+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z + ",";
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
			}
			AlarmSettingsApplyProcedure.execute(world, entity);
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor level, BlockPos pos, int slot) {
		AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> result.set(capability.getStackInSlot(slot)));
		return result.get();
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBoolean(tag);
		return false;
	}
}