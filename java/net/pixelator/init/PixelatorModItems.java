/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.item.PixelatorTabletItem;
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

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}