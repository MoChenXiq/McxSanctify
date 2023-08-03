package cn.upd.mcxsanctify.util;

import cn.upd.mcxsanctify.McxSanctify;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;

public class ConfigSetting {
    public static String version;
    public static boolean checkNewVersion;

    public static void load(Configuration config){
        version=config.getString("version","1-0-0");
        checkNewVersion = config.getBoolean("checkNewVersion",true);
        if(checkNewVersion){
            Bukkit.getScheduler().runTaskAsynchronously(McxSanctify.getInstance(), NewVersionUtils::checkNewVersion);
        }
    }


}
