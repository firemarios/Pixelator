package net.pixelator.client.gui;

import net.pixelator.world.inventory.ComputerGUIMenu;
import net.pixelator.procedures.*;
import net.pixelator.network.ComputerGUIButtonMessage;
import net.pixelator.init.PixelatorModScreens;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

public class ComputerGUIScreen extends AbstractContainerScreen<ComputerGUIMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_empty;
	private Button button_empty1;
	private ImageButton imagebutton_camera_box;
	private ImageButton imagebutton_camera_box1;
	private ImageButton imagebutton_camera_box2;
	private ImageButton imagebutton_camera_box3;
	private ImageButton imagebutton_pixelator_camera_left;
	private ImageButton imagebutton_pixelator_camera_left1;
	private ImageButton imagebutton_pixelator_camera_left2;
	private ImageButton imagebutton_pixelator_camera_left3;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("pixelator:textures/screens/computer_gui.png");
	private static final ResourceLocation IMAGE_0 = new ResourceLocation("pixelator:textures/screens/title_island.png");

	public ComputerGUIScreen(ComputerGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 175;
		this.imageHeight = 160;
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
		guiGraphics.blit(IMAGE_0, this.leftPos + 9, this.topPos + -5, 0, 0, 120, 15, 120, 15);
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
		if (ComputerCam1VProcedure.execute(entity))
			guiGraphics.drawString(this.font, ComputerCam1LProcedure.execute(entity), 6, 55, -12829636, false);
		if (ComputerCam2VProcedure.execute(entity))
			guiGraphics.drawString(this.font, ComputerCam2LProcedure.execute(entity), 96, 55, -12829636, false);
		if (ComputerCam3VProcedure.execute(entity))
			guiGraphics.drawString(this.font, ComputerCam3LProcedure.execute(entity), 6, 109, -12829636, false);
		if (ComputerCam4VProcedure.execute(entity))
			guiGraphics.drawString(this.font, ComputerCam4LProcedure.execute(entity), 96, 109, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.computer_gui.label_camera_management"), 21, 0, -12829636, false);
		if (ComputerNoCamsVProcedure.execute(entity))
			guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.computer_gui.label_no_cameras_connected"), 33, 22, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_empty = Button.builder(Component.translatable("gui.pixelator.computer_gui.button_empty"), e -> {
			int x = ComputerGUIScreen.this.x;
			int y = ComputerGUIScreen.this.y;
			if (ComputerNextBtnProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerGUIButtonMessage(0, x, y, z));
				ComputerGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 137, this.topPos + 133, 30, 20).build();
		this.addRenderableWidget(button_empty);
		button_empty1 = Button.builder(Component.translatable("gui.pixelator.computer_gui.button_empty1"), e -> {
			int x = ComputerGUIScreen.this.x;
			int y = ComputerGUIScreen.this.y;
			if (ComputerPreviousBtnProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerGUIButtonMessage(1, x, y, z));
				ComputerGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 7, this.topPos + 133, 30, 20).build();
		this.addRenderableWidget(button_empty1);
		imagebutton_camera_box = new ImageButton(this.leftPos + 7, this.topPos + 13, 70, 40, 0, 0, 40, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_camera_box.png"), 70, 80, e -> {
			int x = ComputerGUIScreen.this.x;
			int y = ComputerGUIScreen.this.y;
			if (ComputerCam1VProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerGUIButtonMessage(2, x, y, z));
				ComputerGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_camera_box);
		imagebutton_camera_box1 = new ImageButton(this.leftPos + 97, this.topPos + 13, 70, 40, 0, 0, 40, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_camera_box1.png"), 70, 80, e -> {
			int x = ComputerGUIScreen.this.x;
			int y = ComputerGUIScreen.this.y;
			if (ComputerCam2VProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerGUIButtonMessage(3, x, y, z));
				ComputerGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_camera_box1);
		imagebutton_camera_box2 = new ImageButton(this.leftPos + 7, this.topPos + 67, 70, 40, 0, 0, 40, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_camera_box2.png"), 70, 80, e -> {
			int x = ComputerGUIScreen.this.x;
			int y = ComputerGUIScreen.this.y;
			if (ComputerCam3VProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerGUIButtonMessage(4, x, y, z));
				ComputerGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_camera_box2);
		imagebutton_camera_box3 = new ImageButton(this.leftPos + 97, this.topPos + 67, 70, 40, 0, 0, 40, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_camera_box3.png"), 70, 80, e -> {
			int x = ComputerGUIScreen.this.x;
			int y = ComputerGUIScreen.this.y;
			if (ComputerCam4VProcedure.execute(entity)) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerGUIButtonMessage(5, x, y, z));
				ComputerGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_camera_box3);
		imagebutton_pixelator_camera_left = new ImageButton(this.leftPos + 34, this.topPos + 23, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_pixelator_camera_left.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_pixelator_camera_left);
		imagebutton_pixelator_camera_left1 = new ImageButton(this.leftPos + 125, this.topPos + 24, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_pixelator_camera_left1.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_pixelator_camera_left1);
		imagebutton_pixelator_camera_left2 = new ImageButton(this.leftPos + 35, this.topPos + 78, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_pixelator_camera_left2.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_pixelator_camera_left2);
		imagebutton_pixelator_camera_left3 = new ImageButton(this.leftPos + 125, this.topPos + 79, 16, 16, 0, 0, 16, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_pixelator_camera_left3.png"), 16, 32, e -> {
		});
		this.addRenderableWidget(imagebutton_pixelator_camera_left3);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.button_empty.visible = ComputerNextBtnProcedure.execute(entity);
		this.button_empty1.visible = ComputerPreviousBtnProcedure.execute(entity);
		this.imagebutton_camera_box.visible = ComputerCam1VProcedure.execute(entity);
		this.imagebutton_camera_box1.visible = ComputerCam2VProcedure.execute(entity);
		this.imagebutton_camera_box2.visible = ComputerCam3VProcedure.execute(entity);
		this.imagebutton_camera_box3.visible = ComputerCam4VProcedure.execute(entity);
		this.imagebutton_pixelator_camera_left.visible = ComputerCam1VProcedure.execute(entity);
		this.imagebutton_pixelator_camera_left1.visible = ComputerCam2VProcedure.execute(entity);
		this.imagebutton_pixelator_camera_left2.visible = ComputerCam3VProcedure.execute(entity);
		this.imagebutton_pixelator_camera_left3.visible = ComputerCam4VProcedure.execute(entity);
	}
}