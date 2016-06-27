package crystekteam.crystek;

import crystekteam.crystek.client.gui.*;
import crystekteam.crystek.container.*;
import crystekteam.crystek.tiles.generator.TileFluidGenerator;
import crystekteam.crystek.tiles.generator.TileSolarPanel;
import crystekteam.crystek.tiles.machines.TileFurnace;
import crystekteam.crystek.tiles.TileTeslaCell;
import crystekteam.crystek.tiles.generator.TileCoalGenerator;
import crystekteam.crystek.tiles.machines.TileCrystallizer;
import crystekteam.crystek.tiles.machines.TileGrinder;
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
    public static int oreSmasher = 2;
    public static int powerCube = 3;
    public static int solarPanel = 4;
    public static int crystallizer = 5;
    public static int fluidGenerator = 6;

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
        if(ID == oreSmasher)
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
        if(ID == oreSmasher)
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
        return null;
    }
}
