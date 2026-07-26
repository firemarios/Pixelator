package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.function.Function;

public class PixelatorCameraSpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
			if (_playerVars != null) {
				_playerVars.particle_height = 2.5;
				_playerVars.markSyncDirty();
			}
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get()) {
			PixelatorMod.queueServerWork(1, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("spawning", true);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				entity.setDeltaMovement(new Vec3(0, 0, 0));
			});
			PixelatorMod.queueServerWork((int) 23.2, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				createTimedLoop(20, (int) 3.25, _timedLoop -> {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] if entity @s[tag=left] run particle pixelator:pixelator_camera_spawn_particle ^-1 ^-"
										+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).particle_height + " ^0.3 .2 .1 .1 0 10"));
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.particle_height = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).particle_height - 0.1;
							_playerVars.markSyncDirty();
						}
					}
					return true;
				});
				PixelatorMod.queueServerWork(65, () -> {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putBoolean("spawning", false);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				});
			});
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
			PixelatorMod.queueServerWork(1, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_turning")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null) {
						_blockEntity.getPersistentData().putBoolean("spawning", true);
					}
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				entity.setDeltaMovement(new Vec3(0, 0, 0));
			});
			PixelatorMod.queueServerWork((int) 23.2, () -> {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pixelator:pixelator_camera_spawn")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
				createTimedLoop(20, (int) 3.25, _timedLoop -> {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("execute as @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] at @e[type=minecraft:interaction,tag=" + entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).teleportname
										+ ",sort=random] if entity @s[tag=right] run particle pixelator:pixelator_camera_spawn_particle ^1 ^-"
										+ entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).particle_height + " ^0.3 .2 .1 .1 0 10"));
					{
						var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
						if (_playerVars != null) {
							_playerVars.particle_height = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).particle_height - 0.1;
							_playerVars.markSyncDirty();
						}
					}
					return true;
				});
				PixelatorMod.queueServerWork(65, () -> {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putBoolean("spawning", false);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				});
			});
		}
	}

	private static class PixelatorModTimedLoop {
		private int iterator = 0;
		private int ticks = 0;
		private final int total;
		private final Function<PixelatorModTimedLoop, Boolean> forEach;

		private PixelatorModTimedLoop(int total, int ticks, Function<PixelatorModTimedLoop, Boolean> forEach) {
			this.total = total;
			this.ticks = ticks;
			this.forEach = forEach;
			run();
		}

		private void run() {
			if (forEach.apply(this))
				PixelatorMod.queueServerWork(ticks, this::next);
		}

		private void next() {
			if (total > ++iterator)
				run();
		}
	}

	private static PixelatorModTimedLoop createTimedLoop(int total, int ticks, Function<PixelatorModTimedLoop, Boolean> forEach) {
		return new PixelatorModTimedLoop(total, ticks, forEach);
	}
}