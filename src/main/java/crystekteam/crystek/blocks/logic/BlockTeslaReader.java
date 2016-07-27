package crystekteam.crystek.blocks.logic;

import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.blocks.BlockTrashCan;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.logic.TileTeslaReader;
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
