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

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelalarm_block_Converted<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("pixelator", "modelalarm_block_converted"), "main");
	public final ModelPart bone;

	public Modelalarm_block_Converted(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone = partdefinition.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(0, 18).addBox(-13.0F, -2.0F, 3.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(4, 0).addBox(-11.0F, -8.0F, 5.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-11.0F, -9.0F, 4.0F, 1.0F, 7.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-6.0F, -9.0F, 12.0F, 1.0F, 7.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-8.5F, -9.0F, 12.0F, 1.0F, 7.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-11.0F, -9.0F, 12.0F, 1.0F, 7.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-8.5F, -9.0F, 4.0F, 1.0F, 7.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-6.0F, -9.0F, 4.0F, 1.0F, 7.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(7, 19)
						.addBox(-12.1F, -9.0F, 10.0F, 0.1F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(1, 19).addBox(-4.1F, -9.0F, 5.0F, 0.1F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 19)
						.addBox(-4.1F, -9.0F, 7.5F, 0.1F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 19).addBox(-4.1F, -9.0F, 10.0F, 0.1F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 19)
						.addBox(-12.1F, -9.0F, 5.0F, 0.1F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 19).addBox(-12.1F, -9.0F, 7.5F, 0.1F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.0F, 8.0F, 8.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 0).addBox(-1.1F, -7.0F, 1.5F, 0.1F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
				.addBox(-1.1F, -7.0F, -1.0F, 0.1F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 19).addBox(-1.1F, -7.0F, 4.0F, 0.1F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -10.0F, 6.0F, 0.0F, 0.0F, -1.5708F));
		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.0F, -7.0F, -8.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -7.0F, 12.0F, 0.0F, 0.0F, -1.5708F));
		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, -4.0F, 11.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, -7.0F, 11.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -4.0F, 11.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition cube_r6 = bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -7.0F, 11.0F, 1.5708F, 0.0F, -1.5708F));
		PartDefinition cube_r7 = bone.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.0F, -7.0F, 8.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -4.0F, 4.0F, 0.0F, 0.0F, -1.5708F));
		PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -8.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0)
				.addBox(-4.0F, -8.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(1.0F, -8.0F, 0.0F, 1.0F, 8.0F, 0.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -9.0F, 12.0F, 1.5708F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}