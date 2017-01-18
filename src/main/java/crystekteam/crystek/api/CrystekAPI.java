package crystekteam.crystek.api;

import crystekteam.crystek.api.recipe.RecipeGrinder;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class CrystekAPI {
	public static List<RecipeGrinder> GRINDER_RECIPES = new ArrayList<RecipeGrinder>();

	public static RecipeGrinder registerGrinderRecipe(ItemStack output, Object input, int grindingBladeMinimum, boolean canAnvil) {
		RecipeGrinder recipe = new RecipeGrinder(output, input, grindingBladeMinimum, canAnvil);
		GRINDER_RECIPES.add(recipe);
		return recipe;
	}
}
