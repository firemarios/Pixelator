package net.pixelator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.pixelator.network.PixelatorModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.regex.Pattern;

public class AlarmUpdateCamerasOnPlayerJoinProcedure {
	public static void execute(LevelAccessor world) {
		double lx = 0;
		double ly = 0;
		double lz = 0;
		double index = 0;
		{
			String[] _array = PixelatorModVariables.MapVariables.get(world).camera_to_load.split(Pattern.quote("+"));
			if (_array.length != 0) {
				for (String stringiterator : _array) {
					if (index == 0) {
						lx = new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(stringiterator);
					} else if (index == 1) {
						ly = new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(stringiterator);
					} else if (index == 2) {
						lz = new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(stringiterator);
					}
					index++;
					world.scheduleTick(BlockPos.containing(lx, ly, lz), world.getBlockState(BlockPos.containing(lx, ly, lz)).getBlock(), 10);
				}
			} else {
				String stringiterator = PixelatorModVariables.MapVariables.get(world).camera_to_load;
				for (int _yourmother = 0; _yourmother < 1; _yourmother++) {
					if (index == 0) {
						lx = new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(stringiterator);
					} else if (index == 1) {
						ly = new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(stringiterator);
					} else if (index == 2) {
						lz = new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(stringiterator);
					}
					index++;
					world.scheduleTick(BlockPos.containing(lx, ly, lz), world.getBlockState(BlockPos.containing(lx, ly, lz)).getBlock(), 10);
				}
			}
		}
	}
}