package crystekteam.crystek.compat.jei.grinder;

import crystekteam.crystek.compat.jei.CrystekRecipeCategoryUids;
import crystekteam.crystek.lib.ModInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class GrinderRecipeCategory implements IRecipeCategory
{
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int GRINDER_SLOT = 2;

    public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/jei/grinder.png");
    private final IDrawable background;
    private final String title;

    public GrinderRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(texture, 45, 28, 86, 39);
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
        recipeLayout.getItemStacks().init(INPUT_SLOT, true, 2, 6);
        recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 62, 6);
        recipeLayout.getItemStacks().init(GRINDER_SLOT, true, 30, 19);

        if (recipeWrapper instanceof GrinderRecipeWrapper)
        {
            GrinderRecipeWrapper wrapper = (GrinderRecipeWrapper) recipeWrapper;
            recipeLayout.getItemStacks().set(INPUT_SLOT, wrapper.getInputs());
            recipeLayout.getItemStacks().set(OUTPUT_SLOT, wrapper.getOutputs());
            recipeLayout.getItemStacks().set(GRINDER_SLOT, OreDictionary.getOres("crystekGrindingBlade"));
        }
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IRecipeWrapper iRecipeWrapper, IIngredients iIngredients)
    {

    }
}
