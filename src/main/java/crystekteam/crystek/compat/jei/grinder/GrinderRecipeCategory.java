package crystekteam.crystek.compat.jei.grinder;

import crystekteam.crystek.client.gui.GuiGrinder;
import crystekteam.crystek.compat.jei.RecipeCategoryUids;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
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

    private final IDrawable background;
    private final IDrawableAnimated electricity;
    private final String title;

    public GrinderRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(GuiGrinder.INVENTORY_BACKGROUND, 46, 16, 91, 54);
        IDrawableStatic electricityDrawable = guiHelper.createDrawable(GuiGrinder.ICONS, 176, 0, 14, 14);
        electricity = guiHelper.createAnimatedDrawable(electricityDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);
        title = I18n.translateToLocal("crystek.jei.category.grinder");
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return RecipeCategoryUids.GRINDER;
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
    public void drawAnimations(@Nonnull Minecraft minecraft)
    {
        electricity.draw(minecraft, 10, 20);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
    {
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 0, 5);
        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 73, 5);

        if (recipeWrapper instanceof GrinderRecipeWrapper)
        {
            GrinderRecipeWrapper alchemyArrayWrapper = (GrinderRecipeWrapper) recipeWrapper;
            recipeLayout.getItemStacks().set(INPUT_SLOT, alchemyArrayWrapper.getInputs());
            recipeLayout.getItemStacks().set(OUTPUT_SLOT, alchemyArrayWrapper.getOutputs());
        }
    }
}
