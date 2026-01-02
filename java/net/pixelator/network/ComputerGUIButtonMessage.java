package net.pixelator.network;

import net.pixelator.procedures.*;
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
public record ComputerGUIButtonMessage(int buttonID, int x, int y, int z) {
	public ComputerGUIButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	public static void buffer(ComputerGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(ComputerGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> handleButtonAction(context.getSender(), message.buttonID, message.x, message.y, message.z));
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ComputerNextPressProcedure.execute(entity);
		}
		if (buttonID == 1) {

			ComputerPreviousPressProcedure.execute(entity);
		}
		if (buttonID == 2) {

			ComputerCam1PressProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			ComputerCam2PressProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			ComputerCam3PressProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			ComputerCam4PressProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(ComputerGUIButtonMessage.class, ComputerGUIButtonMessage::buffer, ComputerGUIButtonMessage::new, ComputerGUIButtonMessage::handler);
	}
}