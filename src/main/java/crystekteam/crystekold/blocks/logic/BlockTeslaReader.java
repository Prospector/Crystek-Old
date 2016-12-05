package crystekteam.crystekold.blocks.logic;

import crystekteam.crystekold.blocks.BlockBase;
import crystekteam.crystekold.lib.ModInfo;
import crystekteam.crystekold.tiles.logic.TileTeslaReader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 08/07/2016.
 */
public class BlockTeslaReader extends BlockBase
{
    public BlockTeslaReader()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + "teslareader");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileTeslaReader();
    }
}
