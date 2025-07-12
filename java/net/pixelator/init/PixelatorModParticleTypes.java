/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.PixelatorMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

public class PixelatorModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, PixelatorMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PIXELATOR_CAMERA_SPAWN_PARTICLE = REGISTRY.register("pixelator_camera_spawn_particle", () -> new SimpleParticleType(true));
}