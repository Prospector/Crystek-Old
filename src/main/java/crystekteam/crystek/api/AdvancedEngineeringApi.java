package crystekteam.crystek.api;

import crystekteam.crystek.api.recipe.RecipeGrinder;
import crystekteam.crystek.api.recipe.CrafterRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AdvancedEngineeringApi
{
    public static List<CrafterRecipe> crafterRecipes = new ArrayList<CrafterRecipe>();
    public static List<RecipeGrinder> smasherRecipes = new ArrayList<RecipeGrinder>();

    public static CrafterRecipe registerCrafterRecipe(ItemStack output, Object... inputs)
    {
        CrafterRecipe recipe = new CrafterRecipe(output, inputs);
        crafterRecipes.add(recipe);
        return recipe;
    }

    public static RecipeGrinder registerSmasherRecipe(ItemStack output, Object input)
    {
        RecipeGrinder recipe = new RecipeGrinder(output, input);
        smasherRecipes.add(recipe);
        return recipe;
    }
}
