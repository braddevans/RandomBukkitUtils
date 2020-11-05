package uk.co.breadhub.randombukkitutils.bukkit;

import org.bukkit.Bukkit;
import uk.co.breadhub.randombukkitutils.api.VersionUtilAPI;

public class VersionUtils implements VersionUtilAPI {
    private static final boolean hasUUIDs;
    private static String OBC_PREFIX;

    static {
        try {
            OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        hasUUIDs = !Bukkit.getServer().getVersion().contains("1.4") && !Bukkit.getServer().getVersion().contains(
                "1.5") && !Bukkit.getServer().getVersion().contains("1.6");
    }

    /**
     * Returns the package name for the Bukkit Server
     * Bukkit.getServer().getClass().getPackage().getName();
     *
     * @return
     */
    @Override
    public String getObcPrefix() {
        return OBC_PREFIX;
    }

    @Override
    public boolean HasUUIDs() {
        return hasUUIDs;
    }

}