package cn.upd.mcxsanctify.command;

import cn.upd.mcxsanctify.command.son.CheckHandLore;
import cn.upd.mcxsanctify.command.son.CheckLore;
import cn.upd.mcxsanctify.command.son.HeavyLoad;
import cn.upd.mcxsanctify.command.son.HelpCommand;
import cn.upd.mcxsanctify.config.Message;
import cn.upd.mcxsanctify.command.son.gui.AttributeUpgradesGui;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;


public class McxSanctifyCommand implements TabExecutor{

    private final Map<String, TabExecutor> subCommands = new HashMap<>();

    public McxSanctifyCommand(){
        registerSubCommand("upgui",new AttributeUpgradesGui());
        registerSubCommand("help",new HelpCommand());
        registerSubCommand("checklore",new CheckLore());
        registerSubCommand("handlore",new CheckHandLore());
        registerSubCommand("reload",new HeavyLoad());
    }

    public void registerSubCommand(String subCommand, TabExecutor executor) {
        subCommands.put(subCommand.toLowerCase(), executor);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String subCommand = args[0].toLowerCase();
            TabExecutor executor = subCommands.get(subCommand);
            if (executor != null) {
                return executor.onCommand(sender, command, label, args);
            }
        }else
            return subCommands.get("help").onCommand(sender, command, label, args);

        sender.sendMessage(ChatColor.RED+ Message.help1);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>(subCommands.keySet());
//        if(!sender.hasPermission("mcxsanctify.manager")){
//            if(args.length==1){
//                return Collections.singletonList("");
//            }else {
//                return new ArrayList<>();
//            }
//        }

        if(sender.hasPermission("mcxsanctify.upgui")){
            if(args.length==1){
                return filter(list,args);
            }else {
                String subCommand = args[0].toLowerCase();
                TabExecutor executor = subCommands.get(subCommand);
                if (executor != null) {
                    return executor.onTabComplete(sender, command, label, args);
                }
            }
        }
        return null;
    }

    public static List<String> filter(List<String> list, String[] args) {
        String latest = null;
        if (args.length != 0) {
            latest = args[args.length - 1];
        }
        //isEmpty判断是否为空
        if (list.isEmpty() || latest == null)
            return list;
        String ll = latest.toLowerCase();
        //startsWith判断字符串是否以指定字符或子字符串开头 removeIf() 方法用于删除所有满足特定条件的数组元素。
        list.removeIf(k -> !k.toLowerCase().startsWith(ll));
        return list;
    }

}
