package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;
import net.pixelator.PixelatorMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

import java.util.regex.Pattern;

@Mod.EventBusSubscriber
public class AlarmGetCamerasOnPlayerJoinProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level());
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		double lx = 0;
		double ly = 0;
		double lz = 0;
		String camera_to_load = "";
		{
			String[] _array = PixelatorModVariables.MapVariables.get(world).to_update_cams.split(Pattern.quote(","));
			if (_array.length != 0) {
				for (String stringiterator : _array) {
					PixelatorMod.LOGGER.info(("Loading Alarm for " + stringiterator));
					PixelatorModVariables.MapVariables.get(world).camera_to_load = stringiterator;
					PixelatorModVariables.MapVariables.get(world).markSyncDirty();
					AlarmUpdateCamerasOnPlayerJoinProcedure.execute(world);
				}
			} else {
				String stringiterator = PixelatorModVariables.MapVariables.get(world).to_update_cams;
				for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
					PixelatorMod.LOGGER.info(("Loading Alarm for " + stringiterator));
					PixelatorModVariables.MapVariables.get(world).camera_to_load = stringiterator;
					PixelatorModVariables.MapVariables.get(world).markSyncDirty();
					AlarmUpdateCamerasOnPlayerJoinProcedure.execute(world);
				}
			}
		}
	}
}