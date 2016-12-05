package crystekteam.crystekold.blocks.machines;

import crystekteam.crystekold.Crystek;
import crystekteam.crystekold.GuiHandler;
import crystekteam.crystekold.blocks.BlockBase;
import crystekteam.crystekold.lib.ModInfo;
import crystekteam.crystekold.tiles.machines.TileFluidizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class BlockFluidizer extends BlockBase
{
    public BlockFluidizer()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".fluidizer");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileFluidizer();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.isSneaking() && !fillBlockWithFluid(world, pos, playerIn, heldItem, side))
        {
            super.onBlockActivated(world, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
            playerIn.openGui(Crystek.instance, GuiHandler.fluidizer, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
