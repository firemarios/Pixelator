package net.pixelator.client.gui;

import net.pixelator.world.inventory.CameraBoundToServerMenu;
import net.pixelator.procedures.GuiGetServerNetworkNameCableProcedure;
import net.pixelator.network.CameraBoundToServerButtonMessage;
import net.pixelator.init.PixelatorModScreens;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;

public class CameraBoundToServerScreen extends AbstractContainerScreen<CameraBoundToServerMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox cameraboundname;
	private ImageButton imagebutton_done_btn;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("pixelator:textures/screens/camera_bound_to_server.png");
	private static final ResourceLocation IMAGE_0 = new ResourceLocation("pixelator:textures/screens/cable.png");
	private static final ResourceLocation IMAGE_1 = new ResourceLocation("pixelator:textures/screens/title_island.png");
	private static final ResourceLocation IMAGE_2 = new ResourceLocation("pixelator:textures/screens/arrow_exit.png");
	private static final ResourceLocation IMAGE_3 = new ResourceLocation("pixelator:textures/screens/title_island_extend.png");

	public CameraBoundToServerScreen(CameraBoundToServerMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 70;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("cameraboundname"))
				cameraboundname.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		cameraboundname.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(IMAGE_0, this.leftPos + 9, this.topPos + 12, 0, 0, 24, 24, 24, 24);
		guiGraphics.blit(IMAGE_1, this.leftPos + 8, this.topPos + -6, 0, 0, 120, 15, 120, 15);
		guiGraphics.blit(IMAGE_2, this.leftPos + 196, this.topPos + 44, 0, 0, 12, 19, 12, 19);
		guiGraphics.blit(IMAGE_3, this.leftPos + 33, this.topPos + -6, 0, 0, 116, 15, 116, 15);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (cameraboundname.isFocused())
			return cameraboundname.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String cameraboundnameValue = cameraboundname.getValue();
		super.resize(minecraft, width, height);
		cameraboundname.setValue(cameraboundnameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.camera_bound_to_server.label_network_name"), 9, 40, -12829636, false);
		guiGraphics.drawString(this.font, GuiGetServerNetworkNameCableProcedure.execute(entity), 9, 49, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.camera_bound_to_server.label_bind_camera_to_server"), 24, -1, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		cameraboundname = new EditBox(this.font, this.leftPos + 37, this.topPos + 15, 153, 20, Component.translatable("gui.pixelator.camera_bound_to_server.cameraboundname"));
		cameraboundname.setMaxLength(8192);
		cameraboundname.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "cameraboundname", content, false);
		});
		cameraboundname.setHint(Component.translatable("gui.pixelator.camera_bound_to_server.cameraboundname"));
		this.addWidget(this.cameraboundname);
		imagebutton_done_btn = new ImageButton(this.leftPos + 171, this.topPos + 44, 18, 18, 0, 0, 18, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_done_btn.png"), 18, 36, e -> {
			int x = CameraBoundToServerScreen.this.x;
			int y = CameraBoundToServerScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraBoundToServerButtonMessage(0, x, y, z));
				CameraBoundToServerButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_done_btn);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		cameraboundname.tick();
	}
}