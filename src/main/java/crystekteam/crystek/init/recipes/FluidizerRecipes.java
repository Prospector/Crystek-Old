package crystekteam.crystek.init.recipes;

import crystekteam.crystek.api.CrystekAPI;
import crystekteam.crystek.init.CrystekBlocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import reborncore.common.recipes.RCRecipeMethods;

/**
 * Created by Prospector
 */
public class FluidizerRecipes extends RCRecipeMethods {
	public static void init() {
		register(CrystekBlocks.TESLARINE_FLUID, 10, "crystalTesla");
	}

	static void register(Fluid output, int amount, Object input) {
		register(new FluidStack(output, amount), input);
	}

	static void register(Fluid output, Object input) {
		register(new FluidStack(output, 1000), input);
	}

	static void register(FluidStack output, Object input) {
		CrystekAPI.registerFluidizerRecipe(output, input);
	}
}
