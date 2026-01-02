package net.pixelator.client.gui;

import net.pixelator.world.inventory.PixelatorCameraSearchMenu;
import net.pixelator.network.PixelatorCameraSearchButtonMessage;
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

public class PixelatorCameraSearchScreen extends AbstractContainerScreen<PixelatorCameraSearchMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox searchTeleport;
	EditBox network;
	Button button_done;

	public PixelatorCameraSearchScreen(PixelatorCameraSearchMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 140;
		this.imageHeight = 110;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("pixelator:textures/screens/pixelator_camera_search.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		searchTeleport.render(guiGraphics, mouseX, mouseY, partialTicks);
		network.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/search_icon.png"), this.leftPos + 1, this.topPos + 1, 0, 0, 16, 16, 16, 16);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (searchTeleport.isFocused())
			return searchTeleport.keyPressed(key, b, c);
		if (network.isFocused())
			return network.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		searchTeleport.tick();
		network.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String searchTeleportValue = searchTeleport.getValue();
		String networkValue = network.getValue();
		super.resize(minecraft, width, height);
		searchTeleport.setValue(searchTeleportValue);
		network.setValue(networkValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.pixelator_camera_search.label_search"), 16, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		searchTeleport = new EditBox(this.font, this.leftPos + 11, this.topPos + 52, 118, 18, Component.translatable("gui.pixelator.pixelator_camera_search.searchTeleport"));
		searchTeleport.setHint(Component.translatable("gui.pixelator.pixelator_camera_search.searchTeleport"));
		searchTeleport.setMaxLength(8192);
		searchTeleport.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "searchTeleport", content, false);
		});
		this.addWidget(this.searchTeleport);
		network = new EditBox(this.font, this.leftPos + 11, this.topPos + 25, 118, 18, Component.translatable("gui.pixelator.pixelator_camera_search.network"));
		network.setHint(Component.translatable("gui.pixelator.pixelator_camera_search.network"));
		network.setMaxLength(8192);
		network.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "network", content, false);
		});
		this.addWidget(this.network);
		button_done = Button.builder(Component.translatable("gui.pixelator.pixelator_camera_search.button_done"), e -> {
			int x = PixelatorCameraSearchScreen.this.x;
			int y = PixelatorCameraSearchScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new PixelatorCameraSearchButtonMessage(0, x, y, z));
				PixelatorCameraSearchButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 46, this.topPos + 78, 46, 20).build();
		this.addRenderableWidget(button_done);
	}
}