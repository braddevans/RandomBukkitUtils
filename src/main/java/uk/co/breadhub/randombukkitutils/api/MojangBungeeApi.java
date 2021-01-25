package uk.co.breadhub.randombukkitutils.api;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public interface MojangBungeeApi {
    UUID parseUUID(final String s);

    UUID getUUIDOfUsername(String username);

    String getPlayerFromUUID(UUID uniqueID);
}
