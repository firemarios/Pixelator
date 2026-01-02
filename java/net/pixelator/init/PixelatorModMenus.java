/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.world.inventory.*;
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
	public static final RegistryObject<MenuType<PixelatorCameraSearchMenu>> PIXELATOR_CAMERA_SEARCH = REGISTRY.register("pixelator_camera_search", () -> IForgeMenuType.create(PixelatorCameraSearchMenu::new));
	public static final RegistryObject<MenuType<ServerPropertiesMenu>> SERVER_PROPERTIES = REGISTRY.register("server_properties", () -> IForgeMenuType.create(ServerPropertiesMenu::new));
	public static final RegistryObject<MenuType<CameraBoundToServerMenu>> CAMERA_BOUND_TO_SERVER = REGISTRY.register("camera_bound_to_server", () -> IForgeMenuType.create(CameraBoundToServerMenu::new));
	public static final RegistryObject<MenuType<ComputerLoginMenu>> COMPUTER_LOGIN = REGISTRY.register("computer_login", () -> IForgeMenuType.create(ComputerLoginMenu::new));
	public static final RegistryObject<MenuType<ComputerRegisterMenu>> COMPUTER_REGISTER = REGISTRY.register("computer_register", () -> IForgeMenuType.create(ComputerRegisterMenu::new));
	public static final RegistryObject<MenuType<ComputerGUIMenu>> COMPUTER_GUI = REGISTRY.register("computer_gui", () -> IForgeMenuType.create(ComputerGUIMenu::new));
	public static final RegistryObject<MenuType<CameraViewPropertiesMenu>> CAMERA_VIEW_PROPERTIES = REGISTRY.register("camera_view_properties", () -> IForgeMenuType.create(CameraViewPropertiesMenu::new));
	public static final RegistryObject<MenuType<AlarmSettingsMenu>> ALARM_SETTINGS = REGISTRY.register("alarm_settings", () -> IForgeMenuType.create(AlarmSettingsMenu::new));

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