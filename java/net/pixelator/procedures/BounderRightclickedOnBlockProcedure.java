package net.pixelator.procedures;

import net.pixelator.world.inventory.CameraBoundMenu;
import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModBlocks;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.ArrayList;

import io.netty.buffer.Unpooled;

public class BounderRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		String delete_text = "";
		double index = 0;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get() || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 5);
			if (!(executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..1.8]")).contains("passed")) {
				{
					entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.cam_x = x;
						capability.cam_y = y;
						capability.cam_z = z;
						capability.cam_face = getDirectionFromBlockState(blockstate);
						capability.markSyncDirty();
					});
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("CameraBound");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new CameraBoundMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (!(executeCommandGetResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ as @e[type=minecraft:interaction,distance=..1.8] at @s if entity @s[tag=private]")).contains("passed")) {
					index = 0;
					delete_text = ((((((executeCommandGetResult(world, new Vec3(x, y, z), "tag @e[type=minecraft:interaction,distance=..1,limit=1] list")).substring(24)).replace("right", "")).replace("left", "")).replace("netcon", "")).replace(" ",
							"")).replace(",", "");
					for (int index0 = 0; index0 < (int) PixelatorModVariables.cameras_decoded.size(); index0++) {
						if ((/*@String*/(new Object() {
							private <E> E getListElement(ArrayList<Object> objects, int index, Class<E> eClass, Object defaultValue) {
								if (index < objects.size()) {
									var element = objects.get(index);
									if (eClass.isInstance(element)) {
										return eClass.cast(element);
									}
								}
								return eClass.cast(defaultValue);
							}
						}.getListElement(PixelatorModVariables.cameras_decoded, (int) index, String.class, ""))).equals(delete_text)) {
							PixelatorModVariables.cameras_decoded.remove((int) index);
							PixelatorCameraListEncoderProcedure.execute(world);
							break;
						} else {
							index++;
						}
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"kill @e[type=interaction,distance=..1]");
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.unbounded").getString())), false);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.camera.server.cantunbind").getString())), false);
				}
			}
		}
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

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}
}