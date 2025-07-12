package net.pixelator.client.renderer.block;

import net.pixelator.init.PixelatorModBlockEntities;
import net.pixelator.client.model.animations.automatic_pixelator_screen_activatedAnimation;
import net.pixelator.client.model.Modelautomatic_pixelator_screen_activated;
import net.pixelator.block.entity.AutomaticPixelatorScreenActivatedBlockEntity;
import net.pixelator.block.AutomaticPixelatorScreenActivatedBlock;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AutomaticPixelatorScreenActivatedRenderer implements BlockEntityRenderer<AutomaticPixelatorScreenActivatedBlockEntity> {
	private final CustomHierarchicalModel model;
	private final ResourceLocation texture;

	AutomaticPixelatorScreenActivatedRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new CustomHierarchicalModel(context.bakeLayer(Modelautomatic_pixelator_screen_activated.LAYER_LOCATION));
		this.texture = ResourceLocation.parse("pixelator:textures/block/automatic_pixelator_screen.png");
	}

	private void updateRenderState(AutomaticPixelatorScreenActivatedBlockEntity blockEntity) {
		int tickCount = (int) blockEntity.getLevel().getGameTime();
		blockEntity.animationState0.animateWhen(true, tickCount);
	}

	@Override
	public void render(AutomaticPixelatorScreenActivatedBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource renderer, int light, int overlayLight) {
		updateRenderState(blockEntity);
		poseStack.pushPose();
		poseStack.scale(-1, -1, 1);
		poseStack.translate(-0.5, -0.5, 0.5);
		BlockState state = blockEntity.getBlockState();
		Direction facing = state.getValue(AutomaticPixelatorScreenActivatedBlock.FACING);
		switch (facing) {
			case NORTH -> {
			}
			case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(90));
			case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90));
			case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));
		}
		poseStack.translate(0, -1, 0);
		VertexConsumer builder = renderer.getBuffer(RenderType.entityCutout(texture));
		model.setupBlockEntityAnim(blockEntity, blockEntity.getLevel().getGameTime() + partialTick);
		model.renderToBuffer(poseStack, builder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
	}

	@SubscribeEvent
public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(
        (net.minecraft.world.level.block.entity.BlockEntityType<AutomaticPixelatorScreenActivatedBlockEntity>) PixelatorModBlockEntities.AUTOMATIC_PIXELATOR_SCREEN_ACTIVATED.get(),
        AutomaticPixelatorScreenActivatedRenderer::new
    );
}


	private static final class CustomHierarchicalModel extends Modelautomatic_pixelator_screen_activated {
		private final ModelPart root;
		private final BlockEntityHierarchicalModel animator = new BlockEntityHierarchicalModel();

		public CustomHierarchicalModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		public void setupBlockEntityAnim(AutomaticPixelatorScreenActivatedBlockEntity blockEntity, float ageInTicks) {
			animator.setupBlockEntityAnim(blockEntity, ageInTicks);
			super.setupAnim(null, 0, 0, ageInTicks, 0, 0);
		}

		public ModelPart getRoot() {
			return root;
		}

		private class BlockEntityHierarchicalModel extends HierarchicalModel<Entity> {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			}

			public void setupBlockEntityAnim(AutomaticPixelatorScreenActivatedBlockEntity blockEntity, float ageInTicks) {
				animator.root().getAllParts().forEach(ModelPart::resetPose);
				animator.animate(blockEntity.animationState0, automatic_pixelator_screen_activatedAnimation.idle, ageInTicks, 1f);
			}
		}
	}
}