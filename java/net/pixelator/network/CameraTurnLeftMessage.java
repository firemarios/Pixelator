package net.pixelator.network;

import net.pixelator.PixelatorMod;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CameraTurnLeftMessage {
	int type, pressedms;

	public CameraTurnLeftMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public CameraTurnLeftMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(CameraTurnLeftMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(CameraTurnLeftMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(CameraTurnLeftMessage.class, CameraTurnLeftMessage::buffer, CameraTurnLeftMessage::new, CameraTurnLeftMessage::handler);
	}
}