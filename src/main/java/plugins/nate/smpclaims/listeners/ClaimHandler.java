package plugins.nate.smpclaims.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import plugins.nate.smpclaims.managers.ClaimsManager;

import static plugins.nate.smpclaims.managers.ClaimsManager.isClaimTool;
import static plugins.nate.smpclaims.utils.ChatUtils.PREFIX;
import static plugins.nate.smpclaims.utils.ChatUtils.sendMessage;

public class ClaimHandler implements Listener {

    @EventHandler
    public static void onChunkSelection(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = event.getAction();

        if (!isClaimTool(item)) {
            return;
        }

        Chunk chunk = location.getChunk();

        if (action.isLeftClick()) {
            ClaimsManager.selectChunk(player, chunk);
        } else if (action.isRightClick()) {
            ClaimsManager.deselectChunk(player, chunk);
        }
    }
}
