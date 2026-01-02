/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class PixelatorModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PixelatorMod.MODID);
	public static final RegistryObject<CreativeModeTab> PIXELATOR = REGISTRY.register("pixelator",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.pixelator.pixelator")).icon(() -> new ItemStack(PixelatorModItems.BOUNDER.get())).displayItems((parameters, tabData) -> {
				tabData.accept(PixelatorModItems.BOUNDER.get());
				tabData.accept(PixelatorModItems.PIXELATOR_TABLET.get());
				tabData.accept(PixelatorModItems.ANTENNA.get());
				tabData.accept(PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT.get().asItem());
				tabData.accept(PixelatorModBlocks.PIXELATOR_CAMERA_LEFT.get().asItem());
				tabData.accept(PixelatorModBlocks.PIXELATOR_SCREEN.get().asItem());
				tabData.accept(PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN.get().asItem());
				tabData.accept(PixelatorModBlocks.ROUTER.get().asItem());
				tabData.accept(PixelatorModBlocks.SERVER.get().asItem());
				tabData.accept(PixelatorModItems.ID_CARD.get());
				tabData.accept(PixelatorModItems.CABLE.get());
				tabData.accept(PixelatorModItems.USB.get());
				tabData.accept(PixelatorModBlocks.COMPUTER.get().asItem());
				tabData.accept(PixelatorModItems.VIDEO_CABLE.get());
				tabData.accept(PixelatorModBlocks.ALARM_BLOCK.get().asItem());
				tabData.accept(PixelatorModItems.NIGHT_VISION_MODULE.get());
				tabData.accept(PixelatorModItems.ENTIY_GLOWING_MODULE.get());
				tabData.accept(PixelatorModItems.ALARM_MODULE.get());
				tabData.accept(PixelatorModItems.PLAYER_DETECTION_MODULE.get());
				tabData.accept(PixelatorModItems.MOTION_DETECTION_MODULE.get());
				tabData.accept(PixelatorModItems.REDSTONE_MODULE.get());
			}).build());
}