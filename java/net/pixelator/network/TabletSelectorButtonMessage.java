package net.pixelator.network;

import net.pixelator.procedures.TabletSelectProcedure;
import net.pixelator.PixelatorMod;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record TabletSelectorButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<TabletSelectorButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PixelatorMod.MODID, "tablet_selector_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, TabletSelectorButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, TabletSelectorButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new TabletSelectorButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<TabletSelectorButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final TabletSelectorButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TabletSelectProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(TabletSelectorButtonMessage.TYPE, TabletSelectorButtonMessage.STREAM_CODEC, TabletSelectorButtonMessage::handleData);
	}
}