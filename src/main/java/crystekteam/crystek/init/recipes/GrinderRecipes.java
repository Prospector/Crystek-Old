package crystekteam.crystek.init.recipes;

import crystekteam.crystek.api.CrystekAPI;
import crystekteam.crystek.init.CrystekItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import reborncore.common.recipes.RCRecipeMethods;

/**
 * Created by Prospector
 */
public class GrinderRecipes extends RCRecipeMethods {
	public static void init() {
		register(CrystekItems.MATERIALS.getStack("iron_dust", 2), "oreIron", 1, true);
		register(CrystekItems.MATERIALS.getStack("gold_dust", 2), "oreGold", 1, true);
		register(new ItemStack(Items.DYE, 5, 4), "oreLapis", 1, true);
		register(new ItemStack(Items.REDSTONE, 5), "oreRedstone", 1, true);
		register(CrystekItems.MATERIALS.getStack("diamond_dust", 2), "oreDiamond", 1, true);
		register(CrystekItems.MATERIALS.getStack("emerald_dust", 2), "oreEmerald", 1, true);
		register(CrystekItems.MATERIALS.getStack("obsidian_dust", 2), "obsidian", 1, true);
	}

	static void register(ItemStack output, Object input, int grindingBladeMinimum, boolean canAnvil) {
		CrystekAPI.registerGrinderRecipe(output, input, grindingBladeMinimum, canAnvil);
	}

	static void register(ItemStack output, Object input, int grindingBladeMinimum) {
		register(output, input, grindingBladeMinimum, false);
	}
}
