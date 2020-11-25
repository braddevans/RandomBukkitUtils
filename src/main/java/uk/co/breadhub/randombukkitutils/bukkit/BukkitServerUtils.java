package uk.co.breadhub.randombukkitutils.bukkit;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import uk.co.breadhub.randombukkitutils.api.BukkitServerApi;
import uk.co.breadhub.randombukkitutils.api.MojangApi;

import java.lang.reflect.Method;
import java.util.*;

public class BukkitServerUtils implements BukkitServerApi {

    private static MojangApi ma = new MojangUtils();
    private final boolean minecraft17 = Bukkit.getServer().getVersion().contains("1.7"),
            minecraft16 = Bukkit.getServer().getVersion().contains("1.6"),
            minecraft15 = Bukkit.getServer().getVersion().contains("1.5");
    private static Method method;
    private static boolean useReflection;


    static {
        try {
            method = Bukkit.class.getDeclaredMethod("getOnlinePlayers");
            useReflection = method.getReturnType() == Player[].class;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Override
    public double getVersion() {
        if (minecraft17) { return 1.7; }
        if (minecraft16) { return 1.6; }
        if (minecraft15) { return 1.5; }
        else {
            return 2.2; //1.12 = 2.2
        }
        //I swear to whatever if you use 1.4, you're screwed.
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        try {
            if (!useReflection) {
                return Bukkit.getOnlinePlayers();
            } else {
                Player[] playersArray = (Player[]) method.invoke(null);
                return Arrays.asList(playersArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    @Override
    public Collection<? extends OfflinePlayer> getOfflinePlayers() {
        try {
            if (!useReflection) {
                return Arrays.asList(Bukkit.getOfflinePlayers());
            } else {
                OfflinePlayer[] playersArray = (OfflinePlayer[]) method.invoke(null);
                return Arrays.asList(playersArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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
    public OfflinePlayer getOfflinePlayer(UUID uniqueID) {
        if (uniqueID == null) {
            return null;
        }
        if (getVersion() == 2.2) {
            return Bukkit.getServer().getOfflinePlayer(uniqueID);
        }
        else {
            String name = null;
            try {
                name = ma.getPlayerFromUUID(uniqueID);
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            Bukkit.getOfflinePlayer(name);
        }
        return null;
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
            getOfflinePlayers().forEach(oplayer -> {
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
