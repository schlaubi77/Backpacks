package schlaubi77.backpack.util;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.NamespacedKey;
import schlaubi77.backpack.main.Main;
import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;

public class CraftingRecipe {
    public void registerRecipes() {
        Bukkit.addRecipe(getBpRecipe());
    }
    
    public static Recipe getBpRecipe() {
        final ShapedRecipe bpRecipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "recipe.backpack"), SkullAPI.getBackpackSkull());
        bpRecipe.shape(new String[] { "LSL", "LCL", "LSL" });
        bpRecipe.setIngredient('S', Material.STRING);
        bpRecipe.setIngredient('C', Material.CHEST);
        bpRecipe.setIngredient('L', Material.LEATHER);
        return bpRecipe;
    }
}
