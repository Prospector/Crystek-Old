package crystekteam.crystek.init;

import crystekteam.crystek.items.ItemMetadataCrystek;
import crystekteam.crystek.items.misc.ItemCrystallineBonemeal;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reborncore.modcl.ItemCL;
import reborncore.modcl.ItemMetadataCL;
import reborncore.modcl.ModCL;

import java.util.HashMap;

/**
 * Created by Prospector
 */
public class CrystekItems {

	public static HashMap<ItemStack, String> ORE_DICT = new HashMap<>();
	public static HashMap<String, ItemCL> REGISTRY = new HashMap<>();
	public static ItemMetadataCL MATERIALS = new ItemMetadataCrystek("material");
	public static ItemCL CRYSTALLINE_BONEMEAL = new ItemCrystallineBonemeal();
	private static HashMap<String, String> MATERIALS_DICT = new HashMap<>();

	public static void init(ModCL mod) {
		addMaterial("crystal", "crystalTesla");
		addMaterial("blue_crystal", "crystalTeslaBlue");
		addMaterial("purple_crystal", "crystalTeslaPurple");
		addMaterial("red_crystal", "crystalTeslaRed");
		addMaterial("yellow_crystal", "crystalTeslaYellow");
		addMaterial("green_crystal", "crystalTeslaGreen");

		addMaterial("crystal_dust", "dustCrystalTesla");
		addMaterial("blue_crystal_dust", "dustCrystalTeslaBlue");
		addMaterial("purple_crystal_dust", "dustCrystalTeslaPurple");
		addMaterial("red_crystal_dust", "dustCrystalTeslaRed");
		addMaterial("yellow_crystal_dust", "dustCrystalTeslaYellow");
		addMaterial("green_crystal_dust", "dustCrystalTeslaGreen");
		addMaterial("iron_dust", "dustIron");
		addMaterial("gold_dust", "dustGold");
		addMaterial("diamond_dust", "dustDiamond");
		addMaterial("emerald_dust", "dustEmerald");
		addMaterial("obsidian_dust", "dustObsidian");

		addMaterial("crystalline_iron_ingot", "ingotCrystallineIron");
		addMaterial("obsidian_ingot", "ingotObsidian");
		addMaterial("rubrium_ingot", "ingotRubrium");

		REGISTRY.put("material", MATERIALS);
		REGISTRY.put("crystalline_bonemeal", CRYSTALLINE_BONEMEAL);

		for (ItemCL item : REGISTRY.values()) {
			register(item);
		}

		for (String material : MATERIALS_DICT.keySet()) {
			//			ORE_DICT.put(MATERIALS.getStack(material), MATERIALS_DICT.get(material));
		}

	}

	private static void addMaterial(String name, String... oreDictNames) {
		MATERIALS.types.add(name);
		for (String oreName : oreDictNames)
			MATERIALS_DICT.put(name, oreName);
	}

	private static void register(ItemCL item) {
		GameRegistry.register(item);
	}

}
