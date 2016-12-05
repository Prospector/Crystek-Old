package crystekteam.crystekold.init;

import crystekteam.crystekold.blocks.*;
import crystekteam.crystekold.blocks.decor.BlockLamp;
import crystekteam.crystekold.blocks.generator.BlockCoalGenerator;
import crystekteam.crystekold.blocks.generator.BlockFluidGenerator;
import crystekteam.crystekold.blocks.generator.BlockSolarPanel;
import crystekteam.crystekold.blocks.logic.BlockTeslaReader;
import crystekteam.crystekold.blocks.machines.*;
import crystekteam.crystekold.items.Itemblocks.ItemBlockMachine;
import crystekteam.crystekold.laser.BlockLaser;
import crystekteam.crystekold.laser.TileLaser;
import crystekteam.crystekold.multiblock.block.BlockMultiBlockTank;
import crystekteam.crystekold.multiblock.tile.TileMultiBlockTank;
import crystekteam.crystekold.tiles.*;
import crystekteam.crystekold.tiles.generator.TileCoalGenerator;
import crystekteam.crystekold.tiles.generator.TileFluidGenerator;
import crystekteam.crystekold.tiles.generator.TileSolarPanel;
import crystekteam.crystekold.tiles.logic.TileTeslaReader;
import crystekteam.crystekold.tiles.machines.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.InvocationTargetException;

public class ModBlocks
{
    public static Block tank;
    public static Block tinkerTable;
    //Decor
    public static Block lamp;
    //Machines
    public static Block poweredFurnace;
    public static Block grinder;
    public static Block crystallizer;
    public static Block fluidizer;
    public static Block accelerator;
    //Logic
    public static Block teslaReader;

    //Power Storage
    public static Block teslaCell;
    //Power gen
    public static Block coalGen;
    public static Block solarGen;
    public static Block fluidGen;

    public static Block laser;
    public static Block trashCan;
    public static Block crate;

    //multiblock blocks
    public static Block multiblocktank;

    public static void init()
    {
        coalGen = new BlockCoalGenerator();
        registerBlock(coalGen, ItemBlockMachine.class, "coalgenerator");
        GameRegistry.registerTileEntity(TileCoalGenerator.class, "coalgenerator");

        teslaCell = new BlockTeslaCell();
        registerBlock(teslaCell, ItemBlockMachine.class, "teslacell");
        GameRegistry.registerTileEntity(TileTeslaCell.class, "teslacell");

        poweredFurnace = new BlockFurnace();
        registerBlock(poweredFurnace, ItemBlockMachine.class, "poweredfurnace");
        GameRegistry.registerTileEntity(TileFurnace.class, "poweredfurnace");

        laser = new BlockLaser();
        registerBlock(laser, "laser");
        GameRegistry.registerTileEntity(TileLaser.class, "lasertile");

        grinder = new BlockGrinder();
        registerBlock(grinder, ItemBlockMachine.class, "grinder");
        GameRegistry.registerTileEntity(TileGrinder.class, "oresmasher");

        solarGen = new BlockSolarPanel();
        registerBlock(solarGen, ItemBlockMachine.class, "solarpanel");
        GameRegistry.registerTileEntity(TileSolarPanel.class, "solarpanel");

        crystallizer = new BlockCrystallizer();
        registerBlock(crystallizer, ItemBlockMachine.class, "crystallizer");
        GameRegistry.registerTileEntity(TileCrystallizer.class, "crystallizer");

        fluidGen = new BlockFluidGenerator();
        registerBlock(fluidGen, ItemBlockMachine.class, "fluidgen");
        GameRegistry.registerTileEntity(TileFluidGenerator.class, "fluidgen");

        fluidizer = new BlockFluidizer();
        registerBlock(fluidizer, ItemBlockMachine.class, "fluidizer");
        GameRegistry.registerTileEntity(TileFluidizer.class, "fluidizer");

        tank = new BlockTank();
        registerBlock(tank, ItemBlockMachine.class, "tank");
        GameRegistry.registerTileEntity(TileTank.class, "tank");

        teslaReader = new BlockTeslaReader();
        registerBlock(teslaReader, "teslareader");
        GameRegistry.registerTileEntity(TileTeslaReader.class, "teslareader");

        accelerator = new BlockAccelerator();
        registerBlock(accelerator, ItemBlockMachine.class, "accelerator");
        GameRegistry.registerTileEntity(TileAccelerator.class, "accelerator");

        tinkerTable = new BlockTinkerTable();
        registerBlock(tinkerTable, ItemBlockMachine.class, "tinkertable");
        GameRegistry.registerTileEntity(TileTinkerTable.class, "tinkertable");

        multiblocktank = new BlockMultiBlockTank();
        registerBlock(multiblocktank, "multiblocktank");
        GameRegistry.registerTileEntity(TileMultiBlockTank.class, "multiblocktank");

        lamp = new BlockLamp();
        registerBlock(lamp, "lamp");

        trashCan = new BlockTrashCan();
        registerBlock(trashCan, "trashcan");
        GameRegistry.registerTileEntity(TileTrashCan.class, "trashcan");

        crate = new BlockCrate();
        registerBlock(crate, ItemBlockMachine.class, "crate");
        GameRegistry.registerTileEntity(TileCrate.class, "crate");
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
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
