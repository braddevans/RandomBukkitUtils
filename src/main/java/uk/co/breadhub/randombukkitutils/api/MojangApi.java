package uk.co.breadhub.randombukkitutils.api;

import java.util.HashMap;
import java.util.UUID;

public interface MojangApi {
    UUID parseUUID(final String s);

    UUID getUUIDOfUsername(final String username);

    HashMap<String, UUID> getUUIDCache();
}
