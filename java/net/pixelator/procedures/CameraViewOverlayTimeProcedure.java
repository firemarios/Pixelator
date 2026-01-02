package net.pixelator.procedures;

import net.minecraft.world.level.LevelAccessor;

public class CameraViewOverlayTimeProcedure {
	public static String execute(LevelAccessor world) {
		double normalized_ticks = 0;
		double hours = 0;
		double minutes = 0;
		normalized_ticks = world.dayTime() % 24000;
		hours = (normalized_ticks / 1000 + 6) % 24;
		minutes = ((normalized_ticks % 1000) * 60) / 1000;
		hours = Math.round(Math.floor(hours));
		minutes = Math.round(Math.floor(minutes));
		return (("" + hours).length() != 3 ? "" + hours : "0" + hours).replace(".0", "") + ":" + (("" + minutes).length() != 3 ? "" + minutes : "0" + minutes).replace(".0", "");
	}
}