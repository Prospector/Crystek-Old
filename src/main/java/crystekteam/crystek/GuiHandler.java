package crystekteam.crystek;

import crystekteam.crystek.client.gui.*;
import crystekteam.crystek.container.*;
import crystekteam.crystek.tiles.TileTeslaCell;
import crystekteam.crystek.tiles.generator.TileCoalGenerator;
import crystekteam.crystek.tiles.generator.TileFluidGenerator;
import crystekteam.crystek.tiles.generator.TileSolarPanel;
import crystekteam.crystek.tiles.machines.*;
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
    public static int bucketer = 8;
    public static int book = 9;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == coalGenerator)
        {
            return new ContainerCoalGenerator((TileCoalGenerator) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == poweredFurnace)
        {
            return new ContainerPoweredFurnace((TileFurnace) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == grinder)
        {
            return new ContainerGrinder((TileGrinder) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == powerCube)
        {
            return new ContainerTeslaCell((TileTeslaCell) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == solarPanel)
        {
            return new ContainerSolarPanel((TileSolarPanel) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == crystallizer)
        {
            return new ContainerCrystallizer((TileCrystallizer) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == fluidGenerator)
        {
            return new ContainerFluidGenerator((TileFluidGenerator) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == fluidizer)
        {
            return new ContainerFluidizer((TileFluidizer) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == bucketer)
        {
            return new ContainerBucketer((TileBucketer) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        if(ID == book)
        {
            return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == coalGenerator)
        {
            return new GuiCoalGenerator(player, (TileCoalGenerator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == poweredFurnace)
        {
            return new GuiPoweredFurnace(player, (TileFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == grinder)
        {
            return new GuiGrinder(player, (TileGrinder) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == powerCube)
        {
            return new GuiTeslaCell(player, (TileTeslaCell) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == solarPanel)
        {
            return new GuiSolarPanel(player, (TileSolarPanel) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == crystallizer)
        {
            return new GuiCrystallizer(player, (TileCrystallizer) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == fluidGenerator)
        {
            return new GuiFluidGenerator(player, (TileFluidGenerator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == fluidizer)
        {
            return new GuiFluidizer(player, (TileFluidizer) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == bucketer)
        {
            return new GuiBucketer(player, (TileBucketer) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == book)
        {
            return new GuiBook(player);
        }
        return null;
    }
}
