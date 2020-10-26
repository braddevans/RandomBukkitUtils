package uk.co.breadhub.randombukkitutils.items;

import uk.co.breadhub.randombukkitutils.api.VersionUtilAPI;
import uk.co.breadhub.randombukkitutils.api.player.ItemUtilAPI;
import uk.co.breadhub.randombukkitutils.bukkit.VersionUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;

public class ItemUtils implements ItemUtilAPI {
		public static VersionUtilAPI vapi = new VersionUtils();

		@Override
		public String toItemStackString(final ItemStack item) {
				if (!vapi.HasUUIDs()) {
						if (item.getDurability() != 0) {
								// noinspection deprecation
								return item.getTypeId() + ":" + item.getDurability();
						}
						// noinspection deprecation
						return item.getTypeId() + "";
				}
				if (item.getDurability() != 0) {
						return item.getType().toString() + ":" + item.getDurability();
				}
				return item.getType().toString();
		}

		@Override
		public ItemStack getItemStack(final String item) {
				if (item.contains(":")) {
						final String[] split = item.split(":");
						if (split[0].matches("\\d+")) {
								try {
										final Constructor<?> constructor = Class.forName(vapi.getObcPrefix() + ".inventory.CraftItemStack")
										                                        .getDeclaredConstructor(int.class, int.class, short.class,
														                                        ItemMeta.class);
										constructor.setAccessible(true);
										return (ItemStack) constructor.newInstance(Integer.parseInt(split[0]), 1,
														Short.parseShort(split[1]), null);
								} catch (Exception e) {
										e.printStackTrace();
								}
						}
						return new ItemStack(Material.getMaterial(split[0]), 1, Short.parseShort(split[1]));
				}
				if (item.matches("\\d+")) {
						try {
								final Constructor<?> constructor = Class.forName(vapi.getObcPrefix() + ".inventory.CraftItemStack")
								                                        .getDeclaredConstructor(int.class, int.class, short.class,
												                                        ItemMeta.class);
								constructor.setAccessible(true);
								return (ItemStack) constructor.newInstance(Integer.parseInt(item), 1, (short) 0, null);
						} catch (Exception e) {
								e.printStackTrace();
						}
				}
				return new ItemStack(Material.getMaterial(item));
		}
}
