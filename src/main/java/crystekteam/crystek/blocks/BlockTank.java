package crystekteam.crystek.blocks;

import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.TileTank;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 01/07/2016.
 */
public class BlockTank extends BlockBase
{
    public BlockTank()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".tank");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!fillBlockWithFluid(world, pos, playerIn, heldItem, side))
        {
            super.onBlockActivated(world, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileTank();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }


    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
}
