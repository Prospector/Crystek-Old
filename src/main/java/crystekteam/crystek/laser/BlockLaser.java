package crystekteam.crystek.laser;

import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
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
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    //TODO remove debug
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileLaser tileLaser = (TileLaser) world.getTileEntity(pos);
        if (heldItem == null)
        {
            if (!world.isRemote && tileLaser.getConnectedLaser() != null)
            {
                playerIn.addChatComponentMessage(new TextComponentString("Has Connected Laser" + " " + tileLaser.getConnectedLaser().getPos()));
            }
            if (!world.isRemote && tileLaser.getConnectedMachine() != null)
            {
                playerIn.addChatComponentMessage(new TextComponentString("Has Connected Machine"));
            }
            return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileLaser();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

}
