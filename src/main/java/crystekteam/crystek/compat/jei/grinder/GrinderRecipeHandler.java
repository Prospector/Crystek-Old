package crystekteam.crystek.compat.jei.grinder;

import crystekteam.crystek.api.recipe.RecipeGrinder;
import crystekteam.crystek.compat.jei.RecipeCategoryUids;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class GrinderRecipeHandler implements IRecipeHandler<RecipeGrinder>
{
	@Nonnull private final IJeiHelpers jeiHelpers;

	public GrinderRecipeHandler(@Nonnull IJeiHelpers jeiHelpers)
	{
		this.jeiHelpers = jeiHelpers;
	}

	@Nonnull
	@Override public String getRecipeCategoryUid(@Nonnull RecipeGrinder recipe)
	{
		return RecipeCategoryUids.GRINDER;
	}

	@Nonnull @Override public Class<RecipeGrinder> getRecipeClass()
	{
		return RecipeGrinder.class;
	}

	@Nonnull @Override public String getRecipeCategoryUid()
	{
		return RecipeCategoryUids.GRINDER;
	}

	@Nonnull @Override public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeGrinder recipeOreSmasher)
	{
		return new GrinderRecipeWrapper(recipeOreSmasher);
	}

	@Override public boolean isRecipeValid(@Nonnull RecipeGrinder recipeGrinder)
	{
		return true;
	}
}
