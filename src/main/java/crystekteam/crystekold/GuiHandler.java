package crystekteam.crystekold;

import crystekteam.crystekold.client.gui.*;
import crystekteam.crystekold.client.gui.generator.GuiCoalGenerator;
import crystekteam.crystekold.client.gui.generator.GuiFluidGenerator;
import crystekteam.crystekold.client.gui.generator.GuiSolarPanel;
import crystekteam.crystekold.client.gui.machine.GuiCrystallizer;
import crystekteam.crystekold.client.gui.machine.GuiFluidizer;
import crystekteam.crystekold.client.gui.machine.GuiGrinder;
import crystekteam.crystekold.client.gui.machine.GuiPoweredFurnace;
import crystekteam.crystekold.container.*;
import crystekteam.crystekold.tiles.TileCrate;
import crystekteam.crystekold.tiles.TileTeslaCell;
import crystekteam.crystekold.tiles.TileTinkerTable;
import crystekteam.crystekold.tiles.TileTrashCan;
import crystekteam.crystekold.tiles.generator.TileCoalGenerator;
import crystekteam.crystekold.tiles.generator.TileFluidGenerator;
import crystekteam.crystekold.tiles.generator.TileSolarPanel;
import crystekteam.crystekold.tiles.machines.TileCrystallizer;
import crystekteam.crystekold.tiles.machines.TileFluidizer;
import crystekteam.crystekold.tiles.machines.TileFurnace;
import crystekteam.crystekold.tiles.machines.TileGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class GuiHandler implements IGuiHandler
{
    public static int coalGenerator = 0;
    public static int poweredFurnace = 1;
    public static int grinder = 2;
    public static int powerCube = 3;
    public static int solarPanel = 4;
    public static int crystallizer = 5;
    public static int fluidGenerator = 6;
    public static int fluidizer = 7;
    public static int book = 8;
    public static int tinkertable = 9;
    public static int trashcan = 10;
    public static int crate = 11;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == coalGenerator)
        {
            return new ContainerCoalGenerator((TileCoalGenerator) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == poweredFurnace)
        {
            return new ContainerPoweredFurnace((TileFurnace) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == grinder)
        {
            return new ContainerGrinder((TileGrinder) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == powerCube)
        {
            return new ContainerTeslaCell((TileTeslaCell) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == solarPanel)
        {
            return new ContainerSolarPanel((TileSolarPanel) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == crystallizer)
        {
            return new ContainerCrystallizer((TileCrystallizer) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == fluidGenerator)
        {
            return new ContainerFluidGenerator((TileFluidGenerator) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == fluidizer)
        {
            return new ContainerFluidizer((TileFluidizer) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == book)
        {
            return null;
        }
        if (ID == tinkertable)
        {
            return new ContainerTinkerTable((TileTinkerTable) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == trashcan)
        {
            return new ContainerTrashCan((TileTrashCan) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if (ID == crate)
        {
            return new ContainerCrate((TileCrate) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == coalGenerator)
        {
            return new GuiCoalGenerator(player, (TileCoalGenerator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == poweredFurnace)
        {
            return new GuiPoweredFurnace(player, (TileFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == grinder)
        {
            return new GuiGrinder(player, (TileGrinder) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == powerCube)
        {
            return new GuiTeslaCell(player, (TileTeslaCell) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == solarPanel)
        {
            return new GuiSolarPanel(player, (TileSolarPanel) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == crystallizer)
        {
            return new GuiCrystallizer(player, (TileCrystallizer) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == fluidGenerator)
        {
            return new GuiFluidGenerator(player, (TileFluidGenerator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == fluidizer)
        {
            return new GuiFluidizer(player, (TileFluidizer) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == book)
        {
            return new GuiBook(player);
        }
        if (ID == tinkertable)
        {
            return new GuiTinkerTable(player, (TileTinkerTable) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == trashcan)
        {
            return new GuiTrashcan(player, (TileTrashCan) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == crate)
        {
            return new GuiCrate(player, (TileCrate) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
