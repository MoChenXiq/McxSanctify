package cn.upd.mcxsanctify.event.dao.imp;

import cn.upd.mcxsanctify.McxSanctify;
import cn.upd.mcxsanctify.event.dao.Elevate;
import cn.upd.mcxsanctify.util.RamdomTool;
import cn.upd.mcxsanctify.util.RegexUtils;
import cn.upd.mcxsanctify.util.ReloadUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ElevateImpl implements Elevate {

    private static final int head = 11;

    private static final int cuirass =20;

    private static final int pants =29;

    private static final int shoe =38;

    private static final int deputy =19;


    protected static int[] tankOfEq = {38, 29, 20, 11, 19}; //gui的

    protected static int[] ontankOfEq = {36, 37, 38, 39, 40};//背包的

    private static final JavaPlugin config = McxSanctify.getProvidingPlugin(McxSanctify.class);

    private static List<String> savatablore = new ArrayList<>(Arrays.asList(config.getConfig().getString("table"), "当前等级:1"));

    private static List<String> def = new ArrayList<>(Arrays.asList(config.getConfig().getString("table"), "当前等级:1"));


    private static Integer level = 0;

    private static String classOfEquipment = null;

    private static int rangesize = config.getConfig().getConfigurationSection("attributeRange").getKeys(false).size();//最大等级

    private static List<String> getlore = null;

    private static Integer nature=0;

    private static Map<Integer, ItemStack> equipmentBar = new HashMap<>();
    @Override
    public void Upgrade(@NotNull InventoryClickEvent clickEvent, Integer val) {
        Player p = (Player) clickEvent.getWhoClicked();
        ItemStack item = p.getInventory().getItem(val);//
        ItemMeta itemMeta = item.getItemMeta();


        switch (val) {
            case 39:
                classOfEquipment = "head";
                break;
            case 38:
                classOfEquipment = "cuirass";
                break;
            case 37:
                classOfEquipment = "pants";
                break;
            case 36:
                classOfEquipment = "shoe";
                break;
            case 40:
                classOfEquipment = "hand";
                break;
            default:
                p.sendMessage("没有此槽位");
                break;

        }

            if (item != null) {
                //模糊匹配
//                if (RegexUtils.contain(config.getConfig().getString("include"),getlore.get(v))) {
                getlore = (itemMeta.hasLore()) ? itemMeta.getLore() : new ArrayList<>();
                if (itemMeta.hasLore()) {
                    for (int i = 0; i < getlore.size(); i++) {
                        if (RegexUtils.contain("当前等级", getlore.get(i))) {
                            String[] strings = getlore.get(i).split(":");
                            Integer numv = Integer.valueOf(strings[1]);
                            level = numv;
                            getlore.set(i, "当前等级:" + (level + 1));
                            p.sendMessage("等级获取成功");
                        }
                    }
                }

                //成功概率
                Integer probability = config.getConfig().getInt("succeed." + (level + 1));


                if (level < rangesize) { //最大等级长度
                    if (RamdomTool.getInt(1, 100) >= probability) {
                        Upson(p, item, clickEvent);
                    } else {
                        p.sendMessage(ChatColor.RED + "强化失败");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "已经达到最大等级上限，请联系管理员增加等级上限");
                }
//                }
//                else {
//                    p.sendMessage(ChatColor.RED +"当前装备不可强化");
//                }

            } else {
                p.sendMessage(ChatColor.RED + "装备栏为空");
            }
    }

    @Override
    public void Upson(Player p, @NotNull ItemStack item, InventoryClickEvent clickEvent) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> attributeshoe = null;
        String attributeRange1 = null;
        Integer armax = 0;
        Integer armin = 0;
        boolean unis=false;
        if (itemMeta.hasLore()) {
            p.sendMessage("有lore");
            for (String con : getlore) {
                p.sendMessage("lore中有那些"+con);
                if (RegexUtils.contain(config.getConfig().getString("table"),con)){
                    p.sendMessage("找到了这个字段");
                    unis=true;
                    p.sendMessage("unis的值为"+unis);
                    break;
                }
                else {
                    p.sendMessage("没有这个字段");
                }
            }
        }else {
            p.sendMessage("没有lore");
        }

        if (unis) {
//            List<String> lore = itemMeta.getLore();
            int currentLevelIndex = -1;
            for (int i = 0; i < getlore.size(); i++) {
                if (RegexUtils.contain("当前等级", getlore.get(i))) {
                    currentLevelIndex = i;

                    break;
                }
            }

            if (currentLevelIndex != -1) {
                attributeshoe = config.getConfig().getStringList("attribute." + classOfEquipment);
                attributeRange1 = config.getConfig().getString("attributeRange." + (level + 1));
                String[] split = attributeRange1.split("-");
                armax = Integer.parseInt(split[1]);
                armin = Integer.parseInt(split[0]);
                /**
                 *
                 */
                for (int j = 0; j < attributeshoe.size(); j++) {
                    String s = getlore.get(currentLevelIndex + 1 + j).replaceAll("\\s+", "");
                    String[] nums = s.split(":");
                    nature= Integer.valueOf(nums[1]);
                    p.sendMessage("强化前的伤害"+nature);
                    Integer price= nature+RamdomTool.getInt(armin, armax);
                    String loreString = attributeshoe.get(j) + " " + price;
                    getlore.add(currentLevelIndex + 1 + j, loreString);
                    p.sendMessage("属性添加完毕");
                }

                if (getlore != null && attributeshoe != null && currentLevelIndex >= 0 && currentLevelIndex < attributeshoe.size()) {
                    Integer attributeLong=0;
                    attributeLong=currentLevelIndex+attributeshoe.size()+1;
                    List<String> sublist = getlore.subList((currentLevelIndex+1+attributeshoe.size()), attributeLong+attributeshoe.size());
                    sublist.clear();
                    p.sendMessage("新增下标删除完毕");
                }

            }

            itemMeta.setLore(getlore);
            item.setItemMeta(itemMeta);
            p.sendMessage(ChatColor.AQUA + "强化成功!");
            p.sendMessage("A1");
            level++;
        }
        else {
            attributeshoe = config.getConfig().getStringList("attribute." + classOfEquipment);
            attributeRange1 = config.getConfig().getString("attributeRange.1");

            String[] split = attributeRange1.split("-");
            armax = Integer.parseInt(split[1]);
            armin = Integer.parseInt(split[0]);
            p.sendMessage("伤害:" + armin + "-" + armax);

            for (int i = 0; i < attributeshoe.size(); i++) {
                p.sendMessage("8");
                String att = String.valueOf(RamdomTool.getInt(armin, armax));
                //
                String loreString = attributeshoe.get(i) + " " + att;
                def.add(loreString);
            }
            boolean order= config.getConfig().getBoolean("order");
            for (String gl:getlore){
                if (order){
                    //排列第一
                    def.add(gl);

                }else {
                    //0表示正常顺序2表示lore显示在第一列
                    def.add(0,gl);
                }

            }
            itemMeta.setLore(def);
            item.setItemMeta(itemMeta);
            //删除当前等级后的下标
            p.sendMessage(ChatColor.AQUA + "强化成功!");
            List<String> sublist = def.subList(2, def.size());
            def.removeAll(sublist);
            p.sendMessage("A2");
        }



        //刷新菜单
        p.updateInventory();
        refreshGUI(p, clickEvent.getInventory());
    }

    @Override
    public void refreshGUI(Player player, Inventory inventor) {

        ItemStack paney = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 10);//玻璃板紫黄色
        ItemMeta paneyMeta = paney.getItemMeta();
        paneyMeta.setDisplayName("§e装备栏");
        List<String> panelorey = new ArrayList<>();
        panelorey.add("§3§l需要身穿装备!");
        panelorey.add("§a§l点击装备...即可强化装备");
        panelorey.add("§a§l点击扣除，点卷，金币，经验...");
        paneyMeta.setLore(panelorey);
        paney.setItemMeta(paneyMeta);


        ItemStack invhead = player.getInventory().getItem(39);
        ItemStack invcuirass = player.getInventory().getItem(38);
        ItemStack invpants = player.getInventory().getItem(37);
        ItemStack invshoe = player.getInventory().getItem(36);
        ItemStack invdeputy = player.getInventory().getItem(40);

        if (invhead != null && invhead.getType() != Material.AIR)inventor.setItem(head,invhead);
        else  inventor.setItem(head, paney);

        if (invcuirass != null && invcuirass.getType() != Material.AIR)inventor.setItem(cuirass,invcuirass);
        else  inventor.setItem(cuirass, paney);

        if (invpants != null && invpants.getType() != Material.AIR)inventor.setItem(pants,invpants);
        else  inventor.setItem(pants, paney);

        if (invshoe != null && invshoe.getType() != Material.AIR)inventor.setItem(shoe,invshoe);
        else  inventor.setItem(shoe, paney);

        if (invdeputy != null && invdeputy.getType() != Material.AIR)inventor.setItem(deputy,invdeputy);
        else  inventor.setItem(deputy, paney);

//            Integer v = 0;
//        for (int eq : ontankOfEq) { //背包
//            ItemStack item = player.getInventory().getItem(eq);
//            if (item != null && item.getType() != Material.AIR) {
//                equipmentBar.put(v++, item);
////                arrayList.add(item);
//            } else {
//                inventor.setItem(tankOfEq[v++], paney);
//            }
//        }
//        if (equipmentBar != null) {
//            for (int i = 0; i < equipmentBar.size(); i++) {
//                inventor.setItem(tankOfEq[i], equipmentBar.get(i));
////                gui.setItem(tankOfEq[i], arrayList.get(i));
//            }
//        }

        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand != null && hand.getType() != Material.AIR) {
            inventor.setItem(21, hand);
        } else {
            inventor.setItem(21, paney);
        }
        player.openInventory(inventor);
        player.updateInventory();
        ReloadUtils.openMenu(player,inventor, McxSanctify.getInstance());
    }

    @Override
    public void accHand(Player p, InventoryClickEvent clickEvent) {
        ItemStack inMainHand = p.getInventory().getItemInMainHand();
        ItemMeta itemMeta = inMainHand.getItemMeta();

        if (itemMeta != null) {
//            //模糊匹配
//            if (RegexUtils.contain(config.getConfig().getString("include"),savatablore.get(i))){
            getlore = (itemMeta.hasLore()) ? itemMeta.getLore() : new ArrayList<>();
            if (itemMeta.hasLore()) {
                for (int i = 0; i < getlore.size(); i++) {
                    if (RegexUtils.contain("当前等级", getlore.get(i))) {
                        String[] strings = getlore.get(i).split(":");
                        Integer numv = Integer.valueOf(strings[1]);
                        level = numv;
                        getlore.set(i, "当前等级:" + (level + 1));
                        p.sendMessage("等级获取成功");
                    }
                }
            }

            //成功概率
            Integer probability = config.getConfig().getInt("succeed." + (level + 1));


            if (level < rangesize) { //最大等级长度
                if (RamdomTool.getInt(1, 100) >= probability) {
                    classOfEquipment = "hand";
                    Upson(p, inMainHand, clickEvent);
                } else {
                    p.sendMessage(ChatColor.RED + "强化失败");
                }
            } else {
                p.sendMessage(ChatColor.RED + "已经达到最大等级上限，请联系管理员增加等级上限");
            }
//        else {
//                p.sendMessage(ChatColor.RED +"当前装备不可强化");
//            }
        } else {
            p.sendMessage(ChatColor.RED + "装备栏为空");
        }

    }
}
