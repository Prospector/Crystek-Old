package crystekteam.crystek.api;

import crystekteam.crystek.api.recipe.RecipeFluidizer;
import crystekteam.crystek.api.recipe.RecipeGrinder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class CrystekAPI {
	public static List<RecipeGrinder> GRINDER_RECIPES = new ArrayList<>();
	public static List<RecipeFluidizer> FLUIDIZER_RECIPES = new ArrayList<>();

	public static RecipeGrinder registerGrinderRecipe(ItemStack output, Object input, int grindingBladeMinimum, boolean canAnvil) {
		RecipeGrinder recipe = new RecipeGrinder(output, input, grindingBladeMinimum, canAnvil);
		GRINDER_RECIPES.add(recipe);
		return recipe;
	}

	public static RecipeFluidizer registerFluidizerRecipe(FluidStack output, Object input) {
		RecipeFluidizer recipe = new RecipeFluidizer(output, input);
		FLUIDIZER_RECIPES.add(recipe);
		return recipe;
	}
}
