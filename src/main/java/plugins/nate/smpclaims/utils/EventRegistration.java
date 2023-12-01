package plugins.nate.smpclaims.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import plugins.nate.smpclaims.SMPClaims;
import plugins.nate.smpclaims.listeners.ClaimHandler;

public class EventRegistration {
    public static void registerEvents(SMPClaims plugin) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new ClaimHandler(), plugin);
    }
}
