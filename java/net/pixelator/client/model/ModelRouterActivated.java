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
public class ModelRouterActivated<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("pixelator", "model_router_activated"), "main");
	public final ModelPart Dish1;
	public final ModelPart Dish2;
	public final ModelPart Pillar1;
	public final ModelPart antena;
	public final ModelPart Pillar2;
	public final ModelPart bb_main;

	public ModelRouterActivated(ModelPart root) {
		this.Dish1 = root.getChild("Dish1");
		this.Dish2 = root.getChild("Dish2");
		this.Pillar1 = root.getChild("Pillar1");
		this.antena = root.getChild("antena");
		this.Pillar2 = root.getChild("Pillar2");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Dish1 = partdefinition.addOrReplaceChild("Dish1",
				CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(44, 26).addBox(-3.0F, -1.0F, -5.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 38)
						.addBox(-5.0F, -1.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(44, 29).addBox(-3.0F, -1.0F, 3.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 43)
						.addBox(3.0F, -1.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 13.0F, 0.0F));
		PartDefinition Dish2 = partdefinition.addOrReplaceChild("Dish2",
				CubeListBuilder.create().texOffs(0, 17).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(36, 35).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 26)
						.addBox(-6.0F, -1.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(16, 35).addBox(-4.0F, -1.0F, 4.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 17)
						.addBox(4.0F, -1.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 17.0F, 0.0F));
		PartDefinition Pillar1 = partdefinition.addOrReplaceChild("Pillar1", CubeListBuilder.create().texOffs(0, 33).addBox(-10.0F, -7.0F, 6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));
		PartDefinition antena = partdefinition.addOrReplaceChild("antena",
				CubeListBuilder.create().texOffs(16, 45).addBox(-1.5F, 0.0F, 7.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 32).addBox(-2.0F, -1.0F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, 8.0F, -8.0F));
		PartDefinition Pillar2 = partdefinition.addOrReplaceChild("Pillar2", CubeListBuilder.create().texOffs(16, 38).addBox(-3.0F, -3.0F, 6.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 16.0F, -8.0F));
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Dish1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Dish2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Pillar1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		antena.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Pillar2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}