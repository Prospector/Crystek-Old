package crystekteam.crystek.api;

import crystekteam.crystek.api.recipe.RecipeCrystallizer;
import crystekteam.crystek.api.recipe.RecipeGrinder;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class CrystekApi
{
    public static List<RecipeGrinder> smasherRecipes = new ArrayList<RecipeGrinder>();
    public static List<RecipeCrystallizer> crystallizerRecipes = new ArrayList<RecipeCrystallizer>();
    public static List<FluidFuelHandler> fluidFuelHandlers = new ArrayList<FluidFuelHandler>();

    public static RecipeGrinder registerSmasherRecipe(ItemStack output, Object input)
    {
        RecipeGrinder recipe = new RecipeGrinder(output, input);
        smasherRecipes.add(recipe);
        return recipe;
    }

    public static RecipeCrystallizer registerCrystallizerRecipes(Object input, FluidStack fluidStack, ItemStack output)
    {
        RecipeCrystallizer recipe = new RecipeCrystallizer(input, fluidStack, output);
        crystallizerRecipes.add(recipe);
        return recipe;
    }

    public static FluidFuelHandler registerFuel(FluidStack fluidStack, long amount, long output)
    {
        FluidFuelHandler fuelHandler = new FluidFuelHandler(fluidStack, amount, output);
        fluidFuelHandlers.add(fuelHandler);
        return fuelHandler;
    }
}
