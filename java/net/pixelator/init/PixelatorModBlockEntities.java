/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.block.entity.RouterBlockEntity;
import net.pixelator.block.entity.RouterActivatedBlockEntity;
import net.pixelator.block.entity.PixelatorScreenBlockEntity;
import net.pixelator.block.entity.PixelatorCameraRightBlockEntity;
import net.pixelator.block.entity.PixelatorCameraLeftBlockEntity;
import net.pixelator.block.entity.AutomaticPixelatorScreenBlockEntity;
import net.pixelator.block.entity.AutomaticPixelatorScreenActivatedBlockEntity;
import net.pixelator.PixelatorMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class PixelatorModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, PixelatorMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> PIXELATOR_CAMERA_RIGHT = register("pixelator_camera_right", PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT, PixelatorCameraRightBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> PIXELATOR_SCREEN = register("pixelator_screen", PixelatorModBlocks.PIXELATOR_SCREEN, PixelatorScreenBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> AUTOMATIC_PIXELATOR_SCREEN = register("automatic_pixelator_screen", PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN, AutomaticPixelatorScreenBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED = register("automatic_pixelator_screen_activated", PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED,
			AutomaticPixelatorScreenActivatedBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ROUTER = register("router", PixelatorModBlocks.ROUTER, RouterBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> ROUTER_ACTIVATED = register("router_activated", PixelatorModBlocks.ROUTER_ACTIVATED, RouterActivatedBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> PIXELATOR_CAMERA_LEFT = register("pixelator_camera_left", PixelatorModBlocks.PIXELATOR_CAMERA_LEFT, PixelatorCameraLeftBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PIXELATOR_CAMERA_RIGHT.get(), (blockEntity, side) -> ((PixelatorCameraRightBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PIXELATOR_SCREEN.get(), (blockEntity, side) -> ((PixelatorScreenBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, AUTOMATIC_PIXELATOR_SCREEN.get(), (blockEntity, side) -> ((AutomaticPixelatorScreenBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED.get(), (blockEntity, side) -> ((AutomaticPixelatorScreenActivatedBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ROUTER.get(), (blockEntity, side) -> ((RouterBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ROUTER_ACTIVATED.get(), (blockEntity, side) -> ((RouterActivatedBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PIXELATOR_CAMERA_LEFT.get(), (blockEntity, side) -> ((PixelatorCameraLeftBlockEntity) blockEntity).getItemHandler());
	}
}