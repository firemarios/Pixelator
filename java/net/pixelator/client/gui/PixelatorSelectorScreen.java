package net.pixelator.client.gui;

import net.pixelator.world.inventory.PixelatorSelectorMenu;
import net.pixelator.network.PixelatorSelectorButtonMessage;
import net.pixelator.init.PixelatorModScreens;
import net.pixelator.PixelatorMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;

public class PixelatorSelectorScreen extends AbstractContainerScreen<PixelatorSelectorMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox teleportname;
	Button button_go;

	public PixelatorSelectorScreen(PixelatorSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 202;
		this.imageHeight = 41;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("pixelator:textures/screens/pixelator_selector.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		teleportname.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/pixelator_camera_left.png"), this.leftPos + 9, this.topPos + 12, 0, 0, 16, 16, 16, 16);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (teleportname.isFocused())
			return teleportname.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		teleportname.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String teleportnameValue = teleportname.getValue();
		super.resize(minecraft, width, height);
		teleportname.setValue(teleportnameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		teleportname = new EditBox(this.font, this.leftPos + 30, this.topPos + 11, 118, 18, Component.translatable("gui.pixelator.pixelator_selector.teleportname"));
		teleportname.setHint(Component.translatable("gui.pixelator.pixelator_selector.teleportname"));
		teleportname.setMaxLength(8192);
		teleportname.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "teleportname", content, false);
		});
		this.addWidget(this.teleportname);
		button_go = Button.builder(Component.translatable("gui.pixelator.pixelator_selector.button_go"), e -> {
			int x = PixelatorSelectorScreen.this.x;
			int y = PixelatorSelectorScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorSelectorButtonMessage(0, x, y, z));
				PixelatorSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 156, this.topPos + 10, 35, 20).build();
		this.addRenderableWidget(button_go);
	}
}