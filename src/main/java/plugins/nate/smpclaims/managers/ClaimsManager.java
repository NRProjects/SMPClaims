package plugins.nate.smpclaims.managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static plugins.nate.smpclaims.utils.ChatUtils.PREFIX;
import static plugins.nate.smpclaims.utils.ChatUtils.sendMessage;

public class ClaimsManager {
    public static void giveClaimTool(Player player) {
        ItemStack chunkSelector = new ItemStack(Material.STICK, 1);
        ItemMeta meta = chunkSelector.getItemMeta();

        if (meta == null) {
            return;
        }

        meta.setDisplayName(ChatColor.GREEN + "Dowsing Rod");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Use this to select chunks for claiming.");
        lore.add(ChatColor.GRAY + "/smp claim confirm to confirm your selection");
        meta.setLore(lore);
        chunkSelector.setItemMeta(meta);

        if(!(player.getInventory().firstEmpty() == -1)) {
            player.getInventory().addItem(chunkSelector);
            sendMessage(player, PREFIX + "&aSelect chunks to start claiming!");
        } else {
            sendMessage(player, PREFIX + "&cYour inventory is full!");
        }
    }

    public static boolean isClaimTool(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }

        List<String> lore = meta.getLore();
        if (lore == null || lore.isEmpty()) {
            return false;
        }

        String loreLine = lore.get(0);
        String strippedLoreLine = ChatColor.stripColor(loreLine);
        return "Use this to select chunks for claiming.".equals(strippedLoreLine);
    }

}
