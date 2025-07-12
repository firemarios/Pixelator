/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class PixelatorModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PixelatorMod.MODID);
	public static final RegistryObject<SoundEvent> PIXELATOR_CAMERA_TURNING = REGISTRY.register("pixelator_camera_turning", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_camera_turning")));
	public static final RegistryObject<SoundEvent> PIXELATOR_CAMERA_SPAWN = REGISTRY.register("pixelator_camera_spawn", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_camera_spawn")));
	public static final RegistryObject<SoundEvent> PIXELATOR_SCREEN_LEVER = REGISTRY.register("pixelator_screen_lever", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_screen_lever")));
	public static final RegistryObject<SoundEvent> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATION = REGISTRY.register("automatic_pixelator_screen_activation",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "automatic_pixelator_screen_activation")));
	public static final RegistryObject<SoundEvent> PIXELATOR_SCREEN_ACTIVATE = REGISTRY.register("pixelator_screen_activate", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_screen_activate")));
}