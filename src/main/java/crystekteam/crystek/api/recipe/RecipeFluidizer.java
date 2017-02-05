package crystekteam.crystek.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by Prospector
 */
public class RecipeFluidizer {
	Object input;
	FluidStack output;
	int amount;

	public RecipeFluidizer(FluidStack output, Object input) {
		this.input = input;
		this.output = output;
		this.amount = output.amount;
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

	public boolean fluidMatches(FluidStack fluidStack) {
		FluidStack fluidStackCopy = this.output.copy();
		return fluidStack.isFluidEqual(fluidStackCopy);
	}

	public Object getInput() {
		return input;
	}

	public FluidStack getOutput() {
		return output;
	}

	public int getAmount() {
		return amount;
	}

}
