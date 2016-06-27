package crystekteam.crystek.laser;

import crystekteam.crystek.tiles.prefab.TileBase;
import crystekteam.crystek.tiles.prefab.TileTeslaStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class TileLaser extends TileTeslaStorage
{
    BlockPos connectedPos;
    TileEntity connectedTile;

    public TileLaser()
    {
        super(0, "", 0, 1000, 1000, 1000, 0);
    }

    @Override
    public void update()
    {
        if(!hasConnectedTile())
        {
            findConnections();
        }
        if(hasConnectedTile() && getStoredPower() != 1000)
        {
            requestPower(1000);
        }
    }

    public void findConnections()
    {
        for (final EnumFacing side : EnumFacing.values())
        {
            int i;
            for (i = 0; i < 15; ++i)
            {
                final TileEntity tile = worldObj.getTileEntity(pos.offset(side).add(i, i, i));
                if (tile != null && !tile.isInvalid() && tile instanceof TileBase)
                {
                    setConnectedTile(tile);
                    break;
                }
            }
        }
    }

    public boolean hasConnectedTile()
    {
        if(getConnectedTile() != null)
        {
            return true;
        }
        return false;
    }

    public BlockPos getConnectedPos()
    {
        return connectedPos;
    }

    public TileEntity getConnectedTile()
    {
        return connectedTile;
    }

    public void setConnectedTile(TileEntity tile)
    {
        connectedTile = tile;
    }

    public void removeConnection()
    {
        this.connectedTile = null;
    }

    public void requestPower(long amount)
    {
        if(connectedTile != null)
        {
            if (connectedTile instanceof TileBase)
            {
                TileBase tile = (TileBase) connectedTile;
                if (tile.getStoredPower() >= amount)
                {
                    tile.usePower(amount);
                    generatePower(amount);
                }
            }
        }
        if(worldObj.getTileEntity(connectedTile.getPos()) == null)
        {
            removeConnection();
        }
    }
}
