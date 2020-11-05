package uk.co.breadhub.randombukkitutils.api.world;

import org.bukkit.Location;
import org.bukkit.event.block.SignChangeEvent;

public interface SignUtilsApi {

    void updateSign(SignChangeEvent event, final String[] lines);

    void removeSignAt(Location loc, boolean DropsItem);

}
