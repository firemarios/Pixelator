package net.pixelator.client.model;

import net.minecraft.world.entity.ai.behavior.Mount;
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

import com.mojang.datafixers.optics.Lens;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelpixelator_camera<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("pixelator", "modelpixelator_camera"), "main");
	public final ModelPart Mount;
	public final ModelPart Arm;
	public final ModelPart Lens;

	public Modelpixelator_camera(ModelPart root) {
		this.Mount = root.getChild("Mount");
		this.Arm = root.getChild("Arm");
		this.Lens = root.getChild("Lens");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Mount = partdefinition.addOrReplaceChild("Mount", CubeListBuilder.create(), PartPose.offset(8.0F, 24.0F, -8.0F));
		PartDefinition cube_r1 = Mount.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(0, 10).addBox(-11.0F, -6.0F, 0.0F, 6.0F, 6.0F, 0.5F, new CubeDeformation(0.0F)).texOffs(22, 10).addBox(-10.5F, -5.5F, 0.5F, 0.5F, 0.5F, 0.2F, new CubeDeformation(0.0F)).texOffs(12, 22)
						.addBox(-10.5F, -1.0F, 0.5F, 0.5F, 0.5F, 0.2F, new CubeDeformation(0.0F)).texOffs(22, 11).addBox(-6.0F, -1.0F, 0.5F, 0.5F, 0.5F, 0.2F, new CubeDeformation(0.0F)).texOffs(12, 17)
						.addBox(-6.0F, -5.5F, 0.5F, 0.5F, 0.5F, 0.2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-16.0F, 0.0F, 16.0F, -3.1416F, 0.0F, -3.1416F));
		PartDefinition Arm = partdefinition.addOrReplaceChild("Arm", CubeListBuilder.create(), PartPose.offset(8.0F, 24.0F, -8.0F));
		PartDefinition cube_r2 = Arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 21).addBox(-8.45F, -5.0F, 3.15F, 0.7F, 1.5F, 0.7F, new CubeDeformation(0.0F)).texOffs(14, 14)
				.addBox(-8.62F, -3.62F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(14, 10).addBox(-8.45F, -3.45F, 0.5F, 0.7F, 0.7F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-16.0F, 0.0F, 16.0F, -3.1416F, 0.0F, -3.1416F));
		PartDefinition Lens = partdefinition.addOrReplaceChild("Lens", CubeListBuilder.create(), PartPose.offsetAndRotation(0.1F, 19.25F, 4.5F, 0.48F, 0.0F, 0.0F));
		PartDefinition cube_r3 = Lens.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 17).addBox(-8.62F, -5.62F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.1F, 1.75F, -0.5F, -3.1416F, 0.0F, -3.1416F));
		PartDefinition cube_r4 = Lens.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(14, 16).addBox(-8.62F, -5.62F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-9.62F, -8.0F, 2.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 21)
						.addBox(-8.84F, -6.0F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 18).addBox(-8.59F, -5.75F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(12, 20)
						.addBox(-8.34F, -5.75F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(16, 20).addBox(-8.09F, -5.75F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 6)
						.addBox(-7.84F, -5.75F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 8).addBox(-7.59F, -6.0F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(18, 14)
						.addBox(-7.34F, -6.25F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(4, 17).addBox(-7.34F, -6.5F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(8, 17)
						.addBox(-7.34F, -6.75F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(12, 18).addBox(-7.34F, -7.0F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 20)
						.addBox(-7.59F, -7.25F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 4).addBox(-7.84F, -7.5F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 2)
						.addBox(-8.09F, -7.5F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(8, 19).addBox(-8.34F, -7.5F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(20, 0)
						.addBox(-8.59F, -7.5F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(4, 21).addBox(-8.84F, -7.25F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(4, 19)
						.addBox(-9.09F, -7.0F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(0, 19).addBox(-9.09F, -6.75F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(16, 18)
						.addBox(-9.09F, -6.5F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)).texOffs(18, 16).addBox(-9.09F, -6.25F, 9.0F, 0.25F, 0.25F, 1.5F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.1F, 4.75F, 3.5F, -3.1416F, 0.0F, -3.1416F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Mount.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Lens.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}