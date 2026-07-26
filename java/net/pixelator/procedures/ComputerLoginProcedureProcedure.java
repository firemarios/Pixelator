package net.pixelator.procedures;

import net.pixelator.world.inventory.ComputerGUIMenu;
import net.pixelator.network.PixelatorModVariables;
import net.pixelator.init.PixelatorModMenus;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.regex.Pattern;

import io.netty.buffer.Unpooled;

public class ComputerLoginProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String username_in = "";
		String password_in = "";
		String username_reg = "";
		String password_reg = "";
		username_in = (entity instanceof Player _entity0 && _entity0.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "computerLoginUsername", "") : "";
		password_in = (entity instanceof Player _entity1 && _entity1.containerMenu instanceof PixelatorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "computerLoginPassword", "") : "";
		if (!((username_in).isEmpty() || (password_in).isEmpty())) {
			username_reg = getBlockNBTString(world, BlockPos.containing(x, y, z), "username");
			password_reg = getBlockNBTString(world, BlockPos.containing(x, y, z), "password");
			if ((username_in).equals(username_reg) && (password_in).equals(password_reg)) {
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.computer_cams = 0;
						_playerVars.markSyncDirty();
					}
				}
				if (!(getBlockNBTString(world, BlockPos.containing(x, y, z), "cams")).isEmpty()) {
					String _splitContent7 = Pattern.quote(",");
					String _toSplit7 = (getBlockNBTString(world, BlockPos.containing(x, y, z), "cams"));
					String[] _array7 = _toSplit7.split(_splitContent7);
					if (_array7.length != 0) {
						for (String stringiterator : _array7) {
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_cams++;
						}
					} else {
						String stringiterator = _toSplit7;
						for (int _yourmother7 = 0; _yourmother7 < 1; _yourmother7++) {
							entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_cams++;
						}
					}
				}
				{
					var _playerVars = entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElse(null);
					if (_playerVars != null) {
						_playerVars.computer_pages = Math.ceil(entity.getCapability(PixelatorModVariables.PLAYER_VARIABLES).orElseGet(PixelatorModVariables.PlayerVariables::new).computer_cams / 4);
						_playerVars.computer_page = 1;
						_playerVars.markSyncDirty();
					}
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("ComputerGUI");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new ComputerGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.computer.login.invalid").getString())), false);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("msg.pixelator.computer.empty").getString())), false);
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getString(tag);
		return "";
	}
}