package uk.co.breadhub.randombukkitutils.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import uk.co.breadhub.randombukkitutils.api.BukkitServerApi;

import java.lang.reflect.Method;
import java.util.*;

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

    public static OfflinePlayer[] getOfflinePlayers() {
        try {
            Method method = Bukkit.class.getDeclaredMethod("getOfflinePlayers");
            if (method.getReturnType() != OfflinePlayer[].class) {
                return Bukkit.getOfflinePlayers();
            }
            else {
                return (OfflinePlayer[]) method.invoke(null);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * -========================================-
     * <h1>Get player by short name</h1>
     * <p>
     * Use a Java 8 stream to parse through offline players
     * to get the player object
     * </p>
     * -========================================-
     *
     * @param playerstr
     *
     * @return
     */
    @Override
    public Player getPlayerShort(String playerstr) {
        final Player[] player = {null};

        try {
            Arrays.stream(Objects.requireNonNull(getOfflinePlayers())).forEach(oplayer -> {
                if (oplayer.getName().toLowerCase().contains(playerstr.toLowerCase())) {
                    player[0] = oplayer.getPlayer();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        return player[0];
    }

    /**
     * -========================================-
     * <h1>Get player by short name</h1>
     * <p>
     * Use a Java 8 stream to parse through offline players
     * to get the player object
     * </p>
     * -========================================-
     *
     * @param playerstr
     *
     * @return
     */
    @Override
    public Player getPlayerOnlineShort(String playerstr) {
        final Player[] player = {null};

        try {
            getOnlinePlayers().forEach(oplayer -> {
                if (oplayer.getName().toLowerCase().contains(playerstr.toLowerCase())) {
                    player[0] = oplayer.getPlayer();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        return player[0];
    }
}
