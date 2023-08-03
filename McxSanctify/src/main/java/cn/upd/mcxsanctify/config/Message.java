package cn.upd.mcxsanctify.config;

import cn.upd.mcxsanctify.McxSanctify;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Message {
    public static String prefix;
    public static String title1;

    public static String reload1;
    public static String reload2;
    public static String reloadSuccessfully;

    public static String help1;


    private static FileConfiguration config;
    public static void init(){
        File LangConfigFile = new File(McxSanctify.getInstance().getDataFolder(), "message.yml");
        config = YamlConfiguration.loadConfiguration(LangConfigFile);
        prefix = getString("prefix","&l&b[&McxSanctify&b]&r");
        title1 = getString("command.title1","&c&m    &e&m    &a&m    &f&b&lMCX&6命令提示&c&m    &e&m    &a&m    &f");
        reload1 = getString("command.reload1","&b/mcx reload");
        reload2 = getString("command.reload2","&a重载插件~ 请不要使用plugman启用!");
        reloadSuccessfully = getString("command.reloadSuccessfully","&a重载成功~(如果后台没报错的话)");
        help1 = getString("command.help1","&c/xes help 获得帮助!");
    }

    static String getString(String path,String def){
        return ChatColor.translateAlternateColorCodes('&',config.getString(path,def));
    }


}
