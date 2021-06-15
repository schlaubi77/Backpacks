package schlaubi77.backpack.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.UUID;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import schlaubi77.backpack.util.BackpackManager;
import schlaubi77.backpack.main.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class InteractListener implements Listener {
	
    @EventHandler
    public void handleInteract(final PlayerInteractEvent e) {
    	
        if(e.getItem() == null) 
            return;
        
        ItemMeta meta = e.getItem().getItemMeta();
        if(meta.getEnchants().containsKey(Enchantment.ARROW_INFINITE) && e.getItem().getType().equals(Material.PLAYER_HEAD)) {
        	e.setCancelled(true);
            if(meta.hasLore()) {
                if(meta.getLore().size() == 1) {
                    final Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&l&bBackpack"));
                    inv.setContents(new BackpackManager(Main.getPlugin()).getContent(meta.getLore().toArray(new String[0])[0].substring(2)));
                    e.getPlayer().openInventory(inv);
                }
            } else if (e.getItem().getAmount() == 1) {
                final ArrayList<String> list = new ArrayList<String>();
                final String uuid = ChatColor.DARK_GRAY + UUID.randomUUID().toString();
                list.add(uuid);
                meta.setLore(list);
                e.getItem().setItemMeta(meta);
                new BackpackManager(Main.getPlugin()).setExists(uuid.substring(2), true);
            } else {
                e.getPlayer().sendMessage("§cYou can't use backpacks in stacked form!");
            }
        }
    }
}
