package cn.upd.mcxsanctify.event.dao;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Elevate {
    void Upgrade(@NotNull InventoryClickEvent clickEvent, Integer val);
    void Upson(Player p, @NotNull ItemStack item, InventoryClickEvent clickEvent);
    void refreshGUI(Player player, Inventory inventor);
    void accHand(Player p, InventoryClickEvent clickEvent);
}
