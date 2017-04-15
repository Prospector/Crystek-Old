package crystekteam.crystek.init;

import crystekteam.crystek.core.ItemMachineDebug;
import crystekteam.crystek.items.ItemMetadataCrystek;
import crystekteam.crystek.items.misc.ItemCrystallineBonemeal;
import crystekteam.crystek.items.misc.ItemGrindingBlade;
import crystekteam.crystek.items.misc.ItemObsidianWrench;
import crystekteam.crystek.items.tesla.ItemCreativeBattery;
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
	public static ItemCL CRYSTALLINE_BONEMEAL;
	public static ItemCL GOLD_GRINDING_BLADE;
	public static ItemCL IRON_GRINDING_BLADE;
	public static ItemCL DIAMOND_GRINDING_BLADE;
	public static ItemCL OBSIDIAN_GRINDING_BLADE;
	public static ItemCL CRYSTALLINE_GRINDING_BLADE;
	public static ItemCL OBSIDIAN_WRENCH;
	public static ItemCL MACHINE_DEBUG;
	public static ItemCL CREATIVE_BATTERY;
	private static HashMap<String, String> MATERIALS_DICT = new HashMap<>();

	private static void addMaterial(String name, String... oreDictNames) {
		MATERIALS.types.add(name);
		for (String oreName : oreDictNames)
			MATERIALS_DICT.put(name, oreName);
	}

	public void init(ModCL mod) {
		OBSIDIAN_WRENCH = new ItemObsidianWrench();
		MATERIALS = new ItemMetadataCrystek("material");
		CRYSTALLINE_BONEMEAL = new ItemCrystallineBonemeal();
		IRON_GRINDING_BLADE = new ItemGrindingBlade("iron", 2, 63, new ItemStack(Items.IRON_INGOT));
		GOLD_GRINDING_BLADE = new ItemGrindingBlade("gold", 4, 31, new ItemStack(Items.GOLD_INGOT));
		DIAMOND_GRINDING_BLADE = new ItemGrindingBlade("diamond", 3, 1023, new ItemStack(Items.DIAMOND));
		OBSIDIAN_GRINDING_BLADE = new ItemGrindingBlade("obsidian", 1, -1, ItemStack.EMPTY);
		CRYSTALLINE_GRINDING_BLADE = new ItemGrindingBlade("crystalline", 3, -1, ItemStack.EMPTY);
		MACHINE_DEBUG = new ItemMachineDebug();
		CREATIVE_BATTERY = new ItemCreativeBattery();

		addMaterial("crystal", "crystalTesla");
		addMaterial("blue_crystal", "crystalTesla", "crystalTeslaBlue");
		addMaterial("purple_crystal", "crystalTesla", "crystalTeslaPurple");
		addMaterial("red_crystal", "crystalTesla", "crystalTeslaRed");
		addMaterial("yellow_crystal", "crystalTesla", "crystalTeslaYellow");
		addMaterial("green_crystal", "crystalTesla", "crystalTeslaGreen");
		addMaterial("experience_crystal", "crystalExperience");

		addMaterial("crystal_dust", "dustCrystalTesla");
		addMaterial("blue_crystal_dust", "dustCrystalTesla", "dustCrystalTeslaBlue");
		addMaterial("purple_crystal_dust", "dustCrystalTesla", "dustCrystalTeslaPurple");
		addMaterial("red_crystal_dust", "dustCrystalTesla", "dustCrystalTeslaRed");
		addMaterial("yellow_crystal_dust", "dustCrystalTesla", "dustCrystalTeslaYellow");
		addMaterial("green_crystal_dust", "dustCrystalTesla", "dustCrystalTeslaGreen");
		addMaterial("iron_dust", "dustIron");
		addMaterial("gold_dust", "dustGold");
		addMaterial("diamond_dust", "dustDiamond");
		addMaterial("emerald_dust", "dustEmerald");
		addMaterial("obsidian_dust", "dustObsidian");
		addMaterial("rubrium_dust", "dustRubrium");

		addMaterial("crystalline_iron_ingot", "ingotCrystallineIron");
		addMaterial("obsidian_ingot", "ingotObsidian");
		addMaterial("rubrium_ingot", "ingotRubrium");

		addMaterial("tesla_lens", "lensTesla");

		addToRegistry(OBSIDIAN_WRENCH);
		addToRegistry(MATERIALS);
		addToRegistry(CRYSTALLINE_BONEMEAL);
		addToRegistry(IRON_GRINDING_BLADE);
		addToRegistry(GOLD_GRINDING_BLADE);
		addToRegistry(DIAMOND_GRINDING_BLADE);
		addToRegistry(OBSIDIAN_GRINDING_BLADE);
		addToRegistry(CRYSTALLINE_GRINDING_BLADE);
		addToRegistry(MACHINE_DEBUG);
		addToRegistry(CREATIVE_BATTERY);

		for (ItemCL item : itemRegistry.values()) {
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
		itemRegistry.put(name, item);
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
