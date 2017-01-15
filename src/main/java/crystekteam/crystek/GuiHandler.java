package crystekteam.crystek;

import crystekteam.crystek.tiles.TileCrystek;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof TileMachine)
        {
            System.out.print("nfasfoaks");
            TileMachine tileCrystek = (TileMachine) world.getTileEntity(new BlockPos(x, y, z));
            if(tileCrystek.getMachine().getGuiID() == ID)
            {
                return tileCrystek.getMachine().getContainer();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(world.getTileEntity(new BlockPos(x, y, z)) != null && world.getTileEntity(new BlockPos(x, y, z)) instanceof TileMachine)
        {
            System.out.print("nfasfoaks");
            TileMachine tileCrystek = (TileMachine) world.getTileEntity(new BlockPos(x, y, z));
            if(tileCrystek.getMachine().getGuiID() == ID)
            {
                return tileCrystek.getMachine().getGuiContainer();
            }
        }
        return null;
    }
}
