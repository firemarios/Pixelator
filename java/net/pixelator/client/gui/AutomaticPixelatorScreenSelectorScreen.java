package net.pixelator.client.gui;

import net.pixelator.world.inventory.AutomaticPixelatorScreenSelectorMenu;
import net.pixelator.network.AutomaticPixelatorScreenSelectorButtonMessage;
import net.pixelator.init.PixelatorModScreens;

import net.neoforged.neoforge.network.PacketDistributor;

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

public class AutomaticPixelatorScreenSelectorScreen extends AbstractContainerScreen<AutomaticPixelatorScreenSelectorMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox automaticscreenteleportname;
	Button button_done;

	public AutomaticPixelatorScreenSelectorScreen(AutomaticPixelatorScreenSelectorMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 215;
		this.imageHeight = 39;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("automaticscreenteleportname"))
				automaticscreenteleportname.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("pixelator:textures/screens/automatic_pixelator_screen_selector.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		automaticscreenteleportname.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("pixelator:textures/screens/automatic_pixelator_screen_item.png"), this.leftPos + 10, this.topPos + 11, 0, 0, 16, 16, 16, 16);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (automaticscreenteleportname.isFocused())
			return automaticscreenteleportname.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String automaticscreenteleportnameValue = automaticscreenteleportname.getValue();
		super.resize(minecraft, width, height);
		automaticscreenteleportname.setValue(automaticscreenteleportnameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		automaticscreenteleportname = new EditBox(this.font, this.leftPos + 33, this.topPos + 10, 118, 18, Component.translatable("gui.pixelator.automatic_pixelator_screen_selector.automaticscreenteleportname"));
		automaticscreenteleportname.setMaxLength(8192);
		automaticscreenteleportname.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "automaticscreenteleportname", content, false);
		});
		automaticscreenteleportname.setHint(Component.translatable("gui.pixelator.automatic_pixelator_screen_selector.automaticscreenteleportname"));
		this.addWidget(this.automaticscreenteleportname);
		button_done = Button.builder(Component.translatable("gui.pixelator.automatic_pixelator_screen_selector.button_done"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new AutomaticPixelatorScreenSelectorButtonMessage(0, x, y, z));
				AutomaticPixelatorScreenSelectorButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 156, this.topPos + 9, 46, 20).build();
		this.addRenderableWidget(button_done);
	}
}