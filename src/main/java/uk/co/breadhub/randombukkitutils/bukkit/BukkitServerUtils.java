package uk.co.breadhub.randombukkitutils.bukkit;

import uk.co.breadhub.randombukkitutils.api.BukkitServerApi;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class BukkitServerUtils implements BukkitServerApi {

		@Override
		public Collection<? extends Player> getOnlinePlayers() {
				try {
						final Method method = Bukkit.class.getDeclaredMethod("getOnlinePlayers");
						if (method.getReturnType() != Player[].class) {
								return Bukkit.getOnlinePlayers();
						}
						else {
								return Arrays.asList((Player[]) method.invoke(null));
						}
				} catch (Exception e) {
						e.printStackTrace();
				}
				return Collections.emptyList();
		}

		@Override
		public OfflinePlayer getOfflinePlayerByUUID(UUID uniqueID) {
				return Bukkit.getServer().getOfflinePlayer(uniqueID);
		}

		@Override
		public OfflinePlayer getOfflinePlayerByName(String name) {
				return Bukkit.getServer().getOfflinePlayer(name);
		}

		@Override
		public boolean isFakePlayer(String username) {
				if (username.contains("[") || username.contains("]")) { return true; }
				if (username.length() < 10) { return false; }
				switch (username.substring(10)) {
						case "ComputerCr":
						case "FakeThaumc":
						case "OpenModsFa":
								return true;
						default:
								return false;
				}
		}
}
