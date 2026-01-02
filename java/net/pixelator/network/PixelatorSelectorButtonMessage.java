package net.pixelator.network;

import net.pixelator.procedures.TeleportationMenuPreviousPageProcedure;
import net.pixelator.procedures.TeleportationMenuNextPageProcedure;
import net.pixelator.procedures.PixelatorTeleportOpenSearchProcedure;
import net.pixelator.procedures.PixelatorTeleportCam6BtnPProcedure;
import net.pixelator.procedures.PixelatorTeleportCam5BtnPProcedure;
import net.pixelator.procedures.PixelatorTeleportCam4BtnPProcedure;
import net.pixelator.procedures.PixelatorTeleportCam3BtnPProcedure;
import net.pixelator.procedures.PixelatorTeleportCam2BtnPProcedure;
import net.pixelator.procedures.PixelatorTeleportCam1BtnPProcedure;
import net.pixelator.PixelatorMod;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PixelatorSelectorButtonMessage {
	private final int buttonID, x, y, z;

	public PixelatorSelectorButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public PixelatorSelectorButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(PixelatorSelectorButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(PixelatorSelectorButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TeleportationMenuNextPageProcedure.execute(world, entity);
		}
		if (buttonID == 1) {

			TeleportationMenuPreviousPageProcedure.execute(entity);
		}
		if (buttonID == 2) {

			PixelatorTeleportCam1BtnPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			PixelatorTeleportCam2BtnPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			PixelatorTeleportCam3BtnPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			PixelatorTeleportCam4BtnPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			PixelatorTeleportCam5BtnPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			PixelatorTeleportCam6BtnPProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 8) {

			PixelatorTeleportOpenSearchProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(PixelatorSelectorButtonMessage.class, PixelatorSelectorButtonMessage::buffer, PixelatorSelectorButtonMessage::new, PixelatorSelectorButtonMessage::handler);
	}
}