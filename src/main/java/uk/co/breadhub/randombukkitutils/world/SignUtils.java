package uk.co.breadhub.randombukkitutils.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.SignChangeEvent;
import uk.co.breadhub.randombukkitutils.api.world.SignUtilsApi;

public class SignUtils implements SignUtilsApi {

    /**
     * <h1>Update sign</h1>
     *
     * <p>Example code 1</p>
     * <code>
     *     @EventHandler
     *     public void onSignPlace(final SignChangeEvent event) {
     *         final Player player = event.getPlayer();
     *         final String[] lines = event.getLines();
     *         updateSign(event, lines);
     *     }
     * </code>
     *
     * <p>Example code 2</p>
     * <code>
     *     // the code below will create the sign event and call it to trigger the below function
     *     updateSign(new SignChangeEvent(location.getBlock(), sender, lines), lines)
     * </code>
     *
     * @param event
     * @param lines
     */
    @Override
    public void updateSign(SignChangeEvent event, final String[] lines) {
        event.setLine(0, String.valueOf(lines[0]));
        event.setLine(1, String.valueOf(lines[1]));
        event.setLine(2, String.valueOf(lines[2]));
        event.setLine(3, String.valueOf(lines[3]));
    }

    /**
     * <h1>Remove sign at Location</h1>
     * <p>
     *     Location: world x y z location;
     *     Boolean: will the block drop its item
     * </p>
     * @param loc
     * @param DropsItem
     */
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
