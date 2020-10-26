package uk.co.breadhub.randombukkitutils.api.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface PlayerInventoryUtilAPI {
		ItemStack getItemInHand(final Player player);

		void transfer(final ItemStack item, final Inventory source, final Inventory target);

		void remove(final ItemStack item, final Inventory inventory);

		boolean hasAvaliableSlot(Player player);

		void update(final InventoryHolder inv);
}
