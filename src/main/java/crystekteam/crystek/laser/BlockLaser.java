package crystekteam.crystek.laser;

import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.lib.ModInfo;
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
 * Created by Gigabit101 on 02/06/2016.
 */
public class BlockLaser extends BlockBase
{
    public BlockLaser()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".laser");
        this.setLightLevel(1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileLaser();
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
//        if(worldIn.getTileEntity(pos) instanceof TileLaser && !worldIn.isRemote)
//        {
//            TileLaser tile = (TileLaser) worldIn.getTileEntity(pos);
//            if(tile.getConnection1() != null)
//                playerIn.addChatComponentMessage(new TextComponentString("Connection1 " + tile.getConnection1().toString()));
//            if(tile.getConnection2() != null)
//                playerIn.addChatComponentMessage(new TextComponentString("Connection2 " + tile.getConnection2().toString()));
//        }
        return true;
    }


//    @Override
//    public EnumBlockRenderType getRenderType(IBlockState state) {
//        return EnumBlockRenderType.INVISIBLE;
//    }
//
//
//    @Override
//    public boolean isOpaqueCube(IBlockState state) {
//        return false;
//    }
//
//    public boolean renderAsNormalBlock() {
//        return false;
//    }


}
