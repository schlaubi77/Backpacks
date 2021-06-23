package schlaubi77.backpack.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import schlaubi77.backpack.util.BackpackManager;
import schlaubi77.backpack.main.Main;
import schlaubi77.backpack.persistentData.UUIDDataType;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class InteractListener implements Listener {
	
	private static final UUIDDataType type = new UUIDDataType();
	private static final NamespacedKey key = new NamespacedKey(Main.getPlugin(), "backpack_uuid");
	
    @EventHandler
    public void handleInteract(final PlayerInteractEvent e) {
    	
        if(e.getItem() == null) 
            return;
        
        ItemMeta meta = e.getItem().getItemMeta();
        if(meta.getEnchants().containsKey(Enchantment.ARROW_INFINITE) && e.getItem().getType().equals(Material.PLAYER_HEAD)) {
        	e.setCancelled(true);
            if(meta.getPersistentDataContainer().has(key, type)) {
                 final Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&l&bBackpack"));
                 inv.setContents(new BackpackManager(Main.getPlugin()).getContent(meta.getPersistentDataContainer().get(key, type).toString()));
                 e.getPlayer().openInventory(inv);
            } else if (e.getItem().getAmount() == 1) {
                if(e.getItem().getItemMeta() instanceof PersistentDataHolder) {
                	UUID uuid = UUID.randomUUID();
                	meta.getPersistentDataContainer().set(key, type, uuid);
                	e.getItem().setItemMeta(meta);
                	new BackpackManager(Main.getPlugin()).setExists(uuid.toString(), true);
                } else
                	Bukkit.getLogger().log(Level.SEVERE, "[Backpacks] Creating the backpack, attempted by " + e.getPlayer().getName() + " failed!");
            } else {
                e.getPlayer().sendMessage("§cYou can't use backpacks in stacked form!");
            }
        }
    }
}
