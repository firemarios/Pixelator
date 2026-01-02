package net.pixelator.network;

import net.pixelator.procedures.NightVisionModuleGUIPressProcedure;
import net.pixelator.procedures.ManualAlarmModuleGUIPressProcedure;
import net.pixelator.procedures.EntityGlowingModuleGUIPressProcedure;
import net.pixelator.procedures.AlarmSettingsGUIPressProcedure;
import net.pixelator.procedures.AlarmModuleGUIPressProcedure;
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
public record CameraViewPropertiesButtonMessage(int buttonID, int x, int y, int z) {
	public CameraViewPropertiesButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	public static void buffer(CameraViewPropertiesButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(CameraViewPropertiesButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			NightVisionModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 1) {

			NightVisionModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 3) {

			EntityGlowingModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 4) {

			EntityGlowingModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 6) {

			AlarmModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 7) {

			AlarmModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 9) {

			ManualAlarmModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 10) {

			ManualAlarmModuleGUIPressProcedure.execute(world, entity);
		}
		if (buttonID == 12) {

			AlarmSettingsGUIPressProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(CameraViewPropertiesButtonMessage.class, CameraViewPropertiesButtonMessage::buffer, CameraViewPropertiesButtonMessage::new, CameraViewPropertiesButtonMessage::handler);
	}
}