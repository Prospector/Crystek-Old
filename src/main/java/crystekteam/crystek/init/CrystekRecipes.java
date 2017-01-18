package crystekteam.crystek.init;

import crystekteam.crystek.init.recipes.CraftingTableRecipes;
import crystekteam.crystek.init.recipes.GrinderRecipes;
import crystekteam.crystek.init.recipes.SmeltingRecipes;

/**
 * Created by Prospector
 */
public class CrystekRecipes {
	public static void init() {
		CraftingTableRecipes.init();
		SmeltingRecipes.init();
		GrinderRecipes.init();
	}
}
