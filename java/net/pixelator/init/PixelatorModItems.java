/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.item.USBItem;
import net.pixelator.item.PixelatorTabletItem;
import net.pixelator.item.IdCardItem;
import net.pixelator.item.CableItem;
import net.pixelator.item.BounderItem;
import net.pixelator.item.AntennaItem;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class PixelatorModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, PixelatorMod.MODID);
	public static final RegistryObject<Item> BOUNDER = REGISTRY.register("bounder", () -> new BounderItem());
	public static final RegistryObject<Item> PIXELATOR_TABLET = REGISTRY.register("pixelator_tablet", () -> new PixelatorTabletItem());
	public static final RegistryObject<Item> ANTENNA = REGISTRY.register("antenna", () -> new AntennaItem());
	public static final RegistryObject<Item> PIXELATOR_CAMERA_RIGHT = block(PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT);
	public static final RegistryObject<Item> PIXELATOR_SCREEN = block(PixelatorModBlocks.PIXELATOR_SCREEN);
	public static final RegistryObject<Item> AUTOMATIC_PIXELATOR_SCREEN = block(PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN);
	public static final RegistryObject<Item> ROUTER = block(PixelatorModBlocks.ROUTER);
	public static final RegistryObject<Item> PIXELATOR_CAMERA_LEFT = block(PixelatorModBlocks.PIXELATOR_CAMERA_LEFT);
	public static final RegistryObject<Item> SERVER = block(PixelatorModBlocks.SERVER);
	public static final RegistryObject<Item> ID_CARD = REGISTRY.register("id_card", () -> new IdCardItem());
	public static final RegistryObject<Item> CABLE = REGISTRY.register("cable", () -> new CableItem());
	public static final RegistryObject<Item> USB = REGISTRY.register("usb", () -> new USBItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}