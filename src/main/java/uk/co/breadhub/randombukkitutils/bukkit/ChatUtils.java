package uk.co.breadhub.randombukkitutils.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {

		public static void sendColorMessage(final CommandSender sender, final String message) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}
}
