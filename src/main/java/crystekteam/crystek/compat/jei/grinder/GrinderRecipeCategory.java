package crystekteam.crystek.compat.jei.grinder;

import crystekteam.crystek.client.gui.GuiGrinder;
import crystekteam.crystek.compat.jei.CrystekRecipeCategoryUids;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class GrinderRecipeCategory implements IRecipeCategory
{
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int GRINDER_SLOT = 2;

    private final IDrawable background;
    private final String title;

    public GrinderRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(GuiGrinder.textureloc, 7, 15, 141, 55);
        title = I18n.translateToLocal("crystek.jei.category.grinder");
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return CrystekRecipeCategoryUids.GRINDER;
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
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 40, 19);
        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 100, 19);
//        recipeLayout.getItemStacks().init(GRINDER_SLOT, false, 100, 19);

        if (recipeWrapper instanceof GrinderRecipeWrapper)
        {
            GrinderRecipeWrapper alchemyArrayWrapper = (GrinderRecipeWrapper) recipeWrapper;
            recipeLayout.getItemStacks().set(INPUT_SLOT, alchemyArrayWrapper.getInputs());
            recipeLayout.getItemStacks().set(OUTPUT_SLOT, alchemyArrayWrapper.getOutputs());
        }
    }
}
