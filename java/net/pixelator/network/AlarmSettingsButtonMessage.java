package net.pixelator.network;

import net.pixelator.procedures.AlarmSettings4PressProcedure;
import net.pixelator.procedures.AlarmSettings3PressProcedure;
import net.pixelator.procedures.AlarmSettings2PressProcedure;
import net.pixelator.procedures.AlarmSettings1PressProcedure;
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
public record AlarmSettingsButtonMessage(int buttonID, int x, int y, int z) {
	public AlarmSettingsButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	public static void buffer(AlarmSettingsButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(AlarmSettingsButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			AlarmSettings1PressProcedure.execute(world, entity);
		}
		if (buttonID == 1) {

			AlarmSettings2PressProcedure.execute(world, entity);
		}
		if (buttonID == 2) {

			AlarmSettings3PressProcedure.execute(world, entity);
		}
		if (buttonID == 3) {

			AlarmSettings4PressProcedure.execute(world, entity);
		}
		if (buttonID == 4) {

			AlarmSettings1PressProcedure.execute(world, entity);
		}
		if (buttonID == 5) {

			AlarmSettings2PressProcedure.execute(world, entity);
		}
		if (buttonID == 6) {

			AlarmSettings3PressProcedure.execute(world, entity);
		}
		if (buttonID == 7) {

			AlarmSettings4PressProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(AlarmSettingsButtonMessage.class, AlarmSettingsButtonMessage::buffer, AlarmSettingsButtonMessage::new, AlarmSettingsButtonMessage::handler);
	}
}