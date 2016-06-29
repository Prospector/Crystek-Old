package crystekteam.crystek.init;

import crystekteam.crystek.blocks.BlockMachineFrame;
import crystekteam.crystek.blocks.BlockTeslaCell;
import crystekteam.crystek.blocks.generator.BlockCoalGenerator;
import crystekteam.crystek.blocks.generator.BlockFluidGenerator;
import crystekteam.crystek.blocks.generator.BlockSolarPanel;
import crystekteam.crystek.blocks.machines.*;
import crystekteam.crystek.laser.BlockLaser;
import crystekteam.crystek.laser.TileLaser;
import crystekteam.crystek.tiles.TileTeslaCell;
import crystekteam.crystek.tiles.generator.TileCoalGenerator;
import crystekteam.crystek.tiles.generator.TileFluidGenerator;
import crystekteam.crystek.tiles.generator.TileSolarPanel;
import crystekteam.crystek.tiles.machines.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.InvocationTargetException;

public class ModBlocks
{
    public static Block machineframe;
    //Decor
    public static Block lamp;
    //Machines
    public static Block poweredFurnace;
    public static Block grinder;
    public static Block crystallizer;
    public static Block fluidizer;
    public static Block bucketer;

    //Power Storage
    public static Block teslaCell;
    //Power gen
    public static Block coalGen;
    public static Block solarGen;
    public static Block fluidGen;

    public static Block laser;

    public static void init()
    {
        machineframe = new BlockMachineFrame();
        registerBlock(machineframe, "machineframe");

        coalGen = new BlockCoalGenerator();
        registerBlock(coalGen, "Coalgenerator");
        GameRegistry.registerTileEntity(TileCoalGenerator.class, "coalgentile");

        teslaCell = new BlockTeslaCell();
        registerBlock(teslaCell, "teslacell");
        GameRegistry.registerTileEntity(TileTeslaCell.class, "teslacell");

        poweredFurnace = new BlockFurnace();
        registerBlock(poweredFurnace, "poweredfurnace");
        GameRegistry.registerTileEntity(TileFurnace.class, "poweredfurnace");

        laser = new BlockLaser();
        registerBlock(laser, "laser");
        GameRegistry.registerTileEntity(TileLaser.class, "lasertile");

        grinder = new BlockGrinder();
        registerBlock(grinder, "grinder");
        GameRegistry.registerTileEntity(TileGrinder.class, "oresmasher");

        solarGen = new BlockSolarPanel();
        registerBlock(solarGen, "solarpanel");
        GameRegistry.registerTileEntity(TileSolarPanel.class, "solarpanel");

        crystallizer = new BlockCrystallizer();
        registerBlock(crystallizer, "crystallizer");
        GameRegistry.registerTileEntity(TileCrystallizer.class, "crystallizer");

        fluidGen = new BlockFluidGenerator();
        registerBlock(fluidGen, "fluidgen");
        GameRegistry.registerTileEntity(TileFluidGenerator.class, "fluidgen");

        fluidizer = new BlockFluidizer();
        registerBlock(fluidizer, "fluidizer");
        GameRegistry.registerTileEntity(TileFluidizer.class, "fluidizer");

        bucketer = new BlockBucketer();
        registerBlock(bucketer, "bucketer");
        GameRegistry.registerTileEntity(TileBucketer.class, "bucketer");
    }

    static void registerOreDictValues(Block item, int meta, String value)
    {
        OreDictionary.registerOre(value, new ItemStack(item, 1, meta));
    }


    public static void registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name)
    {
        block.setRegistryName(name);
        GameRegistry.register(block);
        try
        {
            ItemBlock itemBlock = itemclass.getConstructor(Block.class).newInstance(block);
            itemBlock.setRegistryName(name);
            GameRegistry.register(itemBlock);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
