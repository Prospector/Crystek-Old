package crystekteam.crystekold.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class RecipeCrystallizer
{
    Object input;
    FluidStack fluidStack;
    ItemStack output;
    int amount;

    public RecipeCrystallizer(Object input, FluidStack fluidStack, ItemStack output)
    {
        this.input = input;
        this.fluidStack = fluidStack;
        this.output = output;
        this.amount = fluidStack.amount;
    }

    public boolean matches(ItemStack stack, FluidStack fluidStack)
    {
        if (stackmatches(stack) && fluidMatches(fluidStack))
        {
            return true;
        }
        return false;
    }

    public boolean stackmatches(ItemStack stack)
    {
        if (input instanceof ItemStack)
        {
            ItemStack inputCopy = ((ItemStack) input).copy();
            if (inputCopy.getItemDamage() == Short.MAX_VALUE)
            {
                inputCopy.setItemDamage(stack.getItemDamage());
            }
            return stack.isItemEqual(inputCopy);
        }

        if (input instanceof String)
        {
            List<ItemStack> validStacks = OreDictionary.getOres((String) input);

            for (ItemStack ostack : validStacks)
            {
                ItemStack cstack = ostack.copy();
                if (cstack.getItemDamage() == Short.MAX_VALUE)
                {
                    cstack.setItemDamage(stack.getItemDamage());
                }
                if (stack.isItemEqual(cstack))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fluidMatches(FluidStack fluidStack)
    {
        FluidStack fluidStackCopy = this.fluidStack.copy();
        return fluidStack.isFluidEqual(fluidStackCopy);
    }

    public Object getInput()
    {
        return input;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    public FluidStack getFluidStack()
    {
        return fluidStack;
    }

    public int getAmount()
    {
        return amount;
    }

}
