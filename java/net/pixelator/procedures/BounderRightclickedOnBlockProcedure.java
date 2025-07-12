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

import io.netty.buffer.Unpooled;

public class BounderRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get() || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get()) {
			if (!((new Object() {
				public String getResult(LevelAccessor world, Vec3 pos, String _command) {
					StringBuilder _result = new StringBuilder();
					if (world instanceof ServerLevel _level) {
						CommandSource _dataConsumer = new CommandSource() {
							@Override
							public void sendSystemMessage(Component message) {
								_result.append(message.getString());
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
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(_dataConsumer, pos, Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null), _command);
					}
					return _result.toString();
				}
			}.getResult(world, new Vec3(x, y, z), "execute positioned ~ ~ ~ if entity @e[type=minecraft:interaction,distance=..2]")).contains("passed"))) {
				{
					double _setval = x;
					entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.cam_x = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = y;
					entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.cam_y = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = z;
					entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.cam_z = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					Direction _setval = new Object() {
						public Direction getDirection(BlockState _bs) {
							Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
							if (_prop instanceof DirectionProperty _dp)
								return _bs.getValue(_dp);
							_prop = _bs.getBlock().getStateDefinition().getProperty("axis");
							return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().toArray()[0] instanceof Direction.Axis
									? Direction.fromAxisAndDirection((Direction.Axis) _bs.getValue(_ep), Direction.AxisDirection.POSITIVE)
									: Direction.NORTH;
						}
					}.getDirection(blockstate);
					entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.cam_face = _setval;
						capability.syncPlayerVariables(entity);
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
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"kill @e[type=interaction,distance=..1]");
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7cCamera unbounded"), false);
			}
		}
	}
}