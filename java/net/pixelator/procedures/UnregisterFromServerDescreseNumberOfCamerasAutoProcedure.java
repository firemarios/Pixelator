package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModItems;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class UnregisterFromServerDescreseNumberOfCamerasAutoProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		String text_search = "";
		String text_computer = "";
		String current_world = "";
		boolean return_logic = false;
		try {
			current_world = ((Level) world).dimension().location().toString().replace(":", "");
			if (world.getServer() != null) {
				LevelAccessor _origWorld = world;
				for (ServerLevel worlditerator : world.getServer().getAllLevels()) {
					world = worlditerator;
					if ((PixelatorModVariables.world_server_auto_delete).equals(((Level) world).dimension().location().toString().replace(":", ""))) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
									PixelatorModVariables.MapVariables.get(world).z_server_auto_delete);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("cams", (getBlockNBTNumber(world, BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete,
										PixelatorModVariables.MapVariables.get(world).y_server_auto_delete, PixelatorModVariables.MapVariables.get(world).z_server_auto_delete), "cams") - 1));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						text_search = x + "+" + y + "+" + z + "+" + current_world;
						text_computer = getBlockNBTString(world, BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
								PixelatorModVariables.MapVariables.get(world).z_server_auto_delete), "cams");
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + 1,
									PixelatorModVariables.MapVariables.get(world).z_server_auto_delete);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putString("cams", (text_computer.replace(text_search + ",", "")));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						{
							BlockEntity _ent = world.getBlockEntity(BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
									PixelatorModVariables.MapVariables.get(world).z_server_auto_delete));
							if (_ent != null) {
								final int _slotid = 28;
								final ItemStack _setstack = new ItemStack(PixelatorModItems.USB.get()).copy();
								_setstack.setCount(1);
								_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable)
										((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
								});
							}
						}
						return_logic = true;
						{
							BlockEntity _ent = world.getBlockEntity(BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
									PixelatorModVariables.MapVariables.get(world).z_server_auto_delete));
							if (_ent != null) {
								final int _slotid = 28;
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
					}
				}
				world = _origWorld;
			}
		} catch (Throwable _exception9) {
			_exception9.printStackTrace();
			return_logic = false;
		}
		return return_logic;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDouble(tag);
		return -1;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}
}