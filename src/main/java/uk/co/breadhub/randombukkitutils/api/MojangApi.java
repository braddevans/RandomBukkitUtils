package uk.co.breadhub.randombukkitutils.api;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.UUID;

public interface MojangApi {
    UUID parseUUID(final String s);

    UUID getUUIDOfUsername(final String username);

    HashMap<String, UUID> getUUIDCache();

    String getPlayerFromUUID(UUID uniqueID) throws UnirestException;
}
