package net.pixelator.client.gui;

import net.pixelator.world.inventory.CameraViewPropertiesMenu;
import net.pixelator.procedures.*;
import net.pixelator.network.CameraViewPropertiesButtonMessage;
import net.pixelator.init.PixelatorModScreens;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

public class CameraViewPropertiesScreen extends AbstractContainerScreen<CameraViewPropertiesMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_night_vision_disabled;
	private ImageButton imagebutton_night_vision;
	private ImageButton imagebutton_x;
	private ImageButton imagebutton_glowing_disabled;
	private ImageButton imagebutton_glowing;
	private ImageButton imagebutton_x1;
	private ImageButton imagebutton_alarm_disabled;
	private ImageButton imagebutton_alarm_disabled1;
	private ImageButton imagebutton_x2;
	private ImageButton imagebutton_alarm_manual;
	private ImageButton imagebutton_alarm_manual1;
	private ImageButton imagebutton_x3;
	private ImageButton imagebutton_wrench;

	public CameraViewPropertiesScreen(CameraViewPropertiesMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		boolean customTooltipShown = false;
		if (mouseX > leftPos + 6 && mouseX < leftPos + 24 && mouseY > topPos + 7 && mouseY < topPos + 25) {
			guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.camera_view_properties.tooltip_night_vision"), mouseX, mouseY);
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 6 && mouseX < leftPos + 24 && mouseY > topPos + 25 && mouseY < topPos + 43) {
			guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.camera_view_properties.tooltip_entity_glowing"), mouseX, mouseY);
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 6 && mouseX < leftPos + 24 && mouseY > topPos + 43 && mouseY < topPos + 61) {
			guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.camera_view_properties.tooltip_alarm"), mouseX, mouseY);
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 6 && mouseX < leftPos + 24 && mouseY > topPos + 61 && mouseY < topPos + 79) {
			guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.camera_view_properties.tooltip_manual_alarm_activation"), mouseX, mouseY);
			customTooltipShown = true;
		}
		if (AlarmEnabledSettingsProcedure.execute(world, entity))
			if (mouseX > leftPos + 24 && mouseX < leftPos + 42 && mouseY > topPos + 43 && mouseY < topPos + 61) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.camera_view_properties.tooltip_alarm_settings"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		imagebutton_night_vision_disabled = new ImageButton(this.leftPos + 6, this.topPos + 7, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_night_vision_disabled.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(0, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_night_vision_disabled);
		imagebutton_night_vision = new ImageButton(this.leftPos + 6, this.topPos + 7, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_night_vision.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (NightVisionEnabledBtnProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(1, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_night_vision);
		imagebutton_x = new ImageButton(this.leftPos + 8, this.topPos + 9, 16, 16, 0, 0, 16, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_x.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x);
		imagebutton_glowing_disabled = new ImageButton(this.leftPos + 6, this.topPos + 25, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_glowing_disabled.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(3, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_glowing_disabled);
		imagebutton_glowing = new ImageButton(this.leftPos + 6, this.topPos + 25, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_glowing.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (EntityGlowingEnabledBtnProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(4, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_glowing);
		imagebutton_x1 = new ImageButton(this.leftPos + 7, this.topPos + 26, 16, 16, 0, 0, 16, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_x1.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x1);
		imagebutton_alarm_disabled = new ImageButton(this.leftPos + 6, this.topPos + 43, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_alarm_disabled.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(6, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_alarm_disabled);
		imagebutton_alarm_disabled1 = new ImageButton(this.leftPos + 6, this.topPos + 43, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_alarm_disabled1.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (AlarmEnabledBtnProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(7, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_alarm_disabled1);
		imagebutton_x2 = new ImageButton(this.leftPos + 7, this.topPos + 44, 16, 16, 0, 0, 16, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_x2.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x2);
		imagebutton_alarm_manual = new ImageButton(this.leftPos + 6, this.topPos + 61, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_alarm_manual.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(9, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 9, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_alarm_manual);
		imagebutton_alarm_manual1 = new ImageButton(this.leftPos + 6, this.topPos + 61, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_alarm_manual1.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (ManualAlarmEnabledBtnProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(10, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 10, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_alarm_manual1);
		imagebutton_x3 = new ImageButton(this.leftPos + 7, this.topPos + 62, 16, 16, 0, 0, 16, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_x3.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x3);
		imagebutton_wrench = new ImageButton(this.leftPos + 24, this.topPos + 43, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_wrench.png"), 18, 36, e -> {
			int x = CameraViewPropertiesScreen.this.x;
			int y = CameraViewPropertiesScreen.this.y;
			if (AlarmEnabledSettingsProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraViewPropertiesButtonMessage(12, x, y, z));
				CameraViewPropertiesButtonMessage.handleButtonAction(entity, 12, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_wrench);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.imagebutton_night_vision.visible = NightVisionEnabledBtnProcedure.execute(world, entity);
		this.imagebutton_x.visible = NightVisionEnabledXProcedure.execute(world, entity);
		this.imagebutton_glowing.visible = EntityGlowingEnabledBtnProcedure.execute(world, entity);
		this.imagebutton_x1.visible = EntityGlowingEnabledXProcedure.execute(world, entity);
		this.imagebutton_alarm_disabled1.visible = AlarmEnabledBtnProcedure.execute(world, entity);
		this.imagebutton_x2.visible = AlarmEnabledXProcedure.execute(world, entity);
		this.imagebutton_alarm_manual1.visible = ManualAlarmEnabledBtnProcedure.execute(world, entity);
		this.imagebutton_x3.visible = AlarmEnabledXProcedure.execute(world, entity);
		this.imagebutton_wrench.visible = AlarmEnabledSettingsProcedure.execute(world, entity);
	}
}