package crystekteam.crystek.core;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 28/07/2016.
 */
public class RedStoneControl
{
    TileBase tileBase;
    World world;

    public RedStoneControl(TileBase tileBase)
    {
        this.tileBase = tileBase;
        this.world = tileBase.getWorld();
    }

    public boolean canRun()
    {
        if(tileBase != null)
        {
            if(world.getBlockState(tileBase.getPos()).getStrongPower(world, tileBase.getPos(), EnumFacing.DOWN) != 0)
            {
                return false;
            }
        }
        return true;
    }
}
