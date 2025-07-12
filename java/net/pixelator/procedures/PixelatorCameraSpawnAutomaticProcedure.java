package net.pixelator.procedures;

import net.pixelator.init.PixelatorModParticleTypes;
import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

public class PixelatorCameraSpawnAutomaticProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putBoolean("spawning", true);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			PixelatorMod.queueServerWork(1, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			});
			PixelatorMod.queueServerWork((int) 23.2, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
				PixelatorMod.queueServerWork(10, () -> {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
					PixelatorMod.queueServerWork(10, () -> {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
						PixelatorMod.queueServerWork(10, () -> {
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
							PixelatorMod.queueServerWork(10, () -> {
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
								PixelatorMod.queueServerWork(10, () -> {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
								});
							});
						});
					});
				});
				PixelatorMod.queueServerWork(65, () -> {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putBoolean("spawning", false);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				});
			});
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putBoolean("spawning", true);
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			PixelatorMod.queueServerWork(1, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			});
			PixelatorMod.queueServerWork((int) 23.2, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
				PixelatorMod.queueServerWork(10, () -> {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
					PixelatorMod.queueServerWork(10, () -> {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
						PixelatorMod.queueServerWork(10, () -> {
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
							PixelatorMod.queueServerWork(10, () -> {
								if (world instanceof ServerLevel _level)
									_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
								PixelatorMod.queueServerWork(10, () -> {
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (PixelatorModParticleTypes.PIXELATOR_CAMERA_SPAWN_PARTICLE.get()), x, y, z, 100, 2, 2, 2, 0.2);
								});
							});
						});
					});
				});
				PixelatorMod.queueServerWork(65, () -> {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null)
							_blockEntity.getPersistentData().putBoolean("spawning", false);
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				});
			});
		}
	}
}