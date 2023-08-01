package cn.upd.mcxsanctify.check;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CheckHandLore implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand != null && hand.getType()!= Material.AIR){
            if (hand.hasItemMeta()){//判断有无meta
                ItemMeta itemMeta = hand.getItemMeta();
                if (itemMeta.hasLore()){
                    List<String> lore = itemMeta.getLore();
                    Bukkit.broadcastMessage("手上的物品lore为:");
                    for (String line:lore)Bukkit.broadcastMessage("-"+line);
                }else player.sendMessage(ChatColor.RED +"该物品没有Lore");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
