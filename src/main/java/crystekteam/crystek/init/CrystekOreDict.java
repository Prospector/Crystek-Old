package crystekteam.crystek.init;

import crystekteam.crystek.Crystek;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Prospector
 */
public class CrystekOreDict {

	public static void init() {
		for (ItemStack stack : Crystek.MOD_CL.getItemRegistry().oreEntries.keySet()) {
			OreDictionary.registerOre(Crystek.MOD_CL.getItemRegistry().oreEntries.get(stack), stack);
		}
	}

}
