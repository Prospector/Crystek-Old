package crystekteam.crystek.init;

import crystekteam.crystek.items.ItemMetadataCrystek;
import crystekteam.crystek.items.misc.ItemCrystallineBonemeal;
import net.minecraftforge.fml.common.registry.GameRegistry;
import reborncore.modcl.ItemCL;
import reborncore.modcl.ItemMetadataCL;
import reborncore.modcl.ModCL;
import reborncore.modcl.RegistryCL;

import java.util.HashMap;

/**
 * Created by Prospector
 */
public class CrystekItems extends RegistryCL {

	public static ItemMetadataCL MATERIALS;
	public static ItemCL CRYSTALLINE_BONEMEAL;
	private static HashMap<String, String> MATERIALS_DICT = new HashMap<>();

	private static void addMaterial(String name, String... oreDictNames) {
		MATERIALS.types.add(name);
		for (String oreName : oreDictNames)
			MATERIALS_DICT.put(name, oreName);
	}

	private static void register(ItemCL item) {
		GameRegistry.register(item);
	}

	public void init(ModCL mod) {
		MATERIALS = new ItemMetadataCrystek("material");
		CRYSTALLINE_BONEMEAL = new ItemCrystallineBonemeal();

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

		registry.put("material", MATERIALS);
		registry.put("crystalline_bonemeal", CRYSTALLINE_BONEMEAL);

		for (ItemCL item : registry.values()) {
			register(item);
		}

		for (String material : MATERIALS_DICT.keySet()) {
			oreEntries.put(MATERIALS.getStack(material), MATERIALS_DICT.get(material));
		}

	}

}
