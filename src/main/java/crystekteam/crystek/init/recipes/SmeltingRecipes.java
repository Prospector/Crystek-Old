package crystekteam.crystek.init.recipes;

import crystekteam.crystek.init.CrystekItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reborncore.common.recipes.RCRecipeMethods;

/**
 * Created by Prospector
 */
public class SmeltingRecipes extends RCRecipeMethods {
	public static void init() {
		register(CrystekItems.MATERIALS.getStack("obsidian_dust"), CrystekItems.MATERIALS.getStack("obsidian_ingot"), 0.8F);
	}

	static void register(ItemStack output, ItemStack input, float xp) {
		GameRegistry.addSmelting(output, input, xp);
	}

	static void register(ItemStack input, ItemStack output) {
		GameRegistry.addSmelting(input, output, 0.3F);
	}
}
