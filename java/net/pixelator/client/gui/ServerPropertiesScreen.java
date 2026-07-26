package net.pixelator.client.gui;

import net.pixelator.world.inventory.ServerPropertiesMenu;
import net.pixelator.procedures.GuiGetServerNetworkNameProcedure;
import net.pixelator.procedures.GuiGetServerCheckboxTeleportAllProcedure;
import net.pixelator.procedures.GuiGetServerCamerasProcedure;
import net.pixelator.network.ServerPropertiesButtonMessage;
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
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;

public class ServerPropertiesScreen extends AbstractContainerScreen<ServerPropertiesMenu> implements PixelatorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox network_name;
	private Checkbox everybody_teleport;
	private ImageButton imagebutton_done_btn;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("pixelator:textures/screens/server_properties.png");
	private static final ResourceLocation IMAGE_0 = new ResourceLocation("pixelator:textures/screens/usb_hint.png");
	private static final ResourceLocation IMAGE_1 = new ResourceLocation("pixelator:textures/screens/title_island.png");

	public ServerPropertiesScreen(ServerPropertiesMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 380;
		this.imageHeight = 168;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("network_name"))
				network_name.setValue(stringState);
		}
		if (elementType == 1 && elementState instanceof Boolean logicState) {
			if (name.equals("everybody_teleport")) {
				if (everybody_teleport.selected() != logicState)
					everybody_teleport.onPress();
			}
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		network_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(IMAGE_0, this.leftPos + 51, this.topPos + 120, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(IMAGE_1, this.leftPos + 6, this.topPos + -4, 0, 0, 120, 15, 120, 15);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (network_name.isFocused())
			return network_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String network_nameValue = network_name.getValue();
		super.resize(minecraft, width, height);
		network_name.setValue(network_nameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.server_properties.label_server"), 49, 1, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.server_properties.label_cameras_bounded"), 5, 23, -12829636, false);
		guiGraphics.drawString(this.font, GuiGetServerCamerasProcedure.execute(world, x, y, z), 5, 32, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.server_properties.label_can_eveyone_teleport"), 5, 50, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.server_properties.label_allow_list"), 212, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.server_properties.label_network_name"), 5, 95, -12829636, false);
		guiGraphics.drawString(this.font, GuiGetServerNetworkNameProcedure.execute(world, x, y, z), 5, 104, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.pixelator.server_properties.label_storage"), 5, 123, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		network_name = new EditBox(this.font, this.leftPos + 5, this.topPos + 140, 120, 20, Component.translatable("gui.pixelator.server_properties.network_name"));
		network_name.setMaxLength(8192);
		network_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "network_name", content, false);
		});
		network_name.setHint(Component.translatable("gui.pixelator.server_properties.network_name"));
		this.addWidget(this.network_name);
		imagebutton_done_btn = new ImageButton(this.leftPos + 130, this.topPos + 141, 18, 18, 0, 0, 18, new ResourceLocation("pixelator:textures/screens/atlas/imagebutton_done_btn.png"), 18, 36, e -> {
			int x = ServerPropertiesScreen.this.x;
			int y = ServerPropertiesScreen.this.y;
			if (true) {
				PixelatorMod.PACKET_HANDLER.sendToServer(new ServerPropertiesButtonMessage(0, x, y, z));
				ServerPropertiesButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_done_btn);
		boolean everybody_teleportSelected = GuiGetServerCheckboxTeleportAllProcedure.execute(world, x, y, z);
		everybody_teleport = new Checkbox(this.leftPos + 5, this.topPos + 66, 20, 20, Component.translatable("gui.pixelator.server_properties.everybody_teleport"), everybody_teleportSelected) {
			@Override
			public void onPress() {
				super.onPress();
				if (!menuStateUpdateActive)
					menu.sendMenuStateUpdate(entity, 1, "everybody_teleport", this.selected(), false);
			}
		};
		if (everybody_teleportSelected)
			menu.sendMenuStateUpdate(entity, 1, "everybody_teleport", true, false);
		this.addRenderableWidget(everybody_teleport);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		network_name.tick();
	}
}