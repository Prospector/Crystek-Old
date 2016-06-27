package crystekteam.crystek.compat.jei;

import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.client.gui.GuiPoweredFurnace;
import crystekteam.crystek.compat.jei.grinder.GrinderRecipeCategory;
import crystekteam.crystek.client.gui.GuiGrinder;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
@mezz.jei.api.JEIPlugin
public class AdvancedEngineeringJeiPlugin extends BlankModPlugin
{
    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipeCategories(new GrinderRecipeCategory(jeiHelpers.getGuiHelper()));


        //Click area
        registry.addRecipeClickArea(GuiPoweredFurnace.class, 78, 36, 24, 16, VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeClickArea(GuiGrinder.class, 78, 36, 24, 16, RecipeCategoryUids.GRINDER);

        registry.addRecipes(CrystekApi.smasherRecipes);
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {}
}
