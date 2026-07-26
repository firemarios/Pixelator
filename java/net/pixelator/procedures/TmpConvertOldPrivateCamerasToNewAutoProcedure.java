package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModBlocks;
import net.pixelator.PixelatorMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class TmpConvertOldPrivateCamerasToNewAutoProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean return_logic = false;
		String world_including_server = "";
		try {
			if ((PixelatorModVariables.world_server_auto_delete).isEmpty()) {
				if (world.getServer() != null) {
					LevelAccessor _origWorld = world;
					for (ServerLevel worlditerator : world.getServer().getAllLevels()) {
						world = worlditerator;
						if ((world.getBlockState(BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
								PixelatorModVariables.MapVariables.get(world).z_server_auto_delete))).getBlock() == PixelatorModBlocks.SERVER.get()
								&& (executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=interaction,sort=nearest,limit=1] list"))
										.contains(getBlockNBTString(world, BlockPos.containing(PixelatorModVariables.MapVariables.get(world).x_server_auto_delete, PixelatorModVariables.MapVariables.get(world).y_server_auto_delete,
												PixelatorModVariables.MapVariables.get(world).z_server_auto_delete), "network_name"))) {
							PixelatorModVariables.world_server_auto_delete = ((Level) world).dimension().location().toString().replace(":", "");
						}
					}
					world = _origWorld;
				}
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("tag @e[type=interaction,sort=nearest,limit=1] remove " + PixelatorModVariables.MapVariables.get(world).server_pos_auto_delete));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("tag @e[type=interaction,sort=nearest,limit=1] add " + PixelatorModVariables.MapVariables.get(world).x_server_auto_delete + "+" + PixelatorModVariables.MapVariables.get(world).y_server_auto_delete + "+"
									+ PixelatorModVariables.MapVariables.get(world).z_server_auto_delete + "+" + PixelatorModVariables.world_server_auto_delete));
				PixelatorMod.LOGGER.info((Component.translatable("msg.pixelator.tmp.success_convert").getString()));
			}
			return_logic = true;
		} catch (Exception _exception12) {
			_exception12.printStackTrace();
			PixelatorMod.LOGGER.error((Component.translatable("msg.pixelator.tmp.cannot_convert").getString()));
			return_logic = false;
		}
		return return_logic;
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

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}
}