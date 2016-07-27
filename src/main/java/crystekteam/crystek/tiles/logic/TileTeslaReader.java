package crystekteam.crystek.tiles.logic;

import crystekteam.crystek.tesla.TeslaUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * Created by Gigabit101 on 08/07/2016.
 */
public class TileTeslaReader extends TileEntity implements ITickable
{
    @Override
    public void update()
    {
        //TODO
    }

    public long getStoredPower(EnumFacing facing)
    {
        TileEntity tileEntity = worldObj.getTileEntity(getPos().offset(facing));
        if(tileEntity != null && TeslaUtils.isTelsaBlock(tileEntity))
        {
            return TeslaUtils.getStoredPower(tileEntity);
        }
        return 0;
    }

    public long getMaxPower(EnumFacing facing)
    {
        TileEntity tileEntity = worldObj.getTileEntity(getPos().offset(facing));
        if(tileEntity != null && TeslaUtils.isTelsaBlock(tileEntity))
        {
            return TeslaUtils.getMaxCapacity(tileEntity);
        }
        return 0;
    }
}
