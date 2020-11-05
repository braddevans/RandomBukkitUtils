package uk.co.breadhub.randombukkitutils.bukkit;

import com.mashape.unirest.http.Unirest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import uk.co.breadhub.randombukkitutils.api.MojangApi;

import java.util.HashMap;
import java.util.UUID;

public class MojangUtils implements MojangApi {
    private static final HashMap<String, UUID> UUIDCache = new HashMap<>();
    private static final JSONParser parser = new JSONParser();

    /**
     * <h1>Parse UUID String of Player </h1>
     * <p>
     * Parse to Type: UUID
     * which is not a String but can be converted to one
     * </p>
     *
     * @param s
     *
     * @return
     */
    @Override
    public UUID parseUUID(final String s) {
        try {
            return UUID.fromString(s);
        } catch (Exception ignored) {
        }
        if (s.length() == 32) {
            return UUID.fromString(s.substring(0, 8) + "-" + s.substring(8, 12) + "-" + s.substring(12, 16) + "-" + s
                    .substring(16, 20) + "-" + s.substring(20, 32) + "-");
        }
        return null;
    }

    /**
     * <h1> Get UUID Of Player Username</h1>
     * <p>
     * Pass In the Playername and it will search for it in the cache
     * if it doesn't exist it will be pulled from mojang's api
     * </p>
     *
     * @param username
     *
     * @return
     */
    @Override
    public UUID getUUIDOfUsername(final String username) {

        // check if fake player
        if (username.contains("[") || username.contains("]")) {
            return null;
        }

        // check if uuid is in uuid cache
        UUID uuid = UUIDCache.get(username);

        // if the uuid is in the cache return the uuid
        if (uuid != null) {
            return uuid;
        }

        // else
        // ping mojang for uuid and place it into the cache
        try {
            uuid = parseUUID((String) ((JSONObject) parser.parse(Unirest.get("https://api.mojang.com/users/profiles/minecraft/" + username).asString().getBody())).get("id"));
            UUIDCache.put(username, uuid);
            return uuid;
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    /**
     * <h1>Get UUID Cache</h1>
     *
     * <p>
     * Get Unique Uuid Cache as a simple
     * get rather than creating new instances
     * </p>
     *
     * @return
     */
    @Override
    public HashMap<String, UUID> getUUIDCache() {
        return UUIDCache;
    }
}
