package net.pixelator.client.gui;

import net.pixelator.world.inventory.CameraBoundMenu;
import net.pixelator.network.CameraBoundButtonMessage;
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

public class CameraBoundScreen extends AbstractContainerScreen<CameraBoundMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox cameraboundname;
	private ImageButton imagebutton_done_btn;

	public CameraBoundScreen(CameraBoundMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 184;
		this.imageHeight = 39;
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

	private static final ResourceLocation texture = ResourceLocation.parse("pixelator:textures/screens/camera_bound.png");

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
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/bounder.png"), this.leftPos + 7, this.topPos + 11, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/arrow_exit.png"), this.leftPos + 180, this.topPos + 10, 0, 0, 12, 19, 12, 19);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/title_island.png"), this.leftPos + 7, this.topPos + -9, 0, 0, 120, 15, 120, 15);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.camera_bound.label_bind_camera"), 38, -4, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		cameraboundname = new EditBox(this.font, this.leftPos + 27, this.topPos + 10, 118, 18, Component.translatable("gui.pixelator.camera_bound.cameraboundname"));
		cameraboundname.setMaxLength(8192);
		cameraboundname.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "cameraboundname", content, false);
		});
		cameraboundname.setHint(Component.translatable("gui.pixelator.camera_bound.cameraboundname"));
		this.addWidget(this.cameraboundname);
		imagebutton_done_btn = new ImageButton(this.leftPos + 154, this.topPos + 10, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_done_btn.png"), 18, 36, e -> {
			int x = CameraBoundScreen.this.x;
			int y = CameraBoundScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraBoundButtonMessage(0, x, y, z));
				CameraBoundButtonMessage.handleButtonAction(entity, 0, x, y, z);
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