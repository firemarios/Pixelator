package net.pixelator.client.gui;

import net.pixelator.world.inventory.PixelatorSelectorMenu;
import net.pixelator.procedures.*;
import net.pixelator.network.PixelatorSelectorButtonMessage;
import net.pixelator.init.PixelatorModScreens;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

public class PixelatorSelectorScreen extends AbstractContainerScreen<PixelatorSelectorMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_empty;
	private Button button_empty1;
	private Button button_t;
	private Button button_t1;
	private Button button_t2;
	private Button button_t3;
	private Button button_t4;
	private Button button_t5;
	private Button button_search;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("pixelator:textures/screens/pixelator_selector.png");
	private static final ResourceLocation IMAGE_0 = new ResourceLocation("pixelator:textures/screens/title_island.png");

	public PixelatorSelectorScreen(PixelatorSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 155;
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
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(IMAGE_0, this.leftPos + 8, this.topPos + -6, 0, 0, 120, 15, 120, 15);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.pixelator_selector.label_teleportation_menu"), 21, -1, -12829636, false);
		if (PixelatorTeleportCam1TextVProcedure.execute(entity))
			guiGraphics.drawString(this.font, PixelatorTeleportCam1TextProcedure.execute(entity), 8, 16, -12829636, false);
		if (PixelatorTeleportCam2TextVProcedure.execute(entity))
			guiGraphics.drawString(this.font, PixelatorTeleportCam2TextProcedure.execute(entity), 8, 34, -12829636, false);
		if (PixelatorTeleportCam3TextVProcedure.execute(entity))
			guiGraphics.drawString(this.font, PixelatorTeleportCam3TextProcedure.execute(entity), 8, 52, -12829636, false);
		if (PixelatorTeleportCam4TextVProcedure.execute(entity))
			guiGraphics.drawString(this.font, PixelatorTeleportCam4TextProcedure.execute(entity), 8, 70, -12829636, false);
		if (PixelatorTeleportCam5TextVProcedure.execute(entity))
			guiGraphics.drawString(this.font, PixelatorTeleportCam5TextProcedure.execute(entity), 8, 88, -12829636, false);
		if (PixelatorTeleportCam6TextVProcedure.execute(entity))
			guiGraphics.drawString(this.font, PixelatorTeleportCam6TextProcedure.execute(entity), 8, 106, -12829636, false);
		if (PixelatorTeleportNoCamRegTextProcedure.execute(world))
			guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.pixelator_selector.label_no_cameras_registed"), 8, 16, -6710887, false);
	}

	@Override
	public void init() {
		super.init();
		button_empty = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_empty"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportNextBtnProcedure.execute(world, entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(0, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 127, 30, 20).build();
		this.addRenderableWidget(button_empty);
		button_empty1 = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_empty1"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportPreviousBtnProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(1, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 8, this.topPos + 127, 30, 20).build();
		this.addRenderableWidget(button_empty1);
		button_t = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_t"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportCam1TextVProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(2, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 10, 30, 20).build();
		this.addRenderableWidget(button_t);
		button_t1 = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_t1"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportCam2TextVProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(3, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 28, 30, 20).build();
		this.addRenderableWidget(button_t1);
		button_t2 = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_t2"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportCam3TextVProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(4, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 46, 30, 20).build();
		this.addRenderableWidget(button_t2);
		button_t3 = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_t3"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportCam4TextVProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(5, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 64, 30, 20).build();
		this.addRenderableWidget(button_t3);
		button_t4 = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_t4"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportCam5TextVProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(6, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 82, 30, 20).build();
		this.addRenderableWidget(button_t4);
		button_t5 = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_t5"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (PixelatorTeleportCam6TextVProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(7, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 143, this.topPos + 100, 30, 20).build();
		this.addRenderableWidget(button_t5);
		button_search = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_search"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(8, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		}).bounds(this.leftPos + 62, this.topPos + 127, 56, 20).build();
		this.addRenderableWidget(button_search);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.button_empty.visible = PixelatorTeleportNextBtnProcedure.execute(world, entity);
		this.button_empty1.visible = PixelatorTeleportPreviousBtnProcedure.execute(entity);
		this.button_t.visible = PixelatorTeleportCam1TextVProcedure.execute(entity);
		this.button_t1.visible = PixelatorTeleportCam2TextVProcedure.execute(entity);
		this.button_t2.visible = PixelatorTeleportCam3TextVProcedure.execute(entity);
		this.button_t3.visible = PixelatorTeleportCam4TextVProcedure.execute(entity);
		this.button_t4.visible = PixelatorTeleportCam5TextVProcedure.execute(entity);
		this.button_t5.visible = PixelatorTeleportCam6TextVProcedure.execute(entity);
	}
}