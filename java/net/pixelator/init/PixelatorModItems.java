/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.item.inventory.PixelatorTabletInventoryCapability;
import net.pixelator.item.PixelatorTabletItem;
import net.pixelator.item.BounderItem;
import net.pixelator.item.AntennaItem;
import net.pixelator.PixelatorMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class PixelatorModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(PixelatorMod.MODID);
	public static final DeferredItem<Item> BOUNDER = REGISTRY.register("bounder", BounderItem::new);
	public static final DeferredItem<Item> PIXELATOR_TABLET = REGISTRY.register("pixelator_tablet", PixelatorTabletItem::new);
	public static final DeferredItem<Item> ANTENNA = REGISTRY.register("antenna", AntennaItem::new);
	public static final DeferredItem<Item> PIXELATOR_CAMERA_RIGHT = block(PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT);
	public static final DeferredItem<Item> PIXELATOR_SCREEN = block(PixelatorModBlocks.PIXELATOR_SCREEN);
	public static final DeferredItem<Item> AUTOMATIC_PIXELATOR_SCREEN = block(PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN);
	public static final DeferredItem<Item> ROUTER = block(PixelatorModBlocks.ROUTER);
	public static final DeferredItem<Item> PIXELATOR_CAMERA_LEFT = block(PixelatorModBlocks.PIXELATOR_CAMERA_LEFT);

	// Start of user code block custom items
	// End of user code block custom items
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.ItemHandler.ITEM, (stack, context) -> new PixelatorTabletInventoryCapability(stack), PIXELATOR_TABLET.get());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}