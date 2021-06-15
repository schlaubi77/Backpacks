package schlaubi77.backpack.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void handleInvClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (!e.getView().getTitle().contains("Backpack")) {
            return;
        }
        if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD) && e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_INFINITE) && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().getLore().size() == 1) {
            e.setCancelled(true);
        }
    }
}
