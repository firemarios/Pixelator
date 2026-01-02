package net.pixelator.procedures;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;

public class PixelatorTeleportNoCamRegTextProcedure {
	public static boolean execute(LevelAccessor world) {
		double list_lenght = 0;
		double pages = 0;
		String cameras = "";
		String camera_tmp = "";
		boolean show = false;
		boolean less_than_6 = false;
		cameras = PixelatorModVariables.MapVariables.get(world).cameras;
		cameras = cameras.replace("\"", "");
		PixelatorModVariables.cameras_decoded.clear();
		while (true) {
			if ((cameras).length() == 0 || (cameras).length() == 1 && cameras.contains(",")) {
				break;
			}
			camera_tmp = cameras.substring(0, cameras.indexOf(",", 0));
			PixelatorModVariables.cameras_decoded.add(camera_tmp);
			cameras = cameras.substring(1 + cameras.indexOf(",", 0));
		}
		less_than_6 = false;
		list_lenght = PixelatorModVariables.cameras_decoded.size();
		while (!less_than_6) {
			if (list_lenght <= 6) {
				less_than_6 = true;
				PixelatorModVariables.MapVariables.get(world).teleport_max_pages = pages + 1;
				PixelatorModVariables.MapVariables.get(world).markSyncDirty();
			}
			list_lenght = list_lenght - 6;
			pages++;
		}
		if (PixelatorModVariables.cameras_decoded.size() == 0) {
			show = true;
		} else {
			show = false;
		}
		return show;
	}
}