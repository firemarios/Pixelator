package net.pixelator.client.renderer.block;

import net.pixelator.procedures.PixelatorCameraAnimationSpawnProcedure;
import net.pixelator.procedures.PixelatorCameraAnimationIdleProcedure;
import net.pixelator.init.PixelatorModBlockEntities;
import net.pixelator.client.model.animations.pixelator_cameraAnimation;
import net.pixelator.client.model.Modelpixelator_camera;
import net.pixelator.block.entity.PixelatorCameraRightBlockEntity;
import net.pixelator.block.PixelatorCameraRightBlock;

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
public class PixelatorCameraRightRenderer implements BlockEntityRenderer<PixelatorCameraRightBlockEntity> {
	private final CustomHierarchicalModel model;
	private final ResourceLocation texture;

	public PixelatorCameraRightRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new CustomHierarchicalModel(context.bakeLayer(Modelpixelator_camera.LAYER_LOCATION));
		this.texture = new ResourceLocation("pixelator", "textures/block/pixelator_camera.png");
	}

	private void updateRenderState(PixelatorCameraRightBlockEntity blockEntity) {
		if (blockEntity.getLevel() == null) return;
		int tickCount = (int) blockEntity.getLevel().getGameTime();

		boolean spawnAnim = PixelatorCameraAnimationSpawnProcedure.execute(
			blockEntity.getLevel(),
			blockEntity.getBlockPos().getX(),
			blockEntity.getBlockPos().getY(),
			blockEntity.getBlockPos().getZ()
		);

		boolean idleAnim = PixelatorCameraAnimationIdleProcedure.execute(
			blockEntity.getLevel(),
			blockEntity.getBlockPos().getX(),
			blockEntity.getBlockPos().getY(),
			blockEntity.getBlockPos().getZ()
		);

		blockEntity.animationState0.animateWhen(spawnAnim, tickCount);
		blockEntity.animationState1.animateWhen(idleAnim, tickCount);
	}

	@Override
	public void render(PixelatorCameraRightBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource renderer, int light, int overlayLight) {
		updateRenderState(blockEntity);

		poseStack.pushPose();
		poseStack.scale(-1, -1, 1);
		poseStack.translate(-0.5, -0.5, 0.5);

		BlockState state = blockEntity.getBlockState();
		Direction facing = state.getValue(PixelatorCameraRightBlock.FACING);
		switch (facing) {
			case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(90));
			case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90));
			case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));
			default -> {}
		}

		poseStack.translate(0, -1, 0);
		VertexConsumer builder = renderer.getBuffer(RenderType.entityCutout(texture));
		model.setupBlockEntityAnim(blockEntity, blockEntity.getLevel().getGameTime() + partialTick);
		model.renderToBuffer(poseStack, builder, light, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(
			(net.minecraft.world.level.block.entity.BlockEntityType<PixelatorCameraRightBlockEntity>) 
			(Object) PixelatorModBlockEntities.PIXELATOR_CAMERA_RIGHT.get(), 
			PixelatorCameraRightRenderer::new
		);
	}

	private static final class CustomHierarchicalModel extends Modelpixelator_camera {
		private final ModelPart root;
		private final BlockEntityHierarchicalModel animator;

		public CustomHierarchicalModel(ModelPart root) {
			super(root);
			this.root = root;
			this.animator = new BlockEntityHierarchicalModel(root);
		}

		public void setupBlockEntityAnim(PixelatorCameraRightBlockEntity blockEntity, float ageInTicks) {
			animator.setupBlockEntityAnim(blockEntity, ageInTicks);
			super.setupAnim(null, 0, 0, ageInTicks, 0, 0);
		}

		public ModelPart getRoot() {
			return root;
		}

		private static class BlockEntityHierarchicalModel extends HierarchicalModel<Entity> {
			private final ModelPart rootPart;

			public BlockEntityHierarchicalModel(ModelPart root) {
				this.rootPart = root;
			}

			@Override
			public ModelPart root() {
				return rootPart;
			}

			@Override
			public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				// no entity animation here
			}

			public void setupBlockEntityAnim(PixelatorCameraRightBlockEntity blockEntity, float ageInTicks) {
				rootPart.getAllParts().forEach(ModelPart::resetPose);
				this.animate(blockEntity.animationState0, pixelator_cameraAnimation.right, ageInTicks, 1f);
				this.animate(blockEntity.animationState1, pixelator_cameraAnimation.idle, ageInTicks, 1f);
			}
		}
	}
}
