package crystekteam.crystekold.compat.jei.grinder;

import crystekteam.crystekold.api.recipe.RecipeGrinder;
import crystekteam.crystekold.compat.jei.CrystekRecipeCategoryUids;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class GrinderRecipeHandler implements IRecipeHandler<RecipeGrinder>
{
    @Nonnull
    private final IJeiHelpers jeiHelpers;

    public GrinderRecipeHandler(@Nonnull IJeiHelpers jeiHelpers)
    {
        this.jeiHelpers = jeiHelpers;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull RecipeGrinder recipe)
    {
        return CrystekRecipeCategoryUids.GRINDER;
    }

    @Nonnull
    @Override
    public Class<RecipeGrinder> getRecipeClass()
    {
        return RecipeGrinder.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return CrystekRecipeCategoryUids.GRINDER;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull RecipeGrinder recipe)
    {
        return new GrinderRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull RecipeGrinder recipe)
    {
        return true;
    }
}
