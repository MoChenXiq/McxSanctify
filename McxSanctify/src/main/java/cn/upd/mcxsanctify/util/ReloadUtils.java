package cn.upd.mcxsanctify.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ReloadUtils {
    public static void openMenu(Player player, Inventory menu, Plugin plugin) {
        player.openInventory(menu);

        // 异步刷新菜单
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            // 模拟耗时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 更新菜单内容
            Bukkit.getScheduler().runTask(plugin, () -> {
                // 重新获取菜单内容或者更新原有菜单内容
//                menu.setItem(0, new ItemStack(Material.DIAMOND));
                player.updateInventory();
            });
        });
    }
}
