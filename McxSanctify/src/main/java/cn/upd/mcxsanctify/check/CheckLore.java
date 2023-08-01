package cn.upd.mcxsanctify.check;

import cn.upd.mcxsanctify.McxSanctify;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CheckLore implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player= (Player) sender;
//        ItemStack[] contents = player.getInventory().getContents();
        ItemStack[] storageContents = player.getInventory().getStorageContents();


        for (ItemStack itemStack : storageContents) {
            //空气
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                if (itemStack.hasItemMeta()) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta.hasLore()) {
                        List<String> lore = itemMeta.getLore();
                        Bukkit.broadcastMessage("物品介绍:");
                        for (String line : lore) {
                            Bukkit.broadcastMessage(" - " + line);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
