package crystekteam.crystekold.compat.jei.crystallizer;

import crystekteam.crystekold.compat.jei.CrystekRecipeCategoryUids;
import crystekteam.crystekold.lib.ModInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class CrystallizerRecipeCategory implements IRecipeCategory
{
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 26, 28);
        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 86, 28);
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        guiFluidStacks.init(FLUID_SLOT, true, 5, 5, 14, 64, 4000, true, null);

        if (recipeWrapper instanceof CrystallizerRecipeWrapper)
        {
            CrystallizerRecipeWrapper wrapper = (CrystallizerRecipeWrapper) recipeWrapper;
            recipeLayout.getItemStacks().set(INPUT_SLOT, wrapper.getInputs());
            recipeLayout.getItemStacks().set(OUTPUT_SLOT, wrapper.getOutputs());
            guiFluidStacks.set(FLUID_SLOT, wrapper.getFluidInputs());
        }
    }

    private static final int INPUT_SLOT = 0;
    private static final int FLUID_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/jei/crystallizer.png");
    private final IDrawable background;
    private final String title;

    public CrystallizerRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(texture, 34, 6, 108, 74);
        title = I18n.translateToLocal("crystek.jei.category.crystallizer");
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return CrystekRecipeCategoryUids.CRYSTALLIZER;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return title;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft)
    {
    }

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft)
    {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
    {
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 26, 28);
        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 86, 28);
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        guiFluidStacks.init(FLUID_SLOT, true, 5, 5, 14, 64, 4000, true, null);

        if (recipeWrapper instanceof CrystallizerRecipeWrapper)
        {
            CrystallizerRecipeWrapper wrapper = (CrystallizerRecipeWrapper) recipeWrapper;
            recipeLayout.getItemStacks().set(INPUT_SLOT, wrapper.getInputs());
            recipeLayout.getItemStacks().set(OUTPUT_SLOT, wrapper.getOutputs());
            guiFluidStacks.set(FLUID_SLOT, wrapper.getFluidInputs());
        }
    }
}
