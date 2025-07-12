/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.pixelator.init;

import net.pixelator.PixelatorMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

public class PixelatorModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PixelatorMod.MODID);
	public static final RegistryObject<SimpleParticleType> PIXELATOR_CAMERA_SPAWN_PARTICLE = REGISTRY.register("pixelator_camera_spawn_particle", () -> new SimpleParticleType(true));
}