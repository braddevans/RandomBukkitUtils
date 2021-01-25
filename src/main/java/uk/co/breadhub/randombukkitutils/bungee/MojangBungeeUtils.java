package uk.co.breadhub.randombukkitutils.bungee;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpClient;
import org.json.simple.parser.JSONParser;
import uk.co.breadhub.randombukkitutils.MiscUtils;
import uk.co.breadhub.randombukkitutils.api.MojangBungeeApi;

import java.util.HashMap;
import java.util.UUID;

public class MojangBungeeUtils implements MojangBungeeApi {
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
    public UUID getUUIDOfUsername(String username) {

        // check if fake player
        if (username.contains("[") || username.contains("]")) {
            return null;
        }

        // else
        // ping mojang for uuid and place it into the cache
        try {
            return parseUUID(new JsonParser().parse(Unirest.get("https://mcapi.breadhub.co.uk/api/name/" + username).getBody().toString()).getAsJsonObject().get("uuid").getAsString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * <h1>Get Name from prev names</h1>
     *
     * @param uniqueID
     *
     * @return
     *
     */
    @Override
    public String getPlayerFromUUID(UUID uniqueID) {
        return new JsonParser().parse(Unirest.get("https://mcapi.breadhub.co.uk/api/uuid/" + uniqueID.toString()).getBody().toString()).getAsJsonObject().get("name").getAsString();
    }


    private class profile {
        private String name;
        private String uuid;
    }
}
