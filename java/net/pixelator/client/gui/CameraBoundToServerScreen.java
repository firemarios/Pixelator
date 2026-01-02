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
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;

public class CameraBoundToServerScreen extends AbstractContainerScreen<CameraBoundToServerMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox cameraboundname;
	Button button_done;

	public CameraBoundToServerScreen(CameraBoundToServerMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 215;
		this.imageHeight = 70;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("pixelator:textures/screens/camera_bound_to_server.png");

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
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/cable.png"), this.leftPos + 5, this.topPos + 5, 0, 0, 24, 24, 24, 24);
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
	protected void containerTick() {
		super.containerTick();
		cameraboundname.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String cameraboundnameValue = cameraboundname.getValue();
		super.resize(minecraft, width, height);
		cameraboundname.setValue(cameraboundnameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.camera_bound_to_server.label_network_name"), 8, 40, -12829636, false);
		guiGraphics.drawString(this.font, GuiGetServerNetworkNameCableProcedure.execute(entity), 8, 49, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		cameraboundname = new EditBox(this.font, this.leftPos + 35, this.topPos + 8, 118, 18, Component.translatable("gui.pixelator.camera_bound_to_server.cameraboundname"));
		cameraboundname.setHint(Component.translatable("gui.pixelator.camera_bound_to_server.cameraboundname"));
		cameraboundname.setMaxLength(8192);
		cameraboundname.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "cameraboundname", content, false);
		});
		this.addWidget(this.cameraboundname);
		button_done = Button.builder(Component.translatable("gui.pixelator.camera_bound_to_server.button_done"), e -> {
			int x = CameraBoundToServerScreen.this.x;
			int y = CameraBoundToServerScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new CameraBoundToServerButtonMessage(0, x, y, z));
				CameraBoundToServerButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 159, this.topPos + 7, 46, 20).build();
		this.addRenderableWidget(button_done);
	}
}