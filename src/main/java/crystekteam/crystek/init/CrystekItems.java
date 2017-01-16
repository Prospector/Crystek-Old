package crystekteam.crystek.init;

import crystekteam.crystek.items.ItemMetadataCrystek;
import crystekteam.crystek.items.misc.ItemCrystallineBonemeal;
import crystekteam.crystek.items.misc.ItemGrindingBlade;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
	public static ItemCrystallineBonemeal CRYSTALLINE_BONEMEAL;
	public static ItemGrindingBlade GOLD_GRINDING_BLADE;
	public static ItemGrindingBlade IRON_GRINDING_BLADE;
	public static ItemGrindingBlade DIAMOND_GRINDING_BLADE;
	public static ItemGrindingBlade OBSIDIAN_GRINDING_BLADE;
	public static ItemGrindingBlade CRYSTALLINE_GRINDING_BLADE;
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
		IRON_GRINDING_BLADE = new ItemGrindingBlade("iron", 2, 63, new ItemStack(Items.IRON_INGOT));
		GOLD_GRINDING_BLADE = new ItemGrindingBlade("gold", 4, 31, new ItemStack(Items.GOLD_INGOT));
		DIAMOND_GRINDING_BLADE = new ItemGrindingBlade("diamond", 3, 1023, new ItemStack(Items.DIAMOND));
		OBSIDIAN_GRINDING_BLADE = new ItemGrindingBlade("obsidian", 1, -1, ItemStack.EMPTY);
		CRYSTALLINE_GRINDING_BLADE = new ItemGrindingBlade("crystalline", 3, -1, ItemStack.EMPTY);

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

		addToRegistry(MATERIALS);
		addToRegistry(CRYSTALLINE_BONEMEAL);
		addToRegistry(IRON_GRINDING_BLADE);
		addToRegistry(GOLD_GRINDING_BLADE);
		addToRegistry(DIAMOND_GRINDING_BLADE);
		addToRegistry(OBSIDIAN_GRINDING_BLADE);
		addToRegistry(CRYSTALLINE_GRINDING_BLADE);

		for (ItemCL item : registry.values()) {
			register(item);
		}

		for (String material : MATERIALS_DICT.keySet()) {
			oreEntries.put(MATERIALS.getStack(material), MATERIALS_DICT.get(material));
		}
	}

	private void addToRegistry(ItemCL item) {
		addToRegistry(item.name.replaceFirst("^crystek:", ""), item);
	}

	private void addToRegistry(String name, ItemCL item) {
		registry.put(name, item);
	}

	private void addToRegistry(ItemCL item, String... oreDictNames) {
		addToRegistry(item.name.replaceFirst("^crystek:", ""), item, oreDictNames);
	}

	private void addToRegistry(String name, ItemCL item, String... oreDictNames) {
		addToRegistry(name, item);
		for (String oreName : oreDictNames)
			MATERIALS_DICT.put(name, oreName);
	}

}
