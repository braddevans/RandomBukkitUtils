package uk.co.breadhub.randombukkitutils.bukkit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import uk.co.breadhub.randombukkitutils.api.BukkitServerApi;
import uk.co.breadhub.randombukkitutils.api.MojangApi;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MojangUtils implements MojangApi {
    private static final HashMap<String, UUID> UUIDCache = new HashMap<>();
    private static BukkitServerApi bsa = new BukkitServerUtils();
    private static final JSONParser parser = new JSONParser();
    private static final Gson gson = new Gson();

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
            JsonObject jsonObject = new JsonParser().parse(Unirest.get("https://api.mojang.com/users/profiles/minecraft/" + username).asString().getBody()).getAsJsonObject();
            uuid = parseUUID(jsonObject.get("id").toString());
            UUIDCache.put(username, uuid);
            return uuid;
        } catch (Exception ex) {
            ex.printStackTrace();
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

    /**
     * <h1>Get Name from prev names</h1>
     *
     * @param uniqueID
     *
     * @return
     *
     * @throws UnirestException
     */
    @Override
    public String getPlayerFromUUID(UUID uniqueID) throws UnirestException {
        String name = UUIDCache.entrySet().stream()
                               .filter(e -> e.getValue().equals(uniqueID))
                               .map(Map.Entry::getKey)
                               .findFirst()
                               .orElse(null);
        if (name == null) {
            //note: names at array id 0 will always be the current name
            JsonArray names = new JsonParser().parse(Unirest.get("https://api.mojang.com/user/profiles/" + uniqueID + "/names").asString().getBody()).getAsJsonArray();
            JsonObject object = names.get(0).getAsJsonObject();
            name = object.get("name").toString();
            UUIDCache.put(name, uniqueID);
        }
        return name;
    }

    /**
     * get uuid from username
     *
     * @param username
     *
     * @return
     */
    @Override
    public UUID getUUID(String username) {
        UUID uuid = UUIDCache.get(username);
        if (uuid != null) {
            UUIDCache.put(username, uuid);
            return uuid;
        }
        try {
            uuid = parseUUID(getUUIDOfUsername(username).toString());
            UUIDCache.put(username, uuid);
            return uuid;
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * get uuid from offlineplayer
     *
     * @param p
     *
     * @return
     */
    @Override
    public UUID getUUID(OfflinePlayer p) {
        if (bsa.getVersion() >= 1.7) {
            try {
                return (p.getUniqueId());
            } catch (NoSuchMethodError e) {
                return getUUID(p.getName());
            }
        }
        else {
            return getUUID(p.getName());
        }
    }

    /**
     * get uuid from player Object
     *
     * @param p
     *
     * @return
     */
    @Override
    public UUID getUUID(Player p) {
        if (bsa.getVersion() >= 1.7) {
            try {
                return (p.getUniqueId());
            } catch (NoSuchMethodError e) {
                return getUUID(p.getName());
            }
        }
        else {
            return getUUID(p.getName());
        }
    }
}
