package crystekteam.crystek.compat.jei;

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

        registry.addRecipeCategories(new GrinderRecipeCategory(jeiHelpers.getGuiHelper()), new CrystallizerRecipeCategory(jeiHelpers.getGuiHelper()));
        registry.addRecipeHandlers(new GrinderRecipeHandler(jeiHelpers), new CrystallizerRecipeHandler(jeiHelpers));


        registry.addRecipeClickArea(GuiGrinder.class, 78, 36, 24, 16, CrystekRecipeCategoryUids.GRINDER);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.grinder), CrystekRecipeCategoryUids.GRINDER);
        registry.addRecipes(CrystekApi.grinderRecipes);

        registry.addRecipeClickArea(GuiCrystallizer.class, 78, 36, 24, 16, CrystekRecipeCategoryUids.CRYSTALLIZER);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.crystallizer), CrystekRecipeCategoryUids.CRYSTALLIZER);
        registry.addRecipes(CrystekApi.crystallizerRecipes);

        //vanilla
        registry.addRecipeClickArea(GuiPoweredFurnace.class, 78, 36, 24, 16, VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.poweredFurnace), VanillaRecipeCategoryUid.SMELTING);
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {}
}
