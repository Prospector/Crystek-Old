package crystekteam.crystek.compat.jei.crystallizer;

import crystekteam.crystek.client.gui.GuiCrystallizer;
import crystekteam.crystek.compat.jei.CrystekRecipeCategoryUids;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class CrystallizerRecipeCategory implements IRecipeCategory
{
    private static final int INPUT_SLOT = 0;
    private static final int FLUID_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private final IDrawable background;
    private final String title;

    public CrystallizerRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(GuiCrystallizer.textureloc, 5, 5, 140, 76);
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
    public void drawExtras(@Nonnull Minecraft minecraft) {}

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft) {}

    @SuppressWarnings("unchecked")
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
    {
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 55, 29);
        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 116, 29);
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        guiFluidStacks.init(FLUID_SLOT, true, 34, 5, 14, 65, 4000, true, null);

        if (recipeWrapper instanceof CrystallizerRecipeWrapper)
        {
            CrystallizerRecipeWrapper alchemyArrayWrapper = (CrystallizerRecipeWrapper) recipeWrapper;
            recipeLayout.getItemStacks().set(INPUT_SLOT, alchemyArrayWrapper.getInputs());
            recipeLayout.getItemStacks().set(OUTPUT_SLOT, alchemyArrayWrapper.getOutputs());
            guiFluidStacks.set(FLUID_SLOT, alchemyArrayWrapper.getFluidInputs());
        }
    }
}
