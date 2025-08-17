package net.pixelator.network;

import net.pixelator.PixelatorMod;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PixelatorModVariables {
	public static String teleportAddress = "\"\"";
	public static String tptext1 = "\"\"";
	public static String tptext2 = "";
	public static String tptext3 = "\"\"";
	public static double tpx = 0;
	public static double tpy = 0;
	public static double tpz = 0;
	public static ArrayList<Object> cameras_decoded = new ArrayList<>();

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		PixelatorMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			if (!event.isWasDeath()) {
				clone.automaticscreenteleportname = original.automaticscreenteleportname;
				clone.cam_face = original.cam_face;
				clone.cam_x = original.cam_x;
				clone.cam_y = original.cam_y;
				clone.cam_z = original.cam_z;
				clone.cameraboundname = original.cameraboundname;
				clone.sfound = original.sfound;
				clone.sx = original.sx;
				clone.sy = original.sy;
				clone.sz = original.sz;
				clone.teleport_page = original.teleport_page;
				clone.teleportname = original.teleportname;
				clone.selected_cam = original.selected_cam;
				clone.teleport_type = original.teleport_type;
				clone.command_clear = original.command_clear;
				clone.particle_height = original.particle_height;
				clone.spawn_particles_done = original.spawn_particles_done;
				clone.lcontrol_pressed = original.lcontrol_pressed;
				clone.itemstack_server_bound_network = original.itemstack_server_bound_network;
				clone.selected_network = original.selected_network;
				clone.itemstack_server_bound_pos = original.itemstack_server_bound_pos;
				clone.index_teleport = original.index_teleport;
				clone.x_server = original.x_server;
				clone.y_server = original.y_server;
				clone.z_server = original.z_server;
				clone.server_pos = original.server_pos;
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "pixelator_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				PixelatorMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "pixelator_mapvars";
		public String cameras = "\"\"";
		public double teleport_max_pages = 0.0;
		public double index_auto_delete = 0;
		public double x_server_auto_delete = 0;
		public double y_server_auto_delete = 0;
		public double z_server_auto_delete = 0;
		public String server_pos_auto_delete = "";

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			cameras = nbt.getString("cameras");
			teleport_max_pages = nbt.getDouble("teleport_max_pages");
			index_auto_delete = nbt.getDouble("index_auto_delete");
			x_server_auto_delete = nbt.getDouble("x_server_auto_delete");
			y_server_auto_delete = nbt.getDouble("y_server_auto_delete");
			z_server_auto_delete = nbt.getDouble("z_server_auto_delete");
			server_pos_auto_delete = nbt.getString("server_pos_auto_delete");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putString("cameras", cameras);
			nbt.putDouble("teleport_max_pages", teleport_max_pages);
			nbt.putDouble("index_auto_delete", index_auto_delete);
			nbt.putDouble("x_server_auto_delete", x_server_auto_delete);
			nbt.putDouble("y_server_auto_delete", y_server_auto_delete);
			nbt.putDouble("z_server_auto_delete", z_server_auto_delete);
			nbt.putString("server_pos_auto_delete", server_pos_auto_delete);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				PixelatorMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(ResourceLocation.fromNamespaceAndPath("pixelator", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public String automaticscreenteleportname = "\"\"";
		public Direction cam_face = Direction.NORTH;
		public double cam_x = 0;
		public double cam_y = 0;
		public double cam_z = 0;
		public String cameraboundname = "\"\"";
		public boolean sfound = false;
		public double sx = 0;
		public double sy = 0;
		public double sz = 0;
		public double teleport_page = 1.0;
		public String teleportname = "\"\"";
		public String selected_cam = "\"\"";
		public String teleport_type = "\"\"";
		public boolean command_clear = false;
		public double particle_height = 0;
		public boolean spawn_particles_done = false;
		public boolean lcontrol_pressed = false;
		public String itemstack_server_bound_network = "";
		public String selected_network = "\"\"";
		public String itemstack_server_bound_pos = "\"\"";
		public double index_teleport = 0;
		public double x_server = 0;
		public double y_server = 0;
		public double z_server = 0;
		public String server_pos = "\"\"";

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("automaticscreenteleportname", automaticscreenteleportname);
			nbt.putInt("cam_face", cam_face.get3DDataValue());
			nbt.putDouble("cam_x", cam_x);
			nbt.putDouble("cam_y", cam_y);
			nbt.putDouble("cam_z", cam_z);
			nbt.putString("cameraboundname", cameraboundname);
			nbt.putBoolean("sfound", sfound);
			nbt.putDouble("sx", sx);
			nbt.putDouble("sy", sy);
			nbt.putDouble("sz", sz);
			nbt.putDouble("teleport_page", teleport_page);
			nbt.putString("teleportname", teleportname);
			nbt.putString("selected_cam", selected_cam);
			nbt.putString("teleport_type", teleport_type);
			nbt.putBoolean("command_clear", command_clear);
			nbt.putDouble("particle_height", particle_height);
			nbt.putBoolean("spawn_particles_done", spawn_particles_done);
			nbt.putBoolean("lcontrol_pressed", lcontrol_pressed);
			nbt.putString("itemstack_server_bound_network", itemstack_server_bound_network);
			nbt.putString("selected_network", selected_network);
			nbt.putString("itemstack_server_bound_pos", itemstack_server_bound_pos);
			nbt.putDouble("index_teleport", index_teleport);
			nbt.putDouble("x_server", x_server);
			nbt.putDouble("y_server", y_server);
			nbt.putDouble("z_server", z_server);
			nbt.putString("server_pos", server_pos);
			return nbt;
		}

		public void readNBT(Tag tag) {
			CompoundTag nbt = (CompoundTag) tag;
			automaticscreenteleportname = nbt.getString("automaticscreenteleportname");
			cam_face = Direction.from3DDataValue(nbt.getInt("cam_face"));
			cam_x = nbt.getDouble("cam_x");
			cam_y = nbt.getDouble("cam_y");
			cam_z = nbt.getDouble("cam_z");
			cameraboundname = nbt.getString("cameraboundname");
			sfound = nbt.getBoolean("sfound");
			sx = nbt.getDouble("sx");
			sy = nbt.getDouble("sy");
			sz = nbt.getDouble("sz");
			teleport_page = nbt.getDouble("teleport_page");
			teleportname = nbt.getString("teleportname");
			selected_cam = nbt.getString("selected_cam");
			teleport_type = nbt.getString("teleport_type");
			command_clear = nbt.getBoolean("command_clear");
			particle_height = nbt.getDouble("particle_height");
			spawn_particles_done = nbt.getBoolean("spawn_particles_done");
			lcontrol_pressed = nbt.getBoolean("lcontrol_pressed");
			itemstack_server_bound_network = nbt.getString("itemstack_server_bound_network");
			selected_network = nbt.getString("selected_network");
			itemstack_server_bound_pos = nbt.getString("itemstack_server_bound_pos");
			index_teleport = nbt.getDouble("index_teleport");
			x_server = nbt.getDouble("x_server");
			y_server = nbt.getDouble("y_server");
			z_server = nbt.getDouble("z_server");
			server_pos = nbt.getString("server_pos");
		}
	}

	public static class PlayerVariablesSyncMessage {
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.automaticscreenteleportname = message.data.automaticscreenteleportname;
					variables.cam_face = message.data.cam_face;
					variables.cam_x = message.data.cam_x;
					variables.cam_y = message.data.cam_y;
					variables.cam_z = message.data.cam_z;
					variables.cameraboundname = message.data.cameraboundname;
					variables.sfound = message.data.sfound;
					variables.sx = message.data.sx;
					variables.sy = message.data.sy;
					variables.sz = message.data.sz;
					variables.teleport_page = message.data.teleport_page;
					variables.teleportname = message.data.teleportname;
					variables.selected_cam = message.data.selected_cam;
					variables.teleport_type = message.data.teleport_type;
					variables.command_clear = message.data.command_clear;
					variables.particle_height = message.data.particle_height;
					variables.spawn_particles_done = message.data.spawn_particles_done;
					variables.lcontrol_pressed = message.data.lcontrol_pressed;
					variables.itemstack_server_bound_network = message.data.itemstack_server_bound_network;
					variables.selected_network = message.data.selected_network;
					variables.itemstack_server_bound_pos = message.data.itemstack_server_bound_pos;
					variables.index_teleport = message.data.index_teleport;
					variables.x_server = message.data.x_server;
					variables.y_server = message.data.y_server;
					variables.z_server = message.data.z_server;
					variables.server_pos = message.data.server_pos;
				}
			});
			context.setPacketHandled(true);
		}
	}
}