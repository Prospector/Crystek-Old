package crystekteam.crystekold.blocks.machines;

import crystekteam.crystekold.blocks.BlockBase;
import crystekteam.crystekold.lib.ModInfo;
import crystekteam.crystekold.tiles.machines.TileAccelerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 12/07/2016.
 */
public class BlockAccelerator extends BlockBase
{
    public BlockAccelerator()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".accelerator");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileAccelerator();
    }
}
