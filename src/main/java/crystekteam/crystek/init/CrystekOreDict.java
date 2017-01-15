package crystekteam.crystek.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Prospector
 */
public class CrystekOreDict {

	public static void init() {
		for (ItemStack stack : CrystekItems.ORE_DICT.keySet()) {
			OreDictionary.registerOre(CrystekItems.ORE_DICT.get(stack), stack);
		}
	}

}
