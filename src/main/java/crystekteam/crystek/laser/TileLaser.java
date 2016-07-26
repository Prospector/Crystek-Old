package crystekteam.crystek.laser;

import crystekteam.crystek.init.ModBlocks;
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
    public static final float[] COLORS = new float[]{93F/255F, 43F/255F, 181F/955F};

    public TileLaser()
    {
        super(0, "", 0, 1000, 1000, 1000, 0, 0);
    }

    //Todo rewrite all of this
    @Override
    public void update()
    {
//        if(!hasConnectedTile())
//        {
////            findConnections();
//        }
//        if(hasConnectedTile() && getStoredPower() != 1000)
//        {
//            requestPower(1000);
//        }
//
        int radius = 10;
//
//        //Todo remove
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos pos = getPos().add(x, y, z);
                    if(!pos.equals(getPos())){
                        if(worldObj.getBlockState(pos).getBlock() == ModBlocks.poweredFurnace) {
                            if(connectedTile == null)
                                connectedTile = worldObj.getTileEntity(pos);
                        }
                    }

                }
            }
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
