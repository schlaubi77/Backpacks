package schlaubi77.backpack.util;

import java.io.IOException;
import java.util.logging.Level;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import schlaubi77.backpack.main.Main;

public class DataManager {
    private Main plugin;
    private FileConfiguration dataConfig;
    private File configFile;
    private final String FILE_NAME = "data.yml";
    
    public DataManager(final Main plugin) {
        this.dataConfig = null;
        this.configFile = null;
        this.plugin = plugin;
        this.saveDefaultConfig();
    }
    
    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), FILE_NAME);
        }
        this.dataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
        final InputStream defaultStream = this.plugin.getResource(FILE_NAME);
        if (defaultStream != null) {
            final YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }
    
    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            this.reloadConfig();
        }
        return this.dataConfig;
    }
    
    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null) {
            return;
        }
        try {
            this.getConfig().save(this.configFile);
        }
        catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Saving of File " + FILE_NAME + " has failed", e);
        }
    }
    
    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");
        }
        if (!this.configFile.exists()) {
            try {
                this.configFile.createNewFile();
            }
            catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Creating the file data.yml failed! Backpacks will not keep their items!", e);
            }
        }
    }
}
