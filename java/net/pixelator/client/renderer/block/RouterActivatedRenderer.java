package net.pixelator.client.renderer.block;

import net.pixelator.init.PixelatorModBlockEntities;
import net.pixelator.client.model.animations.RouterActivatedAnimation;
import net.pixelator.client.model.ModelRouterActivated;
import net.pixelator.block.entity.RouterActivatedBlockEntity;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RouterActivatedRenderer implements BlockEntityRenderer<RouterActivatedBlockEntity> {
	private final CustomHierarchicalModel model;
	private final ResourceLocation texture;

	RouterActivatedRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new CustomHierarchicalModel(context.bakeLayer(ModelRouterActivated.LAYER_LOCATION));
		this.texture = ResourceLocation.parse("pixelator:textures/block/router.png");
	}

	private void updateRenderState(RouterActivatedBlockEntity blockEntity) {
		int tickCount = (int) blockEntity.getLevel().getGameTime();
		blockEntity.animationState0.animateWhen(true, tickCount);
	}

	@Override
	public void render(RouterActivatedBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource renderer, int light, int overlayLight) {
		updateRenderState(blockEntity);
		poseStack.pushPose();
		poseStack.scale(-1, -1, 1);
		poseStack.translate(-0.5, -0.5, 0.5);
		poseStack.translate(0, -1, 0);
		VertexConsumer builder = renderer.getBuffer(RenderType.entityCutout(texture));
		model.setupBlockEntityAnim(blockEntity, blockEntity.getLevel().getGameTime() + partialTick);
		model.renderToBuffer(poseStack, builder, light, overlayLight);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer((BlockEntityType<RouterActivatedBlockEntity>) PixelatorModBlockEntities.ROUTER_ACTIVATED.get(), RouterActivatedRenderer::new);
	}

	private static final class CustomHierarchicalModel extends ModelRouterActivated {
		private final ModelPart root;
		private final BlockEntityHierarchicalModel animator = new BlockEntityHierarchicalModel();

		public CustomHierarchicalModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		public void setupBlockEntityAnim(RouterActivatedBlockEntity blockEntity, float ageInTicks) {
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

			public void setupBlockEntityAnim(RouterActivatedBlockEntity blockEntity, float ageInTicks) {
				animator.root().getAllParts().forEach(ModelPart::resetPose);
				animator.animate(blockEntity.animationState0, RouterActivatedAnimation.idle, ageInTicks, 1f);
			}
		}
	}
}