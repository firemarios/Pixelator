package net.pixelator.procedures;

import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.Map;

public class AutomaticPixelatorScreenActivationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "forceload add ~ ~");
		PixelatorMod.queueServerWork(20, () -> {
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
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 11.67, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 20.83, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 33.33, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 42.5, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 58.33, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork((int) 75.83, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("pixelator:automatic_pixelator_screen_activation")), SoundSource.BLOCKS, 1, 1, false);
						}
					}
				}
			});
			PixelatorMod.queueServerWork(80, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get()) {
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
						BlockState _bs = PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
							if (_property != null && _bs.getValue(_property) != null)
								try {
									_bs = _bs.setValue(_property, (Comparable) entry.getValue());
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