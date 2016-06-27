package crystekteam.crystek.api;

import crystekteam.crystek.api.recipe.RecipeGrinder;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AdvancedEngineeringApi
{
    public static List<RecipeGrinder> smasherRecipes = new ArrayList<RecipeGrinder>();

    public static RecipeGrinder registerSmasherRecipe(ItemStack output, Object input)
    {
        RecipeGrinder recipe = new RecipeGrinder(output, input);
        smasherRecipes.add(recipe);
        return recipe;
    }
}
