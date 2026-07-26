package net.pixelator.client.gui;

import net.pixelator.world.inventory.AlarmSettingsMenu;
import net.pixelator.procedures.*;
import net.pixelator.network.AlarmSettingsButtonMessage;
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

public class AlarmSettingsScreen extends AbstractContainerScreen<AlarmSettingsMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_off;
	private ImageButton imagebutton_off1;
	private ImageButton imagebutton_off2;
	private ImageButton imagebutton_off3;
	private ImageButton imagebutton_on;
	private ImageButton imagebutton_on1;
	private ImageButton imagebutton_on2;
	private ImageButton imagebutton_on3;
	private ImageButton imagebutton_x;
	private ImageButton imagebutton_x1;
	private ImageButton imagebutton_x2;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("pixelator:textures/screens/alarm_settings.png");
	private static final ResourceLocation IMAGE_0 = new ResourceLocation("pixelator:textures/screens/title_island.png");

	public AlarmSettingsScreen(AlarmSettingsMenu container, Inventory inventory, Component text) {
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
		if (AlarmSettingsSlider1XVisibilityProcedure.execute(world, entity))
			if (mouseX > leftPos + 6 && mouseX < leftPos + 78 && mouseY > topPos + 29 && mouseY < topPos + 49) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.alarm_settings.tooltip_motion_detection_module_is_missi"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (AlarmSettingsSlider2XVisibilityProcedure.execute(world, entity))
			if (mouseX > leftPos + 6 && mouseX < leftPos + 78 && mouseY > topPos + 56 && mouseY < topPos + 76) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.alarm_settings.tooltip_player_detection_module_is_missi"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (AlarmSettingsSlider3XVisibilityProcedure.execute(world, entity))
			if (mouseX > leftPos + 6 && mouseX < leftPos + 78 && mouseY > topPos + 110 && mouseY < topPos + 130) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.pixelator.alarm_settings.tooltip_redstone_module_is_missing"), mouseX, mouseY);
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
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(IMAGE_0, this.leftPos + 6, this.topPos + -7, 0, 0, 120, 15, 120, 15);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_redstone_output"), 87, 115, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_alarm_triggered_result"), 30, -2, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_alarm_output"), 87, 142, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_auto_trigger"), 6, 13, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_trigger_output"), 6, 92, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_motion_detection"), 87, 34, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.alarm_settings.label_player_detection"), 87, 61, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_off = new ImageButton(this.leftPos + 6, this.topPos + 29, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_off.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(0, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_off);
		imagebutton_off1 = new ImageButton(this.leftPos + 6, this.topPos + 56, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_off1.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(1, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_off1);
		imagebutton_off2 = new ImageButton(this.leftPos + 6, this.topPos + 110, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_off2.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(2, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_off2);
		imagebutton_off3 = new ImageButton(this.leftPos + 6, this.topPos + 137, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_off3.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(3, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_off3);
		imagebutton_on = new ImageButton(this.leftPos + 6, this.topPos + 29, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_on.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (AlarmSettingsSlider1OnVisibilityProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(4, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_on);
		imagebutton_on1 = new ImageButton(this.leftPos + 6, this.topPos + 56, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_on1.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (AlarmSettingsSlider2OnVisibilityProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(5, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_on1);
		imagebutton_on2 = new ImageButton(this.leftPos + 6, this.topPos + 110, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_on2.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (AlarmSettingsSlider3OnVisibilityProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(6, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_on2);
		imagebutton_on3 = new ImageButton(this.leftPos + 6, this.topPos + 137, 72, 20, 0, 0, 20, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_on3.png"), 72, 40, e -> {
			int x = AlarmSettingsScreen.this.x;
			int y = AlarmSettingsScreen.this.y;
			if (AlarmSettingsSlider4OnVisibilityProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new AlarmSettingsButtonMessage(7, x, y, z));
				AlarmSettingsButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_on3);
		imagebutton_x = new ImageButton(this.leftPos + 34, this.topPos + 31, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_x.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x);
		imagebutton_x1 = new ImageButton(this.leftPos + 34, this.topPos + 58, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_x1.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x1);
		imagebutton_x2 = new ImageButton(this.leftPos + 34, this.topPos + 112, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_x2.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_x2);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.imagebutton_on.visible = AlarmSettingsSlider1OnVisibilityProcedure.execute(world, entity);
		this.imagebutton_on1.visible = AlarmSettingsSlider2OnVisibilityProcedure.execute(world, entity);
		this.imagebutton_on2.visible = AlarmSettingsSlider3OnVisibilityProcedure.execute(world, entity);
		this.imagebutton_on3.visible = AlarmSettingsSlider4OnVisibilityProcedure.execute(world, entity);
		this.imagebutton_x.visible = AlarmSettingsSlider1XVisibilityProcedure.execute(world, entity);
		this.imagebutton_x1.visible = AlarmSettingsSlider2XVisibilityProcedure.execute(world, entity);
		this.imagebutton_x2.visible = AlarmSettingsSlider3XVisibilityProcedure.execute(world, entity);
	}
}