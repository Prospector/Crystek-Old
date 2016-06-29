package crystekteam.crystek.compat.jei;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.client.gui.GuiCrystallizer;
import crystekteam.crystek.client.gui.GuiGrinder;
import crystekteam.crystek.client.gui.GuiPoweredFurnace;
import crystekteam.crystek.compat.jei.crystallizer.CrystallizerRecipeCategory;
import crystekteam.crystek.compat.jei.crystallizer.CrystallizerRecipeHandler;
import crystekteam.crystek.compat.jei.grinder.GrinderRecipeCategory;
import crystekteam.crystek.compat.jei.grinder.GrinderRecipeHandler;
import crystekteam.crystek.init.ModBlocks;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
@mezz.jei.api.JEIPlugin
public class CrystekJeiPlugin extends BlankModPlugin
{
    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        //Grinder
        registry.addRecipeCategories(new GrinderRecipeCategory(jeiHelpers.getGuiHelper()), new CrystallizerRecipeCategory(jeiHelpers.getGuiHelper()));
        registry.addRecipeHandlers(new GrinderRecipeHandler(jeiHelpers), new CrystallizerRecipeHandler(jeiHelpers));


        registry.addRecipeClickArea(GuiGrinder.class, 78, 36, 24, 16, RecipeCategoryUids.GRINDER);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.grinder), RecipeCategoryUids.GRINDER);
        registry.addRecipes(CrystekApi.smasherRecipes);
        registry.addRecipeClickArea(GuiCrystallizer.class, 78, 36, 24, 16, RecipeCategoryUids.CRYSTALLIZER);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.crystallizer), RecipeCategoryUids.CRYSTALLIZER);
        registry.addRecipes(CrystekApi.crystallizerRecipes);

        //vanilla
        registry.addRecipeClickArea(GuiPoweredFurnace.class, 78, 36, 24, 16, VanillaRecipeCategoryUid.SMELTING);
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {}
}
