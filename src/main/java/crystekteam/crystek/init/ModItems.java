package crystekteam.crystek.init;

import crystekteam.crystek.items.armour.ItemPowerArmour;
import crystekteam.crystek.items.misc.ItemCrafting;
import crystekteam.crystek.items.misc.ItemGrindingBlade;
import crystekteam.crystek.items.powered.ItemBattery;
import crystekteam.crystek.items.tools.ItemCircuit;
import crystekteam.crystek.items.tools.ItemLinkDevice;
import crystekteam.crystek.items.tools.ItemPowerScanner;
import crystekteam.crystek.items.tools.ItemWelder;
import crystekteam.crystek.items.tools.tesla.ItemDrill;
import crystekteam.crystek.util.ItemBank;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems
{
	public static Item welder;
	public static Item powerScanner;
	public static Item wrench;
	public static Item laserLinkingDevice;
	public static Item crafting;
	public static Item circuit;
	public static Item battery;
	public static Item drill;
	public static ItemGrindingBlade ironGrindingBlade;
	public static ItemGrindingBlade goldGrindingBlade;
	public static ItemGrindingBlade diamondGrindingBlade;
	public static ItemGrindingBlade obsidianGrindingBlade;
	public static ItemGrindingBlade teslaAlloyGrindingBlade;

	//armor
	public static Item powerArmourHelmet;
	public static Item powerArmourChestplate;
	public static Item powerArmourLeggings;
	public static Item powerArmourBoots;

	public static void init()
	{
		welder = new ItemWelder();
		GameRegistry.register(welder);

		powerScanner = new ItemPowerScanner();
		GameRegistry.register(powerScanner);

		laserLinkingDevice = new ItemLinkDevice();
		GameRegistry.register(laserLinkingDevice);

		crafting = new ItemCrafting();
		GameRegistry.register(crafting);

		circuit = new ItemCircuit();
		GameRegistry.register(circuit);

		battery = new ItemBattery();
		GameRegistry.register(battery);

		drill = new ItemDrill();
		GameRegistry.register(drill);

		ironGrindingBlade = new ItemGrindingBlade("grindingbladeIron", 2, 63, new ItemStack(Items.IRON_INGOT));
		GameRegistry.register(ironGrindingBlade);

		goldGrindingBlade = new ItemGrindingBlade("grindingbladeGold", 4, 31, new ItemStack(Items.GOLD_INGOT));
		GameRegistry.register(goldGrindingBlade);

		diamondGrindingBlade = new ItemGrindingBlade("grindingbladeDiamond", 3, 1023, new ItemStack(Items.DIAMOND));
		GameRegistry.register(diamondGrindingBlade);

		obsidianGrindingBlade = new ItemGrindingBlade("grindingbladeObsidian", 1, -1, new ItemStack(Blocks.OBSIDIAN));
		GameRegistry.register(obsidianGrindingBlade);

		teslaAlloyGrindingBlade = new ItemGrindingBlade("grindingbladeTeslaAlloy", 3, -1, ItemBank.teslaIngot);
		GameRegistry.register(teslaAlloyGrindingBlade);

		powerArmourHelmet = new ItemPowerArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.HEAD, 1000, 200, 200)
				.setRegistryName("powerarmour.helmet");
		GameRegistry.register(powerArmourHelmet);

		powerArmourChestplate = new ItemPowerArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.CHEST, 1000, 200, 200)
				.setRegistryName("powerarmour.chestplate");
		GameRegistry.register(powerArmourChestplate);

		powerArmourLeggings = new ItemPowerArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.LEGS, 1000, 200, 200)
				.setRegistryName("powerarmour.leggings");
		GameRegistry.register(powerArmourLeggings);

		powerArmourBoots = new ItemPowerArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.FEET, 1000, 200, 200)
				.setRegistryName("powerarmour.boots");
		GameRegistry.register(powerArmourBoots);

		int i;
		//Register ore dict values for all crafting items
		for (i = 0; i < ItemCrafting.types.length; ++i)
		{
			String[] name = ItemCrafting.types.clone();
			registerOreDictValues(crafting, i, name[i]);
		}
	}

	static void registerOreDictValues(Item item, int meta, String value)
	{
		OreDictionary.registerOre(value, new ItemStack(item, 1, meta));
	}
}
