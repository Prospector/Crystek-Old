package crystekteam.crystek.api.recipe;

import crystekteam.crystek.api.CrystekApi;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by Gigabit101 on 16/06/2016.
 */
public class RecipeGrinder
{
    ItemStack output;
    Object input;

    public RecipeGrinder(ItemStack output, Object input)
    {
        this.output = output;
        this.input = input;
    }

    public boolean matches(ItemStack stack)
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

    public static ItemStack getOutputFrom(ItemStack input)
    {
        if (input != null)
        {
            for (RecipeGrinder recipe : CrystekApi.grinderRecipes)
            {
                if (recipe.matches(input))
                {
                    ItemStack output = recipe.getOutput().copy();
                    return output;
                }
            }
        }
        return null;
    }

    public Object getInput()
    {
        return input;
    }

    public ItemStack getOutput()
    {
        return output;
    }
}
