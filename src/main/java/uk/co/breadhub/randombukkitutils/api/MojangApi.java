package uk.co.breadhub.randombukkitutils.api;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public interface MojangApi {
    UUID parseUUID(final String s);

    HashMap<String, UUID> getUUIDCache();

    String getPlayerFromUUID(UUID uniqueID) throws UnirestException;

    UUID getUUIDOfUsername(String username);

    UUID getUUIDfromOfflinePlayer(OfflinePlayer p);

    UUID getUUIDfromPlayer(Player p);
}
