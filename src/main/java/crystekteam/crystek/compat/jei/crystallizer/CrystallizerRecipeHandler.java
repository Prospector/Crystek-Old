package crystekteam.crystek.compat.jei.crystallizer;

import crystekteam.crystek.api.recipe.RecipeCrystallizer;
import crystekteam.crystek.api.recipe.RecipeGrinder;
import crystekteam.crystek.compat.jei.RecipeCategoryUids;
import crystekteam.crystek.compat.jei.grinder.GrinderRecipeWrapper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class CrystallizerRecipeHandler implements IRecipeHandler<RecipeCrystallizer>
{
	@Nonnull private final IJeiHelpers jeiHelpers;

	public CrystallizerRecipeHandler(@Nonnull IJeiHelpers jeiHelpers)
	{
		this.jeiHelpers = jeiHelpers;
	}

	@Nonnull
	@Override public String getRecipeCategoryUid(@Nonnull RecipeCrystallizer recipe)
	{
		return RecipeCategoryUids.CRYSTALLIZER;
	}

	@Nonnull @Override public Class<RecipeCrystallizer> getRecipeClass()
	{
		return RecipeCrystallizer.class;
	}

	@Nonnull @Override public String getRecipeCategoryUid()
	{
		return RecipeCategoryUids.CRYSTALLIZER;
	}

	@Nonnull @Override public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeCrystallizer recipeOreSmasher)
	{
		return new CrystallizerRecipeWrapper(recipeOreSmasher);
	}

	@Override public boolean isRecipeValid(@Nonnull RecipeCrystallizer recipeGrinder)
	{
		return true;
	}
}
