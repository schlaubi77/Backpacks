package schlaubi77.backpack.command;

import schlaubi77.backpack.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class SaveStopCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cServer was stopped!"));
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.shutdown();
            }
        }, 40L);
        return false;
    }
}
