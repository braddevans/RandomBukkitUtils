package uk.co.breadhub.randombukkitutils.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.SignChangeEvent;
import uk.co.breadhub.randombukkitutils.api.world.SignUtilsApi;

public class SignUtils implements SignUtilsApi {

    @Override
    public void updateSign(SignChangeEvent event, final String[] lines) {
        event.setLine(0, String.valueOf(lines[0]));
        event.setLine(1, String.valueOf(lines[1]));
        event.setLine(2, String.valueOf(lines[2]));
        event.setLine(3, String.valueOf(lines[3]));
    }

    @Override
    public void removeSignAt(Location loc, boolean DropsItem) {
        if (DropsItem) {
            loc.getBlock().breakNaturally();
        }
        else {
            loc.getBlock().setType(Material.AIR);
        }
    }
}
