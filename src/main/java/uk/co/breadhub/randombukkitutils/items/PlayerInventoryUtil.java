package uk.co.breadhub.randombukkitutils.items;

import uk.co.breadhub.randombukkitutils.api.VersionUtilAPI;
import uk.co.breadhub.randombukkitutils.api.player.PlayerInventoryUtilAPI;
import uk.co.breadhub.randombukkitutils.bukkit.VersionUtils;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryUtil implements PlayerInventoryUtilAPI {

		@Override
		public ItemStack getItemInHand(final Player player) {
				VersionUtilAPI vapi = new VersionUtils();
				if (!vapi.HasUUIDs()) {
						// noinspection deprecation
						return player.getItemInHand();
				}
				final ItemStack item = player.getInventory().getItemInMainHand();
				if (item != null) {
						return item;
				}
				return player.getInventory().getItemInOffHand();
		}

		@Override
		public void transfer(final ItemStack item, final Inventory source, final Inventory target) {
				if (target != null) {
						target.addItem(item);
						update(target.getHolder());
				}
				if (source != null) {
						remove(item, source);
						update(source.getHolder());
				}
		}

		@Override
		public void remove(final ItemStack item, final Inventory inventory) {
				int amountLeft = item.getAmount();
				for (int slot = 0; slot < inventory.getSize() && amountLeft > 0; slot++) {
						final ItemStack current = inventory.getItem(slot);
						if (current != null && current.isSimilar(item)) {
								final int neededToRemove = Math.min(current.getAmount(), amountLeft);
								current.setAmount(current.getAmount() - neededToRemove);
								amountLeft -= neededToRemove;
								inventory.setItem(slot, current);
						}
				}
		}

		@Override
		public boolean hasAvaliableSlot(Player player) {
				Inventory inv = player.getInventory();
				for (ItemStack item : inv.getContents()) {
						if (item == null) {
								return true;
						}
				}
				return false;
		}

		@Override
		public void update(final InventoryHolder inv) {
				if (inv instanceof Player) {
						// noinspection deprecation
						((Player) inv).updateInventory();
				}
				if (inv instanceof BlockState) {
						((BlockState) inv).update();
				}
		}
}
