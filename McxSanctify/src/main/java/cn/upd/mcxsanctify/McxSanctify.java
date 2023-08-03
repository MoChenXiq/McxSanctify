package cn.upd.mcxsanctify;

import cn.upd.mcxsanctify.check.Person;
import cn.upd.mcxsanctify.command.McxSanctifyCommand;
import cn.upd.mcxsanctify.config.Message;
import cn.upd.mcxsanctify.event.Guicheck;
import cn.upd.mcxsanctify.util.ConfigSetting;
import cn.upd.mcxsanctify.util.ReloadUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class McxSanctify extends JavaPlugin {

    @Getter
    private static JavaPlugin instance;

    @Override
    public void onEnable() {

        // 插件启动逻辑
        instance = this;
        //初始化语言文件
        Message.init();
        if (!new File(getDataFolder(), "message.yml").exists()) {
            saveResource("message.yml", false);
        }
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false);
        }
        this.getLogger().info("插件已经启动");
        //McxSanctify注册命令
        Objects.requireNonNull(Bukkit.getPluginCommand("mcxsanctify")).setExecutor(new McxSanctifyCommand());
        ConfigurationSerialization.registerClass(Person.class);

        //监听
        Bukkit.getPluginManager().registerEvents(new Guicheck(),this);


        //生成配置文件
//        saveDefaultConfig();

    }

    public static void reload(){
        if (!new File(getInstance().getDataFolder(), "config.yml").exists()) {
            getInstance().saveResource("config.yml", false);
        }
        ConfigSetting.load(getInstance().getConfig());
//        log(Message.reloadSuccessfully);
    }
    @Override
    public void onDisable() {
        // 插件关闭逻辑
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage("§6[McxSanctify] " +"§a"+ message);
    }

    public static void warning(String message) {
        Bukkit.getConsoleSender().sendMessage("§e[McxSanctify] " +"§e"+ message);
    }


}
