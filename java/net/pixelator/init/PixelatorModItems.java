/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.item.*;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class PixelatorModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, PixelatorMod.MODID);
	public static final RegistryObject<Item> BOUNDER;
	public static final RegistryObject<Item> PIXELATOR_TABLET;
	public static final RegistryObject<Item> ANTENNA;
	public static final RegistryObject<Item> PIXELATOR_CAMERA_RIGHT;
	public static final RegistryObject<Item> PIXELATOR_SCREEN;
	public static final RegistryObject<Item> AUTOMATIC_PIXELATOR_SCREEN;
	public static final RegistryObject<Item> ROUTER;
	public static final RegistryObject<Item> PIXELATOR_CAMERA_LEFT;
	public static final RegistryObject<Item> SERVER;
	public static final RegistryObject<Item> ID_CARD;
	public static final RegistryObject<Item> CABLE;
	public static final RegistryObject<Item> USB;
	public static final RegistryObject<Item> COMPUTER;
	public static final RegistryObject<Item> VIDEO_CABLE;
	public static final RegistryObject<Item> NIGHT_VISION_MODULE;
	public static final RegistryObject<Item> ENTIY_GLOWING_MODULE;
	public static final RegistryObject<Item> ALARM_MODULE;
	public static final RegistryObject<Item> ALARM_BLOCK;
	public static final RegistryObject<Item> PLAYER_DETECTION_MODULE;
	public static final RegistryObject<Item> MOTION_DETECTION_MODULE;
	public static final RegistryObject<Item> REDSTONE_MODULE;
	public static final RegistryObject<Item> REDSTONE_EMITTER_MODULE;
	static {
		BOUNDER = REGISTRY.register("bounder", BounderItem::new);
		PIXELATOR_TABLET = REGISTRY.register("pixelator_tablet", PixelatorTabletItem::new);
		ANTENNA = REGISTRY.register("antenna", AntennaItem::new);
		PIXELATOR_CAMERA_RIGHT = block(PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT);
		PIXELATOR_SCREEN = block(PixelatorModBlocks.PIXELATOR_SCREEN);
		AUTOMATIC_PIXELATOR_SCREEN = block(PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN);
		ROUTER = block(PixelatorModBlocks.ROUTER);
		PIXELATOR_CAMERA_LEFT = block(PixelatorModBlocks.PIXELATOR_CAMERA_LEFT);
		SERVER = block(PixelatorModBlocks.SERVER);
		ID_CARD = REGISTRY.register("id_card", IdCardItem::new);
		CABLE = REGISTRY.register("cable", CableItem::new);
		USB = REGISTRY.register("usb", USBItem::new);
		COMPUTER = block(PixelatorModBlocks.COMPUTER);
		VIDEO_CABLE = REGISTRY.register("video_cable", VideoCableItem::new);
		NIGHT_VISION_MODULE = REGISTRY.register("night_vision_module", NightVisionModuleItem::new);
		ENTIY_GLOWING_MODULE = REGISTRY.register("entiy_glowing_module", EntiyGlowingModuleItem::new);
		ALARM_MODULE = REGISTRY.register("alarm_module", AlarmModuleItem::new);
		ALARM_BLOCK = block(PixelatorModBlocks.ALARM_BLOCK);
		PLAYER_DETECTION_MODULE = REGISTRY.register("player_detection_module", PlayerDetectionModuleItem::new);
		MOTION_DETECTION_MODULE = REGISTRY.register("motion_detection_module", MotionDetectionModuleItem::new);
		REDSTONE_MODULE = REGISTRY.register("redstone_module", RedstoneModuleItem::new);
		REDSTONE_EMITTER_MODULE = block(PixelatorModBlocks.REDSTONE_EMITTER_MODULE);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}