package plugins.nate.smpclaims;

import org.bukkit.plugin.java.JavaPlugin;
import plugins.nate.smpclaims.utils.CommandRegistration;
import plugins.nate.smpclaims.utils.EventRegistration;

public final class SMPClaims extends JavaPlugin {
    private SMPClaims plugin;


    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        EventRegistration.registerEvents(this);
        CommandRegistration.registerCommands(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SMPClaims getPlugin() {
        return plugin;
    }
}
