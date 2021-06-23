package schlaubi77.backpack.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;

import schlaubi77.backpack.main.Main;
import schlaubi77.backpack.persistentData.UUIDDataType;

import org.bukkit.event.Listener;

public class InventoryClickListener implements Listener {
	
	private static final UUIDDataType type = new UUIDDataType();
	
    @EventHandler
    public void handleInvClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (!e.getView().getTitle().contains("Backpack")) {
            return;
        }
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "backpack_uuid");
        if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD) && e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_INFINITE) && e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(key, type)) {
        	e.getWhoClicked().sendMessage(ChatColor.RED + "You can't put backpacks into backpacks");
            e.setCancelled(true);
        }
    }
}
