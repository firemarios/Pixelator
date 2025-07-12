/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.PixelatorMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

public class PixelatorModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, PixelatorMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> PIXELATOR_CAMERA_TURNING = REGISTRY.register("pixelator_camera_turning",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_camera_turning")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PIXELATOR_CAMERA_SPAWN = REGISTRY.register("pixelator_camera_spawn",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_camera_spawn")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PIXELATOR_SCREEN_LEVER = REGISTRY.register("pixelator_screen_lever",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_screen_lever")));
	public static final DeferredHolder<SoundEvent, SoundEvent> AUTOMATIC_PIXELATOR_SCREEN_ACTIVATION = REGISTRY.register("automatic_pixelator_screen_activation",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "automatic_pixelator_screen_activation")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PIXELATOR_SCREEN_ACTIVATE = REGISTRY.register("pixelator_screen_activate",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("pixelator", "pixelator_screen_activate")));
}