package schlaubi77.backpack.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import schlaubi77.backpack.util.BackpackManager;
import schlaubi77.backpack.main.Main;
import schlaubi77.backpack.persistentData.UUIDDataType;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.Listener;

public class InventoryCloseListener implements Listener {
	
	private static final UUIDDataType type = new UUIDDataType();
	
    @EventHandler
    public void handleInvClose(final InventoryCloseEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) 
            return;
        
        if (!e.getView().getTitle().contains("Backpack")) 
            return;
            
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "backpack_uuid");
        new BackpackManager(Main.getPlugin()).setContents(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(key, type).toString(), e.getInventory().getContents());
    }
}
