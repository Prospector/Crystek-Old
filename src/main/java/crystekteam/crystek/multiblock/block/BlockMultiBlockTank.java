package crystekteam.crystek.multiblock.block;

import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.multiblock.tile.TileMultiBlockTank;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 25/07/2016.
 */
public class BlockMultiBlockTank extends BlockBase
{
    public BlockMultiBlockTank()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + "multiblocktank");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileMultiBlockTank();
    }
}
