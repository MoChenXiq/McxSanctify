package cn.upd.mcxsanctify.command.son;

import cn.upd.mcxsanctify.config.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class HelpCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            sender.sendMessage(Message.title1);
            sender.sendMessage(ChatColor.AQUA + "/McxSanctify help " + ChatColor.BLUE + "打开命令帮助列表");
            sender.sendMessage(ChatColor.AQUA + "/McxSanctify upgui " + ChatColor.BLUE + "打开升级界面");
            sender.sendMessage(ChatColor.AQUA + "/McxSanctify checklore " + ChatColor.BLUE + "检查背包全部物品lore");
            sender.sendMessage(ChatColor.AQUA + "/McxSanctify handlore " + ChatColor.BLUE + "手上物品lore");
            sender.sendMessage(ChatColor.AQUA + "/McxSanctify reload " + ChatColor.BLUE + "重载插件");
        }else {

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
