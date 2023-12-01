package plugins.nate.smpclaims.managers;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import plugins.nate.smpclaims.utils.Utils;

import java.util.*;

import static plugins.nate.smpclaims.utils.ChatUtils.PREFIX;
import static plugins.nate.smpclaims.utils.ChatUtils.sendMessage;

public class ClaimsManager {
    private static final int MAX_CHUNKS = 9;
    private static final Map<UUID, Set<Chunk>> selections = new HashMap<>();


    public static void selectChunk(Player player, Chunk chunk) {
        Set<Chunk> selectedChunks = selections.computeIfAbsent(player.getUniqueId(), chunkList -> new HashSet<>());

        if (selectedChunks.size() >= MAX_CHUNKS) {
            sendMessage(player, PREFIX + "&cYou have selected the maximum number of chunks!");
            return;
        }

        if (selectedChunks.contains(chunk)) {
            sendMessage(player, PREFIX + "&cYou have already selected this chunk!");
            return;
        }

        selectedChunks.add(chunk);
        sendMessage(player, PREFIX + "&aSelected chunk! (X: " + chunk.getX() + " Z: " + chunk.getZ() + ")");
    }

    public static void deselectChunk(Player player, Chunk chunk) {
        Set<Chunk> selectedChunks = selections.get(player.getUniqueId());

        if (selectedChunks == null) {
            sendMessage(player, PREFIX + "&cThis chunk was not selected!");
            return;
        }

        if (selectedChunks.contains(chunk)) {
            selectedChunks.remove(chunk);
            sendMessage(player, PREFIX + "&aDeselected chunk! (X: " + chunk.getX() + " Z: " + chunk.getZ() + ")");
        } else {
            sendMessage(player, PREFIX + "&cThis chunk was not selected!");
        }
    }

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
