/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.block.*;
import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class PixelatorModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, PixelatorMod.MODID);
	public static final RegistryObject<Block> PIXELATOR_CAMERA_RIGHT;
	public static final RegistryObject<Block> PIXELATOR_SCREEN;
	public static final RegistryObject<Block> AUTOMATIC_PIXELATOR_SCREEN;
	public static final RegistryObject<Block> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED;
	public static final RegistryObject<Block> ROUTER;
	public static final RegistryObject<Block> ROUTER_ACTIVATED;
	public static final RegistryObject<Block> PIXELATOR_CAMERA_LEFT;
	public static final RegistryObject<Block> SERVER;
	public static final RegistryObject<Block> COMPUTER;
	public static final RegistryObject<Block> ALARM_BLOCK;
	public static final RegistryObject<Block> REDSTONE_EMITTER_MODULE;
	static {
		PIXELATOR_CAMERA_RIGHT = REGISTRY.register("pixelator_camera_right", PixelatorCameraRightBlock::new);
		PIXELATOR_SCREEN = REGISTRY.register("pixelator_screen", PixelatorScreenBlock::new);
		AUTOMATIC_PIXELATOR_SCREEN = REGISTRY.register("automatic_pixelator_screen", AutomaticPixelatorScreenBlock::new);
		AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED = REGISTRY.register("automatic_pixelator_screen_activated", AutomaticPixelatorScreenActivatedBlock::new);
		ROUTER = REGISTRY.register("router", RouterBlock::new);
		ROUTER_ACTIVATED = REGISTRY.register("router_activated", RouterActivatedBlock::new);
		PIXELATOR_CAMERA_LEFT = REGISTRY.register("pixelator_camera_left", PixelatorCameraLeftBlock::new);
		SERVER = REGISTRY.register("server", ServerBlock::new);
		COMPUTER = REGISTRY.register("computer", ComputerBlock::new);
		ALARM_BLOCK = REGISTRY.register("alarm_block", AlarmBlockBlock::new);
		REDSTONE_EMITTER_MODULE = REGISTRY.register("redstone_emitter_module", RedstoneEmitterModuleBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}