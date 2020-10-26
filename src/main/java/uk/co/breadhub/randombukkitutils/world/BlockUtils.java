package uk.co.breadhub.randombukkitutils.world;

import uk.co.breadhub.randombukkitutils.api.VersionUtilAPI;
import uk.co.breadhub.randombukkitutils.api.world.BlockUtilApi;
import uk.co.breadhub.randombukkitutils.bukkit.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.HashSet;

public class BlockUtils implements BlockUtilApi {
		public static VersionUtilAPI vapi = new VersionUtils();

		public static String getLocString(final Location location) {
				return String.format("%s,%d,%d,%d", location.getWorld().getName(), location.getBlockX(), location.getBlockY(),
								location.getBlockZ());
		}

		public static Location getLocationFromString(final String locString) {
				final String[] locs = locString.split(",");
				return new Location(Bukkit.getServer().getWorld(locs[0]), Integer.parseInt(locs[1]),
								Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
		}

		@Override
		public Block getTargetBlock(final Player player) {
				if (!vapi.HasUUIDs()) {
						try {
								@SuppressWarnings("JavaReflectionMemberAccess") final Method method = LivingEntity.class
												.getDeclaredMethod("getTargetBlock", HashSet.class, int.class);
								if (method != null) {
										HashSet<Byte> ignored = new HashSet<>();
										ignored.add((byte) 0);
										return (Block) method.invoke(player, ignored, 300);
								}
						} catch (Exception e) {
								e.printStackTrace();
						}
				}
				try {
						HashSet<Material> ignored = new HashSet<>();
						ignored.add(Material.AIR);
						return player.getTargetBlock(ignored, 300);
				} catch (NoSuchMethodError ignored) {
				}
				return null;
		}
}
