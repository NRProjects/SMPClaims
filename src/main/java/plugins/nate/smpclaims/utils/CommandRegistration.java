package plugins.nate.smpclaims.utils;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import plugins.nate.smpclaims.SMPClaims;
import plugins.nate.smpclaims.commands.ClaimCommand;

public class CommandRegistration {
    public static void registerCommands(SMPClaims plugin) {
        setupCommand("smp", new ClaimCommand(), plugin);
    }

    private static void setupCommand(String commandLabel, CommandExecutor executor, SMPClaims plugin) {
        PluginCommand command = plugin.getCommand(commandLabel);
        if(command != null) {
            command.setExecutor(executor);
        }
    }
}
