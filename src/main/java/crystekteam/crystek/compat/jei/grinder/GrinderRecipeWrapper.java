package crystekteam.crystek.compat.jei.grinder;

import com.google.common.collect.ImmutableList;
import crystekteam.crystek.api.recipe.RecipeGrinder;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class GrinderRecipeWrapper implements IRecipeWrapper
{
    private final List input;
    private final ItemStack output;

    @SuppressWarnings("unchecked")
    public GrinderRecipeWrapper(RecipeGrinder recipe)
    {
        ImmutableList.Builder builder = ImmutableList.builder();
            if(recipe.getInput() instanceof ItemStack)
            {
                builder.add(recipe.getInput());
            }
            if(recipe.getInput() instanceof String)
            {
                builder.add(OreDictionary.getOres(((String) recipe.getInput())));
            }
        input = builder.build();
        output = recipe.getOutput();
    }

    @Override
    public List getInputs()
    {
        return input;
    }

    @Override
    public List getOutputs()
    {
        return ImmutableList.of(output);
    }

    @Override
    public List<FluidStack> getFluidInputs()
    {
        return ImmutableList.of();
    }

    @Override
    public List<FluidStack> getFluidOutputs()
    {
        return ImmutableList.of();
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int i, int i1, int i2, int i3) {}

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft, int i, int i1) {}

    @Nullable
    @Override
    public List<String> getTooltipStrings(int i, int i1)
    {
        return ImmutableList.of();
    }

    @Override
    public boolean handleClick(@Nonnull Minecraft minecraft, int i, int i1, int i2)
    {
        return false;
    }
}
