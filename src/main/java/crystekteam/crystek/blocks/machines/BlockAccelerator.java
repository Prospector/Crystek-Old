package crystekteam.crystek.blocks.machines;

import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileAccelerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 12/07/2016.
 */
public class BlockAccelerator extends BlockBase
{
    public BlockAccelerator()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase()  + ".accelerator");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileAccelerator();
    }
}
