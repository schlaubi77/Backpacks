package schlaubi77.backpack.util;

import org.bukkit.inventory.ItemStack;
import schlaubi77.backpack.main.Main;

public class BackpackManager
{
    private static DataManager data;
    private Main main;
    // bp.uuid.index
    
    public BackpackManager(final Main main) {
        this.main = main;
        BackpackManager.data = new DataManager(this.main);
    }
    
    public void setContents(final String uuid, final ItemStack[] contents) {
        for (int i = 0; i < 27; ++i) {
            BackpackManager.data.getConfig().set("bp." + uuid + "." + i, contents[i]);
        }
        BackpackManager.data.getConfig().set("bp." + uuid + ".exists", true);
        BackpackManager.data.saveConfig();
    }
    
    public ItemStack[] getContent(final String uuid) {
        if (this.exists(uuid)) {
            final ItemStack[] r = new ItemStack[3 * 9];
            for (int i = 0; i < 27; ++i) {
                r[i] = BackpackManager.data.getConfig().getItemStack("bp." + uuid + "." + i);
            }
            return r;
        }
        return new ItemStack[3 * 9];
    }
    
    public boolean exists(final String uuid) {
        return BackpackManager.data.getConfig().getBoolean("bp." + uuid + ".exists");
    }
    
    public void setExists(final String uuid, final boolean exists) {
        BackpackManager.data.getConfig().set("bp." + uuid + ".exists", exists);
        BackpackManager.data.saveConfig();
    }
}
