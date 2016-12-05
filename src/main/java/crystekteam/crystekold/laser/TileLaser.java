package crystekteam.crystekold.laser;

import crystekteam.crystekold.tesla.TeslaUtils;
import crystekteam.crystekold.tiles.prefab.TileTeslaStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class TileLaser extends TileTeslaStorage
{
    TileEntity connectedMachine;
    TileLaser connectedLaser;

    public TileLaser()
    {
        super(0, "", 0, 10000, 1000, 1000, 0, 0);
    }

    @Override
    public void update()
    {
        //Connect to machine under laser
        if (getConnectedMachine() == null)
        {
            setConnectedMachine();
        }

        if (getConnectedLaser() != null)
        {
            //request power from connected coil
            requestPower();
            //transfers power to connected machine
            transferPowerTo(EnumFacing.DOWN);
        }
    }

    public void setConnectedMachine()
    {
        BlockPos tilePos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        if (worldObj.getTileEntity(tilePos) != null && TeslaUtils.isTelsaBlock(worldObj.getTileEntity(tilePos)))
        {
            connectedMachine = worldObj.getTileEntity(pos);
        } else
        {
            connectedMachine = null;
        }
    }

    public TileLaser getConnectedLaser()
    {
        return connectedLaser;
    }

    public TileEntity getConnectedMachine()
    {
        return connectedMachine;
    }

    public void setConnectedLaser(BlockPos pos)
    {
        if (worldObj.getTileEntity(pos) != null && worldObj.getTileEntity(pos) instanceof TileLaser)
        {
            connectedLaser = (TileLaser) worldObj.getTileEntity(pos);
        }
    }

    public void requestPower()
    {
        if (getConnectedMachine() != null && TeslaUtils.isConsumer(getConnectedMachine()))
        {
            if (TeslaUtils.getMissingPower(worldObj.getTileEntity(pos)) >= container.getInputRate())
            {

                if (TeslaUtils.getStoredPower(connectedLaser) >= container.getInputRate())
                {
                    TeslaUtils.removePower(getConnectedLaser(), container.getInputRate());
                    TeslaUtils.addPower(worldObj.getTileEntity(pos), container.getInputRate());
                }
            }
        }
    }


    public void transferPowerTo(EnumFacing facing)
    {
        TileEntity tileEntity = worldObj.getTileEntity(getPos().offset(facing));
        if (tileEntity != null && TeslaUtils.isTelsaBlock(tileEntity) && TeslaUtils.isConsumer(tileEntity))
        {
            if (TeslaUtils.canAcceptPower(tileEntity, this.container.getOutputRate()) && this.container.getStoredPower() >= this.container.getOutputRate())
            {
                TeslaUtils.addPower(tileEntity, this.container.getOutputRate());
                this.container.takePower(this.container.getOutputRate(), false);
            } else if (TeslaUtils.canAcceptPower(tileEntity, this.container.getOutputRate()) && this.container.getStoredPower() < this.container.getOutputRate())
            {
                TeslaUtils.addPower(tileEntity, this.container.getStoredPower());
                this.container.takePower(this.container.getStoredPower(), false);
            }
        }
    }
}
