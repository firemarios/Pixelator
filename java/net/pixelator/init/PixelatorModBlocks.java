/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.block.RouterBlock;
import net.pixelator.block.RouterActivatedBlock;
import net.pixelator.block.PixelatorScreenBlock;
import net.pixelator.block.PixelatorCameraRightBlock;
import net.pixelator.block.PixelatorCameraLeftBlock;
import net.pixelator.block.AutomaticPixelatorScreenBlock;
import net.pixelator.block.AutomaticPixelatorScreenActivatedBlock;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class PixelatorModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, PixelatorMod.MODID);
	public static final RegistryObject<Block> PIXELATOR_CAMERA_RIGHT = REGISTRY.register("pixelator_camera_right", () -> new PixelatorCameraRightBlock());
	public static final RegistryObject<Block> PIXELATOR_SCREEN = REGISTRY.register("pixelator_screen", () -> new PixelatorScreenBlock());
	public static final RegistryObject<Block> AUTOMATIC_PIXELATOR_SCREEN = REGISTRY.register("automatic_pixelator_screen", () -> new AutomaticPixelatorScreenBlock());
	public static final RegistryObject<Block> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED = REGISTRY.register("automatic_pixelator_screen_activated", () -> new AutomaticPixelatorScreenActivatedBlock());
	public static final RegistryObject<Block> ROUTER = REGISTRY.register("router", () -> new RouterBlock());
	public static final RegistryObject<Block> ROUTER_ACTIVATED = REGISTRY.register("router_activated", () -> new RouterActivatedBlock());
	public static final RegistryObject<Block> PIXELATOR_CAMERA_LEFT = REGISTRY.register("pixelator_camera_left", () -> new PixelatorCameraLeftBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}