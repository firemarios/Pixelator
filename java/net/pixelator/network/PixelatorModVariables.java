package net.pixelator.network;

import net.pixelator.PixelatorMod;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.INBTSerializable;
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
	public static ArrayList<Object> session_data = new ArrayList<>();

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		PixelatorMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handleData);
		PixelatorMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability)));
		}

		@SubscribeEvent
		public static void onPlayerTickUpdateSyncPlayerVariables(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer player) {
				player.getCapability(PLAYER_VARIABLES).ifPresent(capability -> {
					if (capability._syncDirty) {
						PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new PlayerVariablesSyncMessage(capability));
						capability._syncDirty = false;
					}
				});
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			event.getOriginal().getCapability(PLAYER_VARIABLES).ifPresent(original -> {
				event.getEntity().getCapability(PLAYER_VARIABLES).ifPresent(clone -> {
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
						clone.computer_page = original.computer_page;
						clone.computer_cams = original.computer_cams;
						clone.computer_pages = original.computer_pages;
						clone.request_index = original.request_index;
						clone.viewing_cam = original.viewing_cam;
						clone.stored_gamemode = original.stored_gamemode;
						clone.stored_location = original.stored_location;
						clone.computer_tp_x = original.computer_tp_x;
						clone.computer_tp_y = original.computer_tp_y;
						clone.computer_tp_z = original.computer_tp_z;
						clone.computer_tp_view_x = original.computer_tp_view_x;
						clone.computer_tp_view_y = original.computer_tp_view_y;
						clone.computer_max_l = original.computer_max_l;
						clone.computer_max_r = original.computer_max_r;
						clone.disabling_cam = original.disabling_cam;
						clone.shift_press = original.shift_press;
					}
				});
			});
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData mapdata = MapVariables.get(player.level());
				SavedData worlddata = WorldVariables.get(player.level());
				if (mapdata != null)
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData worlddata = WorldVariables.get(player.level());
				if (worlddata != null)
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onWorldTick(TickEvent.LevelTickEvent event) {
			if (event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel level) {
				WorldVariables worldVariables = WorldVariables.get(level);
				if (worldVariables._syncDirty) {
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, worldVariables));
					worldVariables._syncDirty = false;
				}
				MapVariables mapVariables = MapVariables.get(level);
				if (mapVariables._syncDirty) {
					PixelatorMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, mapVariables));
					mapVariables._syncDirty = false;
				}
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "pixelator_worldvars";
		boolean _syncDirty = false;

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

		public void markSyncDirty() {
			this.setDirty();
			this._syncDirty = true;
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
		boolean _syncDirty = false;
		public String cameras = "\"\"";
		public double teleport_max_pages = 0.0;
		public double index_auto_delete = 0;
		public double x_server_auto_delete = 0;
		public double y_server_auto_delete = 0;
		public double z_server_auto_delete = 0;
		public String server_pos_auto_delete = "";
		public double alarm_timer = 0;
		public String to_update_cams = "\"\"";
		public String camera_to_load = "\"\"";

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
			alarm_timer = nbt.getDouble("alarm_timer");
			to_update_cams = nbt.getString("to_update_cams");
			camera_to_load = nbt.getString("camera_to_load");
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
			nbt.putDouble("alarm_timer", alarm_timer);
			nbt.putString("to_update_cams", to_update_cams);
			nbt.putString("camera_to_load", camera_to_load);
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			_syncDirty = true;
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
		private final int dataType;
		private final SavedData data;

		public SavedDataSyncMessage(int dataType, SavedData data) {
			this.dataType = dataType;
			this.data = data;
		}

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			int dataType = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			SavedData data = null;
			if (nbt != null) {
				data = dataType == 0 ? new MapVariables() : new WorldVariables();
				if (data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
			this.dataType = dataType;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.dataType);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handleData(final SavedDataSyncMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.dataType == 0)
						MapVariables.clientSide.read(message.data.save(new CompoundTag()));
					else
						WorldVariables.clientSide.read(message.data.save(new CompoundTag()));
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<CompoundTag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(ResourceLocation.fromNamespaceAndPath("pixelator", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public CompoundTag serializeNBT() {
			return playerVariables.serializeNBT();
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			playerVariables.deserializeNBT(nbt);
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		boolean _syncDirty = false;
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
		public double computer_page = 0;
		public double computer_cams = 0;
		public double computer_pages = 0;
		public double request_index = 0;
		public boolean viewing_cam = false;
		public String stored_gamemode = "\"\"";
		public String stored_location = "\"\"";
		public double computer_tp_x = 0;
		public double computer_tp_y = 0;
		public double computer_tp_z = 0;
		public double computer_tp_view_x = 0;
		public double computer_tp_view_y = 0;
		public double computer_max_l = 0;
		public double computer_max_r = 0;
		public boolean disabling_cam = false;
		public boolean shift_press = false;

		@Override
		public CompoundTag serializeNBT() {
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
			nbt.putDouble("computer_page", computer_page);
			nbt.putDouble("computer_cams", computer_cams);
			nbt.putDouble("computer_pages", computer_pages);
			nbt.putDouble("request_index", request_index);
			nbt.putBoolean("viewing_cam", viewing_cam);
			nbt.putString("stored_gamemode", stored_gamemode);
			nbt.putString("stored_location", stored_location);
			nbt.putDouble("computer_tp_x", computer_tp_x);
			nbt.putDouble("computer_tp_y", computer_tp_y);
			nbt.putDouble("computer_tp_z", computer_tp_z);
			nbt.putDouble("computer_tp_view_x", computer_tp_view_x);
			nbt.putDouble("computer_tp_view_y", computer_tp_view_y);
			nbt.putDouble("computer_max_l", computer_max_l);
			nbt.putDouble("computer_max_r", computer_max_r);
			nbt.putBoolean("disabling_cam", disabling_cam);
			nbt.putBoolean("shift_press", shift_press);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
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
			computer_page = nbt.getDouble("computer_page");
			computer_cams = nbt.getDouble("computer_cams");
			computer_pages = nbt.getDouble("computer_pages");
			request_index = nbt.getDouble("request_index");
			viewing_cam = nbt.getBoolean("viewing_cam");
			stored_gamemode = nbt.getString("stored_gamemode");
			stored_location = nbt.getString("stored_location");
			computer_tp_x = nbt.getDouble("computer_tp_x");
			computer_tp_y = nbt.getDouble("computer_tp_y");
			computer_tp_z = nbt.getDouble("computer_tp_z");
			computer_tp_view_x = nbt.getDouble("computer_tp_view_x");
			computer_tp_view_y = nbt.getDouble("computer_tp_view_y");
			computer_max_l = nbt.getDouble("computer_max_l");
			computer_max_r = nbt.getDouble("computer_max_r");
			disabling_cam = nbt.getBoolean("disabling_cam");
			shift_press = nbt.getBoolean("shift_press");
		}

		public void markSyncDirty() {
			_syncDirty = true;
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) {
		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this(new PlayerVariables());
			data.deserializeNBT(buffer.readNbt());
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt(message.data().serializeNBT());
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null)
					Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES).ifPresent(cap -> cap.deserializeNBT(message.data.serializeNBT()));
			});
			context.setPacketHandled(true);
		}
	}
}