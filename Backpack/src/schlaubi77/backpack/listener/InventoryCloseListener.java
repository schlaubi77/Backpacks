package schlaubi77.backpack.listener;

import org.bukkit.event.EventHandler;
import schlaubi77.backpack.util.BackpackManager;
import schlaubi77.backpack.main.Main;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.Listener;

public class InventoryCloseListener implements Listener {
	
    @EventHandler
    public void handleInvClose(final InventoryCloseEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) 
            return;
        
        if (!e.getView().getTitle().contains("Backpack")) 
            return;
            
        new BackpackManager(Main.getPlugin()).setContents(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().toArray(new String[0])[0].substring(2), e.getInventory().getContents());
    }
}
