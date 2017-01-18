package crystekteam.crystek.api.recipe;

import crystekteam.crystek.api.CrystekAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class RecipeGrinder {
	ItemStack output;
	Object input;
	int grindingBladeMinimum;
	boolean canAnvil = false;

	public RecipeGrinder(ItemStack output, Object input, int grindingBladeMinimum, boolean canAnvil) {
		this.output = output;
		this.input = input;
		this.grindingBladeMinimum = grindingBladeMinimum;
		this.canAnvil = canAnvil;
	}

	public static ItemStack getOutputFrom(ItemStack input) {
		if (input != null) {
			for (RecipeGrinder recipe : CrystekAPI.GRINDER_RECIPES) {
				if (recipe.matches(input)) {
					ItemStack output = recipe.getOutput().copy();
					return output;
				}
			}
		}
		return null;
	}

	public boolean matches(ItemStack stack) {
		if (input instanceof ItemStack) {
			ItemStack inputCopy = ((ItemStack) input).copy();
			if (inputCopy.getItemDamage() == Short.MAX_VALUE) {
				inputCopy.setItemDamage(stack.getItemDamage());
			}
			return stack.isItemEqual(inputCopy);
		}

		if (input instanceof String) {
			List<ItemStack> validStacks = OreDictionary.getOres((String) input);

			for (ItemStack ostack : validStacks) {
				ItemStack cstack = ostack.copy();
				if (cstack.getItemDamage() == Short.MAX_VALUE) {
					cstack.setItemDamage(stack.getItemDamage());
				}
				if (stack.isItemEqual(cstack)) {
					return true;
				}
			}
		}
		return false;
	}

	public Object getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public int getGrindingBladeMinimum() {
		return grindingBladeMinimum;
	}

	/*
		Yes, anvillable. Means able to be anvilled. Anvilled means being crushed by dropping an anvil on it. Anvil is a verb.
	 */
	public boolean isAnvillable() {
		return canAnvil;
	}
}