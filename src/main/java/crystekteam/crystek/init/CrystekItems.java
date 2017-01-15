package crystekteam.crystek.init;

import crystekteam.crystek.items.ItemMetadata;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;

/**
 * Created by Prospector
 */
public class CrystekItems {

	public static HashMap<String, String> MATERIAL_ORES = new HashMap<>();
	public static HashMap<String, Item> REGISTRY = new HashMap<>();
	public static ItemMetadata MATERIALS = new ItemMetadata("material");

	public static void init() {
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

		for (Item item : REGISTRY.values()) {
			register(item);
		}
	}

	private static void addMaterial(String name, String... oreDictNames) {
		MATERIALS.types.add(name);
		for (String oreName : oreDictNames)
			MATERIAL_ORES.put(name, oreName);
	}

	private static void register(Item item) {
		GameRegistry.register(item);
	}

}
