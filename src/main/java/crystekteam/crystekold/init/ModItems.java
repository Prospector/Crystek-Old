package crystekteam.crystekold.init;

import crystekteam.crystekold.items.ItemBook;
import crystekteam.crystekold.items.armour.ItemInfusedArmour;
import crystekteam.crystekold.items.armour.ItemJetArmour;
import crystekteam.crystekold.items.misc.ItemCrafting;
import crystekteam.crystekold.items.misc.ItemGrindingBlade;
import crystekteam.crystekold.items.misc.ItemTeslaBonemeal;
import crystekteam.crystekold.items.powered.ItemBattery;
import crystekteam.crystekold.items.tools.ItemCircuit;
import crystekteam.crystekold.items.tools.ItemPowerScanner;
import crystekteam.crystekold.items.tools.ItemWrench;
import crystekteam.crystekold.items.tools.tesla.ItemDrill;
import crystekteam.crystekold.util.ItemBank;
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
    public static Item powerScanner;
    public static Item crafting;
    public static Item circuit;
    public static Item battery;
    public static Item drill;
    public static Item wrench;
    public static Item book;
    public static Item teslabonemeal;
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

    public static Item jetSuitHelmet;
    public static Item jetSuitChestplate;
    public static Item jetSuitLeggings;
    public static Item jetSuitBoots;

    public static void init()
    {

        powerScanner = new ItemPowerScanner();
        GameRegistry.register(powerScanner);

        crafting = new ItemCrafting();
        GameRegistry.register(crafting);

        circuit = new ItemCircuit();
        GameRegistry.register(circuit);

        battery = new ItemBattery();
        GameRegistry.register(battery);

        drill = new ItemDrill();
        GameRegistry.register(drill);

        wrench = new ItemWrench();
        GameRegistry.register(wrench);

        teslabonemeal = new ItemTeslaBonemeal();
        GameRegistry.register(teslabonemeal);

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

        powerArmourHelmet = new ItemInfusedArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.HEAD, 1000, 200,
                200).setRegistryName("infusedarmour.helmet");
        GameRegistry.register(powerArmourHelmet);

        powerArmourChestplate = new ItemInfusedArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.CHEST, 1000,
                200, 200).setRegistryName("infusedarmour.chestplate");
        GameRegistry.register(powerArmourChestplate);

        powerArmourLeggings = new ItemInfusedArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.LEGS, 1000,
                200, 200).setRegistryName("infusedarmour.leggings");
        GameRegistry.register(powerArmourLeggings);

        powerArmourBoots = new ItemInfusedArmour(ItemArmor.ArmorMaterial.DIAMOND, EntityEquipmentSlot.FEET, 1000, 200,
                200).setRegistryName("infusedarmour.boots");
        GameRegistry.register(powerArmourBoots);

        jetSuitHelmet = new ItemJetArmour(ItemArmor.ArmorMaterial.CHAIN, EntityEquipmentSlot.HEAD, 5000, 200, 200)
                .setRegistryName("jetsuit.helmet");
        GameRegistry.register(jetSuitHelmet);

        jetSuitChestplate = new ItemJetArmour(ItemArmor.ArmorMaterial.CHAIN, EntityEquipmentSlot.CHEST, 5000, 200, 200)
                .setRegistryName("jetsuit.chestplate");
        GameRegistry.register(jetSuitChestplate);

        jetSuitLeggings = new ItemJetArmour(ItemArmor.ArmorMaterial.CHAIN, EntityEquipmentSlot.LEGS, 5000, 200, 200)
                .setRegistryName("jetsuit.leggings");
        GameRegistry.register(jetSuitLeggings);

        jetSuitBoots = new ItemJetArmour(ItemArmor.ArmorMaterial.CHAIN, EntityEquipmentSlot.FEET, 5000, 200, 200)
                .setRegistryName("jetsuit.boots");
        GameRegistry.register(jetSuitBoots);

        book = new ItemBook();
        GameRegistry.register(book);

        OreDictionary.registerOre("dyeCyan", teslabonemeal);
        OreDictionary.registerOre("cropBeetroot", Items.BEETROOT);

        OreDictionary.registerOre("crystalTesla", ItemBank.teslaCrystal);
        OreDictionary.registerOre("crystalTesla", ItemBank.teslaCrystalRed);
        OreDictionary.registerOre("crystalTesla", ItemBank.teslaCrystalYellow);
        OreDictionary.registerOre("crystalTesla", ItemBank.teslaCrystalPurple);
        OreDictionary.registerOre("crystalTesla", ItemBank.teslaCrystalBlue);

        OreDictionary.registerOre("crystekGrindingBlade", ironGrindingBlade);
        OreDictionary.registerOre("crystekGrindingBlade", goldGrindingBlade);
        OreDictionary.registerOre("crystekGrindingBlade", diamondGrindingBlade);
        OreDictionary.registerOre("crystekGrindingBlade", obsidianGrindingBlade);
        OreDictionary.registerOre("crystekGrindingBlade", teslaAlloyGrindingBlade);

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
