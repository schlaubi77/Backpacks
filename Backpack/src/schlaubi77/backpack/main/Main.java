package schlaubi77.backpack.main;

import org.bukkit.plugin.PluginManager;
import schlaubi77.backpack.command.SaveStopCommand;
import schlaubi77.backpack.listener.InteractListener;
import schlaubi77.backpack.listener.InventoryCloseListener;
import schlaubi77.backpack.listener.InventoryClickListener;
import org.bukkit.Bukkit;
import schlaubi77.backpack.util.CraftingRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main plugin;
    
    public void onEnable() {
        Main.plugin = this;
        new CraftingRecipe().registerRecipes();
        final PluginManager plMan = Bukkit.getPluginManager();
        plMan.registerEvents(new InventoryClickListener(), this);
        plMan.registerEvents(new InventoryCloseListener(), this);
        plMan.registerEvents(new InteractListener(), this);
        this.getCommand("savestop").setExecutor(new SaveStopCommand());
    }
    
    public static Main getPlugin() {
        return Main.plugin;
    }
}
