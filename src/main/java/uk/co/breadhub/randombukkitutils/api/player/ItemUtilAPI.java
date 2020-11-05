package uk.co.breadhub.randombukkitutils.api.player;

import org.bukkit.inventory.ItemStack;

public interface ItemUtilAPI {
    String toItemStackString(final ItemStack item);

    ItemStack getItemStack(final String item);
}
