package crystekteam.crystek.api.recipe;

import crystekteam.crystek.api.AdvancedEngineeringApi;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CrafterRecipe 
{
	ItemStack output;
	List<Object> inputs;

	public CrafterRecipe(ItemStack output, Object... inputs)
	{
		this.output = output;
		List<Object> inputsToSet = new ArrayList();
		for(Object obj : inputs)
		{
			if(obj instanceof String || obj instanceof ItemStack)
				inputsToSet.add(obj);
			else throw new IllegalArgumentException("Invalid input");
		}
		this.inputs = inputsToSet;
	}
	
	public List<Object> getInputs(ItemStack output)
	{
		List<Object> inputsreturn = new ArrayList(inputs);
		for(CrafterRecipe recipe : AdvancedEngineeringApi.crafterRecipes)
		{
			if(simpleAreStacksEqual(output, recipe.output))
			{
				return recipe.getInputs();
			}
		}
		return null;
	}

	boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2)
	{
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}

	public List<Object> getInputs() {
		return new ArrayList(inputs);
	}

	public ItemStack getOutput() {
		return output;
	}
}
