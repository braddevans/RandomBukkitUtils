package uk.co.breadhub.randombukkitutils.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public interface BukkitServerApi {

    /**
     * get OfflinePlayer object passing in uuid
     *
     * @param uniqueID
     *
     * @return OfflinePlayer
     */
    OfflinePlayer getOfflinePlayerByUUID(UUID uniqueID);

    /**
     * get OfflinePlayer object passing in uuid
     *
     * @param name
     *
     * @return OfflinePlayer
     */
    OfflinePlayer getOfflinePlayerByName(String name);

    /**
     * Get OnlinePlayers
     *
     * @return
     */
    Collection<? extends Player> getOnlinePlayers();

    /**
     * Check If Player is fake Player
     *
     * @param username
     *
     * @return
     */
    boolean isFakePlayer(String username);

    /**
     * get player from name short e.g.
     * <code>
     *     getPlayerShort("bradd");
     * </code>
     * @param playerstr
     * @return
     */
    Player getPlayerShort(String playerstr);

    /**
     * get player that is online from name short e.g.
     * <code>
     *     getPlayerOnlineShort("bradd");
     * </code>
     * @param playerstr
     * @return
     */
    Player getPlayerOnlineShort(String playerstr);
}
