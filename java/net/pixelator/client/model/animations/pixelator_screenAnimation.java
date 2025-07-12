package net.pixelator.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 4.12.5 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class pixelator_screenAnimation {
	public static final AnimationDefinition lever = AnimationDefinition.Builder.withLength(1.0833F).addAnimation("Lever", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5F, KeyframeAnimations.degreeVec(90.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition start = AnimationDefinition.Builder.withLength(0.0F).build();
}