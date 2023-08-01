package cn.upd.mcxsanctify.command;

import cn.upd.mcxsanctify.McxSanctify;
import cn.upd.mcxsanctify.util.Message;
import com.google.common.base.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HeavyLoad implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 执行重载操作
        if(!sender.isOp())
            return false;
        reloadPlugin(); // 这里需要根据你的插件逻辑进行重载操作
        sender.sendMessage(Message.prefix+Message.reloadSuccessfully);

        return false;
    }

    private void reloadPlugin() {
        // 在这里实现插件的重载逻辑
        // 例如重新加载配置文件、重新注册监听器等等
//        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender())
        // 重新加载配置文件
        McxSanctify.reload();


    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}
