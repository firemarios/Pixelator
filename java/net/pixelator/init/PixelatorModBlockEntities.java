/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.block.entity.*;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;

public class PixelatorModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PixelatorMod.MODID);
	public static final RegistryObject<BlockEntityType<PixelatorCameraRightBlockEntity>> PIXELATOR_CAMERA_RIGHT = register("pixelator_camera_right", PixelatorModBlocks.PIXELATOR_CAMERA_RIGHT, PixelatorCameraRightBlockEntity::new);
	public static final RegistryObject<BlockEntityType<PixelatorScreenBlockEntity>> PIXELATOR_SCREEN = register("pixelator_screen", PixelatorModBlocks.PIXELATOR_SCREEN, PixelatorScreenBlockEntity::new);
	public static final RegistryObject<BlockEntityType<AutomaticPixelatorScreenBlockEntity>> AUTOMATIC_PIXELATOR_SCREEN = register("automatic_pixelator_screen", PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN, AutomaticPixelatorScreenBlockEntity::new);
	public static final RegistryObject<BlockEntityType<AutomaticPixelatorScreenActivatedBlockEntity>> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED = register("automatic_pixelator_screen_activated", PixelatorModBlocks.AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED,
			AutomaticPixelatorScreenActivatedBlockEntity::new);
	public static final RegistryObject<BlockEntityType<RouterBlockEntity>> ROUTER = register("router", PixelatorModBlocks.ROUTER, RouterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<RouterActivatedBlockEntity>> ROUTER_ACTIVATED = register("router_activated", PixelatorModBlocks.ROUTER_ACTIVATED, RouterActivatedBlockEntity::new);
	public static final RegistryObject<BlockEntityType<PixelatorCameraLeftBlockEntity>> PIXELATOR_CAMERA_LEFT = register("pixelator_camera_left", PixelatorModBlocks.PIXELATOR_CAMERA_LEFT, PixelatorCameraLeftBlockEntity::new);
	public static final RegistryObject<BlockEntityType<ServerBlockEntity>> SERVER = register("server", PixelatorModBlocks.SERVER, ServerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<ComputerBlockEntity>> COMPUTER = register("computer", PixelatorModBlocks.COMPUTER, ComputerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<AlarmBlockBlockEntity>> ALARM_BLOCK = register("alarm_block", PixelatorModBlocks.ALARM_BLOCK, AlarmBlockBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}