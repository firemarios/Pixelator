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

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

public class PixelatorModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(PixelatorMod.MODID);
	public static final DeferredBlock<Block> PIXELATOR_CAMERA_RIGHT = REGISTRY.register("pixelator_camera_right", PixelatorCameraRightBlock::new);
	public static final DeferredBlock<Block> PIXELATOR_SCREEN = REGISTRY.register("pixelator_screen", PixelatorScreenBlock::new);
	public static final DeferredBlock<Block> AUTOMATIC_PIXELATOR_SCREEN = REGISTRY.register("automatic_pixelator_screen", AutomaticPixelatorScreenBlock::new);
	public static final DeferredBlock<Block> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED = REGISTRY.register("automatic_pixelator_screen_activated", AutomaticPixelatorScreenActivatedBlock::new);
	public static final DeferredBlock<Block> ROUTER = REGISTRY.register("router", RouterBlock::new);
	public static final DeferredBlock<Block> ROUTER_ACTIVATED = REGISTRY.register("router_activated", RouterActivatedBlock::new);
	public static final DeferredBlock<Block> PIXELATOR_CAMERA_LEFT = REGISTRY.register("pixelator_camera_left", PixelatorCameraLeftBlock::new);
	// Start of user code block custom blocks
	// End of user code block custom blocks
}