package crystekteam.crystek.compat.jei.crystallizer;

import com.google.common.collect.ImmutableList;
import crystekteam.crystek.api.recipe.RecipeCrystallizer;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class CrystallizerRecipeWrapper implements IRecipeWrapper
{
    private final List input;
    private final ItemStack output;
    private final FluidStack fluidStack;

    @SuppressWarnings("unchecked")
    public CrystallizerRecipeWrapper(RecipeCrystallizer recipe)
    {
        ImmutableList.Builder builder = ImmutableList.builder();
        if (recipe.getInput() instanceof ItemStack)
        {
            builder.add(recipe.getInput());
        }
        if (recipe.getInput() instanceof String)
        {
            builder.add(OreDictionary.getOres(((String) recipe.getInput())));
        }
        input = builder.build();
        output = recipe.getOutput();
        fluidStack = recipe.getFluidStack();
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

    @SuppressWarnings("null")
    @Override
    public
    @Nonnull
    List<FluidStack> getFluidInputs()
    {
        if (fluidStack != null)
        {
            return Collections.singletonList(fluidStack);
        }
        return Collections.emptyList();
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, input);
        ingredients.setInput(FluidStack.class, fluidStack);
        ingredients.setOutput(ItemStack.class, output);
    }

    @Override
    public List<FluidStack> getFluidOutputs()
    {
        return ImmutableList.of();
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int i, int i1, int i2, int i3)
    {
    }

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft, int i, int i1)
    {
    }

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
