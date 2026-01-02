package net.pixelator.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.gui.screens.Screen;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelpixelator_screen<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("pixelator", "modelpixelator_screen"), "main");
	public final ModelPart Screen;
	public final ModelPart MainBoard;
	public final ModelPart Lever;

	public Modelpixelator_screen(ModelPart root) {
		this.Screen = root.getChild("Screen");
		this.MainBoard = root.getChild("MainBoard");
		this.Lever = root.getChild("Lever");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Screen = partdefinition.addOrReplaceChild("Screen",
				CubeListBuilder.create().texOffs(0, 18).addBox(-7.5F, -15.5F, -1.5F, 31.0F, 15.0F, 0.5F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-8.0F, -16.0F, -1.0F, 32.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 7.0F));
		PartDefinition MainBoard = partdefinition.addOrReplaceChild("MainBoard",
				CubeListBuilder.create().texOffs(36, 45).addBox(-0.25F, 11.0F, -1.0F, 1.0F, 0.5F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 34).addBox(-10.0F, 0.0F, -0.75F, 12.0F, 12.0F, 1.75F, new CubeDeformation(0.0F)).texOffs(44, 40)
						.addBox(0.75F, 1.5F, -1.0F, 0.5F, 9.5F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 45).addBox(-0.75F, 1.5F, -1.0F, 0.5F, 9.5F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 34)
						.addBox(-9.0F, 1.5F, -1.0F, 6.5F, 9.5F, 0.5F, new CubeDeformation(0.0F)).texOffs(40, 45).addBox(-0.25F, 1.0F, -1.0F, 1.0F, 0.5F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-10.0F, 12.0F, 7.0F));
		PartDefinition Lever = partdefinition.addOrReplaceChild("Lever",
				CubeListBuilder.create().texOffs(44, 34).addBox(-0.25F, -1.0F, -4.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 45).addBox(-0.4F, -1.15F, -5.5F, 1.3F, 1.3F, 1.3F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.0F, 19.0F, 6.5F, -0.8727F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Screen.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		MainBoard.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Lever.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}