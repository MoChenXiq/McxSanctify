package cn.upd.mcxsanctify.event;

import cn.upd.mcxsanctify.McxSanctify;

import cn.upd.mcxsanctify.event.upgrademethod.UpPromote;
import cn.upd.mcxsanctify.util.RamdomTool;
import cn.upd.mcxsanctify.util.RegexUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class Guicheck implements Listener {
//    protected static int[] tankOfEq = {38,29,20,11,19,21}; gui的槽位
    //头
    private static final int head = 11;

    private static final int invhead = 39;
    private static final int cuirass =20;
    private static final int invcuirass =38;
    private static final int pants =29;
    private static final int invpants =37;
    private static final int shoe =38;
    private static final int invshoe =36;
    private static final int deputy =19;
    private static final int invdeputy =40;
    private static final int mainHand =21;



    @EventHandler
    public void onUpAttChange(@NotNull InventoryClickEvent clickEvent){
        Player p = (Player) clickEvent.getWhoClicked();
//        refreshGUI(p,clickEvent.getInventory());
        int size = clickEvent.getSlot();
        p.sendMessage("点击槽位"+size);
        if (clickEvent.getWhoClicked().getOpenInventory().getTitle().equals("§c圣物强化界面")) {
            //阻止玩家的监听
            clickEvent.setCancelled(true);
            switch (clickEvent.getRawSlot()) {
                case shoe:
                    UpPromote.Upgrade(clickEvent,invshoe);
                    p.sendMessage("点击了鞋子");
                    break;
                case pants:
                    UpPromote.Upgrade(clickEvent,invpants);
                    p.sendMessage("点击了裤子");
                    break;
                case cuirass:
                    UpPromote.Upgrade(clickEvent,invcuirass);
                    p.sendMessage("点击了胸甲");
                    break;
                case head:
                    UpPromote.Upgrade(clickEvent,invhead);
                    p.sendMessage("点击了头盔");
                    break;
                case deputy:
                    UpPromote.Upgrade(clickEvent,invdeputy);
                    p.sendMessage("点击了副手");
                    break;
                case mainHand:
                    UpPromote.accHand(p,clickEvent);
                    p.sendMessage("点击了主手");
                    break;
                default:

                    break;
            }
        }
    }

}
