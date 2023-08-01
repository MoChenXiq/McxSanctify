package cn.upd.mcxsanctify.gui;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.util.*;

public class AttributeUpgradesGui implements TabExecutor {

    protected static int[] border = {0,1,2,3,4,5,6,7,8,9,10,12,13,17,18,22,26,27,28,30,31,35,36,37,39,40,44,46,47,48,49,50,51,52};
    protected static int[] tankOfEq = {38,29,20,11,19}; //gui的
    protected static int[] ontankOfEq = {36,37,38,39,40};//背包的

    private final Map<Integer, ItemStack> equipmentBar = new HashMap<>();

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        gainEquip(sender);


        return false;
    }
    public void gainEquip(CommandSender sender){
        Player player= (Player) sender;
        Inventory gui = Bukkit.createInventory(null, 6 * 9, "§c圣物强化界面");
        ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 0);//玻璃板紫红
        ItemMeta glassPane = pane.getItemMeta();//玻璃板meta
        glassPane.setDisplayName("§5圣物能量保护板");
        List<String> panelore = new ArrayList<>();
        panelore.add("§3§l强化物放入此处");
        panelore.add("§3§l你可以自由发挥");
        glassPane.setLore(panelore);
        pane.setItemMeta(glassPane);
        for (int index:border){
            gui.setItem(index,pane);
        }

        ItemStack paney = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 10);//玻璃板紫黄色
        ItemMeta paneyMeta = paney.getItemMeta();
        paneyMeta.setDisplayName("§e装备栏");
        List<String> panelorey = new ArrayList<>();
        panelorey.add("§3§l需要身穿装备!");
        panelorey.add("§a§l点击装备...即可强化装备");
        panelorey.add("§a§l点击扣除，点卷，金币，经验...");
        paneyMeta.setLore(panelorey);
        paney.setItemMeta(paneyMeta);

        Integer v=0;

        for (int eq:ontankOfEq) {
            ItemStack item = player.getInventory().getItem(eq);
            if (item != null && item.getType() != Material.AIR) {
                equipmentBar.put(v++,item);
//                arrayList.add(item);
            }else {
                gui.setItem(tankOfEq[v++],paney);
            }
        }
        if (equipmentBar != null){
            for (int i = 0; i < equipmentBar.size(); i++) {
                gui.setItem(tankOfEq[i],equipmentBar.get(i));
//                gui.setItem(tankOfEq[i], arrayList.get(i));
            }
        }
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand != null && hand.getType() != Material.AIR) {
            gui.setItem(21, hand);
        }else {
            gui.setItem(21,paney);
        }

        player.openInventory(gui);
        player.updateInventory();


    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        return null;
    }
}
