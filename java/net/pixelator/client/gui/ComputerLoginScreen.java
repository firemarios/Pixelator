package net.pixelator.client.gui;

import net.pixelator.world.inventory.ComputerLoginMenu;
import net.pixelator.network.ComputerLoginButtonMessage;
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

public class ComputerLoginScreen extends AbstractContainerScreen<ComputerLoginMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox computerLoginUsername;
	private EditBox computerLoginPassword;
	private ImageButton imagebutton_done_btn;

	public ComputerLoginScreen(ComputerLoginMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 150;
		this.imageHeight = 100;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("computerLoginUsername"))
				computerLoginUsername.setValue(stringState);
			else if (name.equals("computerLoginPassword"))
				computerLoginPassword.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("pixelator:textures/screens/computer_login.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		computerLoginUsername.render(guiGraphics, mouseX, mouseY, partialTicks);
		computerLoginPassword.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/title_island.png"), this.leftPos + 11, this.topPos + -5, 0, 0, 120, 15, 120, 15);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/arrow_exit.png"), this.leftPos + 146, this.topPos + 75, 0, 0, 12, 19, 12, 19);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (computerLoginUsername.isFocused())
			return computerLoginUsername.keyPressed(key, b, c);
		if (computerLoginPassword.isFocused())
			return computerLoginPassword.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String computerLoginUsernameValue = computerLoginUsername.getValue();
		String computerLoginPasswordValue = computerLoginPassword.getValue();
		super.resize(minecraft, width, height);
		computerLoginUsername.setValue(computerLoginUsernameValue);
		computerLoginPassword.setValue(computerLoginPasswordValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.computer_login.label_computer_login"), 36, 0, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		computerLoginUsername = new EditBox(this.font, this.leftPos + 16, this.topPos + 21, 118, 18, Component.translatable("gui.pixelator.computer_login.computerLoginUsername"));
		computerLoginUsername.setMaxLength(8192);
		computerLoginUsername.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "computerLoginUsername", content, false);
		});
		computerLoginUsername.setHint(Component.translatable("gui.pixelator.computer_login.computerLoginUsername"));
		this.addWidget(this.computerLoginUsername);
		computerLoginPassword = new EditBox(this.font, this.leftPos + 16, this.topPos + 51, 118, 18, Component.translatable("gui.pixelator.computer_login.computerLoginPassword"));
		computerLoginPassword.setMaxLength(8192);
		computerLoginPassword.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "computerLoginPassword", content, false);
		});
		computerLoginPassword.setHint(Component.translatable("gui.pixelator.computer_login.computerLoginPassword"));
		this.addWidget(this.computerLoginPassword);
		imagebutton_done_btn = new ImageButton(this.leftPos + 125, this.topPos + 75, 18, 18, 0, 0, 18, ResourceLocation.parse("pixelator:textures/screens/atlas/imagebutton_done_btn.png"), 18, 36, e -> {
			int x = ComputerLoginScreen.this.x;
			int y = ComputerLoginScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ComputerLoginButtonMessage(0, x, y, z));
				ComputerLoginButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_done_btn);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		computerLoginUsername.tick();
		computerLoginPassword.tick();
	}
}