package net.pixelator.procedures;

import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class RouterActivationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		PixelatorMod.queueServerWork(2, () -> {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putBoolean("activated", true);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			PixelatorMod.queueServerWork(1, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.ROUTER.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork(15, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.ROUTER.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 25.83, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.ROUTER.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork(40, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.ROUTER.get()) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putBoolean("activated", false);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = PixelatorModBlocks.ROUTER_ACTIVATED.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				}
			});
		});
	}
}