package plugins.nate.smpclaims.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import plugins.nate.smpclaims.managers.ClaimsManager;

import java.util.List;

import static plugins.nate.smpclaims.utils.ChatUtils.PREFIX;
import static plugins.nate.smpclaims.utils.ChatUtils.sendMessage;

public class ClaimCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sendMessage(sender, PREFIX + "Only players can run this command");
            return true;
        }

//        Player player = (Player) sender;

        if (args.length == 0) {
            // TODO: This is going to be the base message and show the usage.
            return true;
        }

        if (args[0].equalsIgnoreCase("claim")) {
            if (args.length > 1 && args[1].equalsIgnoreCase("select")) {
                ClaimsManager.giveClaimTool(player);
            } else if (args.length > 1 && args[1].equalsIgnoreCase("confirm")) {

            }

            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
