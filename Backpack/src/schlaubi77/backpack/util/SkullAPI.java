package schlaubi77.backpack.util;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.ChatColor;
import java.lang.reflect.Field;
import java.util.logging.Level;
import com.mojang.authlib.properties.Property;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import java.util.UUID;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SkullAPI {
    @SuppressWarnings("deprecation")
	public static ItemStack getSkull(final String name) {
        final ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        final SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(name);
        item.setItemMeta(skullMeta);
        return item;
    }
    
    @SuppressWarnings("deprecation")
	public static ItemStack getSkull(final UUID uuid) {
        final ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        final SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(Bukkit.getOfflinePlayer(uuid).getName());
        item.setItemMeta(skullMeta);
        return item;
    }
    
    public static ItemStack getSkullfromBase64(final String url) {
        final ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        final ItemMeta meta = item.getItemMeta();
        final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        final byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
        }
        catch (NoSuchFieldException | SecurityException e) {
            Bukkit.getLogger().log(Level.SEVERE, "There was a error creating the head skin!", e);
        }
        profileField.setAccessible(true);
        try {
            profileField.set(meta, profile);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            Bukkit.getLogger().log(Level.SEVERE, "There was a error creating the head skin", e);
        }
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack getBackpackSkull() {
        final ItemStack item = getSkullfromBase64("https://textures.minecraft.net/texture/8351e505989838e27287e7afbc7f97e796cab5f3598a76160c131c940d0c5");
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l&bBackpack"));
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
