package plugins.nate.smpclaims.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {
    public static final String PREFIX = "&8[&a&lSMPClaims&8] &r";
    public static final String DENIED_MESSAGE = PREFIX + "&cYou do not have access to this command";

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
