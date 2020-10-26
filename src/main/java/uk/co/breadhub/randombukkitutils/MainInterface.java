package uk.co.breadhub.randombukkitutils;

import uk.co.breadhub.randombukkitutils.api.BukkitServerApi;
import uk.co.breadhub.randombukkitutils.api.MojangApi;
import uk.co.breadhub.randombukkitutils.api.VersionUtilAPI;
import uk.co.breadhub.randombukkitutils.api.player.ItemUtilAPI;
import uk.co.breadhub.randombukkitutils.api.player.PlayerInventoryUtilAPI;
import uk.co.breadhub.randombukkitutils.api.world.BlockUtilApi;

public interface MainInterface extends MojangApi, BukkitServerApi,
				VersionUtilAPI, BlockUtilApi, PlayerInventoryUtilAPI, ItemUtilAPI {
}
