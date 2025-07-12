package net.pixelator.network;

import net.pixelator.PixelatorMod;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Direction;

import java.util.function.Supplier;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class PixelatorModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, PixelatorMod.MODID);
	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());
	public static String teleportAddress = "\"\"";
	public static String tptext1 = "\"\"";
	public static String tptext2 = "";
	public static String tptext3 = "\"\"";
	public static double tpx = 0;
	public static double tpy = 0;
	public static double tpz = 0;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(SavedDataSyncMessage.TYPE, SavedDataSyncMessage.STREAM_CODEC, SavedDataSyncMessage::handleData);
		PixelatorMod.addNetworkMessage(PlayerVariablesSyncMessage.TYPE, PlayerVariablesSyncMessage.STREAM_CODEC, PlayerVariablesSyncMessage::handleData);
	}

	@EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES);
			PlayerVariables clone = new PlayerVariables();
			if (!event.isWasDeath()) {
				clone.cam_x = original.cam_x;
				clone.cam_y = original.cam_y;
				clone.cam_z = original.cam_z;
				clone.cam_face = original.cam_face;
				clone.sx = original.sx;
				clone.sy = original.sy;
				clone.sz = original.sz;
				clone.sfound = original.sfound;
				clone.cameraboundname = original.cameraboundname;
				clone.teleportname = original.teleportname;
				clone.automaticscreenteleportname = original.automaticscreenteleportname;
			}
			event.getEntity().setData(PLAYER_VARIABLES, clone);
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					PacketDistributor.sendToPlayer(player, new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					PacketDistributor.sendToPlayer(player, new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					PacketDistributor.sendToPlayer(player, new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "pixelator_worldvars";

		public static WorldVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			WorldVariables data = new WorldVariables();
			data.read(tag, lookupProvider);
			return data;
		}

		public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof ServerLevel level)
				PacketDistributor.sendToPlayersInDimension(level, new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldVariables::new, WorldVariables::load), DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "pixelator_mapvars";
		public double spawn_x = -1.0;
		public double spawn_y = 0.0;
		public double spawn_z = 0.5;
		public double spawn_wait = 1.0;
		public double cameras = 1.0;
		public double camera_delete_result = 0;
		public boolean delete_executed = false;
		public String delete_entity = "\"\"";

		public static MapVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			MapVariables data = new MapVariables();
			data.read(tag, lookupProvider);
			return data;
		}

		public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			spawn_x = nbt.getDouble("spawn_x");
			spawn_y = nbt.getDouble("spawn_y");
			spawn_z = nbt.getDouble("spawn_z");
			spawn_wait = nbt.getDouble("spawn_wait");
			cameras = nbt.getDouble("cameras");
			camera_delete_result = nbt.getDouble("camera_delete_result");
			delete_executed = nbt.getBoolean("delete_executed");
			delete_entity = nbt.getString("delete_entity");
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			nbt.putDouble("spawn_x", spawn_x);
			nbt.putDouble("spawn_y", spawn_y);
			nbt.putDouble("spawn_z", spawn_z);
			nbt.putDouble("spawn_wait", spawn_wait);
			nbt.putDouble("cameras", cameras);
			nbt.putDouble("camera_delete_result", camera_delete_result);
			nbt.putBoolean("delete_executed", delete_executed);
			nbt.putString("delete_entity", delete_entity);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				PacketDistributor.sendToAllPlayers(new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(new SavedData.Factory<>(MapVariables::new, MapVariables::load), DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public record SavedDataSyncMessage(int dataType, SavedData data) implements CustomPacketPayload {
		public static final Type<SavedDataSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PixelatorMod.MODID, "saved_data_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, SavedDataSyncMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, SavedDataSyncMessage message) -> {
			buffer.writeInt(message.dataType);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag(), buffer.registryAccess()));
		}, (RegistryFriendlyByteBuf buffer) -> {
			int dataType = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			SavedData data = null;
			if (nbt != null) {
				data = dataType == 0 ? new MapVariables() : new WorldVariables();
				if (data instanceof MapVariables mapVariables)
					mapVariables.read(nbt, buffer.registryAccess());
				else if (data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt, buffer.registryAccess());
			}
			return new SavedDataSyncMessage(dataType, data);
		});

		@Override
		public Type<SavedDataSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final SavedDataSyncMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.enqueueWork(() -> {
					if (message.dataType == 0)
						MapVariables.clientSide.read(message.data.save(new CompoundTag(), context.player().registryAccess()), context.player().registryAccess());
					else
						WorldVariables.clientSide.read(message.data.save(new CompoundTag(), context.player().registryAccess()), context.player().registryAccess());
				}).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		public double cam_x = 0;
		public double cam_y = 0;
		public double cam_z = 0;
		public Direction cam_face = Direction.NORTH;
		public double sx = 0;
		public double sy = 0;
		public double sz = 0;
		public boolean sfound = false;
		public String cameraboundname = "\"\"";
		public String teleportname = "\"\"";
		public String automaticscreenteleportname = "\"\"";

		@Override
		public CompoundTag serializeNBT(HolderLookup.Provider lookupProvider) {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("cam_x", cam_x);
			nbt.putDouble("cam_y", cam_y);
			nbt.putDouble("cam_z", cam_z);
			nbt.putInt("cam_face", cam_face.get3DDataValue());
			nbt.putDouble("sx", sx);
			nbt.putDouble("sy", sy);
			nbt.putDouble("sz", sz);
			nbt.putBoolean("sfound", sfound);
			nbt.putString("cameraboundname", cameraboundname);
			nbt.putString("teleportname", teleportname);
			nbt.putString("automaticscreenteleportname", automaticscreenteleportname);
			return nbt;
		}

		@Override
		public void deserializeNBT(HolderLookup.Provider lookupProvider, CompoundTag nbt) {
			cam_x = nbt.getDouble("cam_x");
			cam_y = nbt.getDouble("cam_y");
			cam_z = nbt.getDouble("cam_z");
			cam_face = Direction.from3DDataValue(nbt.getInt("cam_face"));
			sx = nbt.getDouble("sx");
			sy = nbt.getDouble("sy");
			sz = nbt.getDouble("sz");
			sfound = nbt.getBoolean("sfound");
			cameraboundname = nbt.getString("cameraboundname");
			teleportname = nbt.getString("teleportname");
			automaticscreenteleportname = nbt.getString("automaticscreenteleportname");
		}

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				PacketDistributor.sendToPlayer(serverPlayer, new PlayerVariablesSyncMessage(this));
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final Type<PlayerVariablesSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(PixelatorMod.MODID, "player_variables_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec
				.of((RegistryFriendlyByteBuf buffer, PlayerVariablesSyncMessage message) -> buffer.writeNbt(message.data().serializeNBT(buffer.registryAccess())), (RegistryFriendlyByteBuf buffer) -> {
					PlayerVariablesSyncMessage message = new PlayerVariablesSyncMessage(new PlayerVariables());
					message.data.deserializeNBT(buffer.registryAccess(), buffer.readNbt());
					return message;
				});

		@Override
		public Type<PlayerVariablesSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.enqueueWork(() -> context.player().getData(PLAYER_VARIABLES).deserializeNBT(context.player().registryAccess(), message.data.serializeNBT(context.player().registryAccess()))).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}
}