package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicReference;

public class AlarmEnabledXProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		boolean return_logic = false;
		if ((itemFromBlockInventory(world,
				BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z),
				7).copy()).getItem() == PixelatorModItems.ALARM_MODULE.get()) {
			return_logic = true;
		} else if ((itemFromBlockInventory(world,
				BlockPos.containing(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3,
						entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z),
				7).copy()).getItem() == Blocks.AIR.asItem()) {
			if (PixelatorModVariables.session_data.contains(((entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_x - 0.5) + "+"
					+ (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_y + 1.3) + "+"
					+ (entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_tp_z - 0.5) + ":alarm_module"))) {
				return_logic = true;
			} else {
				return_logic = false;
			}
		}
		return !return_logic;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor level, BlockPos pos, int slot) {
		AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
		BlockEntity entity = level.getBlockEntity(pos);
		if (entity != null)
			entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> result.set(capability.getStackInSlot(slot)));
		return result.get();
	}
}