package crystekteam.crystek.init;

import crystekteam.crystek.items.misc.ItemCrafting;
import crystekteam.crystek.items.misc.ItemGrindingBlade;
import crystekteam.crystek.items.powered.ItemBattery;
import crystekteam.crystek.items.tools.ItemCircuit;
import crystekteam.crystek.items.tools.ItemLinkDevice;
import crystekteam.crystek.items.tools.ItemPowerScanner;
import crystekteam.crystek.items.tools.ItemWelder;
import crystekteam.crystek.items.tools.tesla.ItemDrill;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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

        ironGrindingBlade = new ItemGrindingBlade("grindingbladeIron", 10, 100, new ItemStack(Items.IRON_INGOT));
        GameRegistry.register(ironGrindingBlade);

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
