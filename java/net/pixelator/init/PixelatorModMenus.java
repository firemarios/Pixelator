/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.world.inventory.TabletSelectorMenu;
import net.pixelator.world.inventory.PixelatorSelectorMenu;
import net.pixelator.world.inventory.CameraBoundMenu;
import net.pixelator.world.inventory.AutomaticPixelatorScreenSelectorMenu;
import net.pixelator.network.MenuStateUpdateMessage;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import java.util.Map;

public class PixelatorModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, PixelatorMod.MODID);
	public static final RegistryObject<MenuType<CameraBoundMenu>> CAMERA_BOUND = REGISTRY.register("camera_bound", () -> IForgeMenuType.create(CameraBoundMenu::new));
	public static final RegistryObject<MenuType<PixelatorSelectorMenu>> PIXELATOR_SELECTOR = REGISTRY.register("pixelator_selector", () -> IForgeMenuType.create(PixelatorSelectorMenu::new));
	public static final RegistryObject<MenuType<AutomaticPixelatorScreenSelectorMenu>> AUTOMATIC_PIXELATOR_SCREEN_SELECTOR = REGISTRY.register("automatic_pixelator_screen_selector",
			() -> IForgeMenuType.create(AutomaticPixelatorScreenSelectorMenu::new));
	public static final RegistryObject<MenuType<TabletSelectorMenu>> TABLET_SELECTOR = REGISTRY.register("tablet_selector", () -> IForgeMenuType.create(TabletSelectorMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof PixelatorModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				PixelatorMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}