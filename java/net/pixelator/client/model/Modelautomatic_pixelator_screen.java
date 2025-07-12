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

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelautomatic_pixelator_screen<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("pixelator", "modelautomatic_pixelator_screen"), "main");
	public final ModelPart Base;
	public final ModelPart Rod1;
	public final ModelPart Dish1;
	public final ModelPart BaseOpening;

	public Modelautomatic_pixelator_screen(ModelPart root) {
		this.Base = root.getChild("Base");
		this.Rod1 = root.getChild("Rod1");
		this.Dish1 = root.getChild("Dish1");
		this.BaseOpening = root.getChild("BaseOpening");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(0, 56).addBox(-4.5F, 7.0F, -4.5F, 9.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 22)
				.addBox(-8.0F, 14.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 39).addBox(-8.0F, 6.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));
		PartDefinition Rod1 = partdefinition.addOrReplaceChild("Rod1", CubeListBuilder.create(), PartPose.offset(7.0F, 19.0F, 5.0F));
		PartDefinition cube_r1 = Rod1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(36, 56).addBox(-1.0F, -13.0F, 0.0F, 1.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		PartDefinition Dish1 = partdefinition.addOrReplaceChild("Dish1",
				CubeListBuilder.create().texOffs(40, 56).addBox(-0.5F, 0.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(52, 56).addBox(-0.5F, 0.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 58)
						.addBox(-0.5F, 0.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(60, 6).addBox(-0.5F, -1.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(60, 8)
						.addBox(-0.5F, -1.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 20.0F, 1.0F, -1.309F, 0.0F, 0.0F));
		PartDefinition cube_r2 = Dish1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(60, 12).addBox(-0.75F, -4.0F, -0.75F, 0.5F, 4.0F, 0.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r3 = Dish1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 60).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 0.0F, -2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = Dish1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 60).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 0.0F, -2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r5 = Dish1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(60, 19).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 0.0F, 2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r6 = Dish1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(60, 17).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 0.0F, 2.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r7 = Dish1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 10).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r8 = Dish1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(60, 4).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r9 = Dish1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(60, 2).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 1.0F, -1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r10 = Dish1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(60, 0).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 1.0F, -1.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition BaseOpening = partdefinition.addOrReplaceChild("BaseOpening", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -7.0F, -7.5F, 15.0F, 7.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Rod1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Dish1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		BaseOpening.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}