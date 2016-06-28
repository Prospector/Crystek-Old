package crystekteam.crystek.blocks.machines;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.GuiHandler;
import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileGrinder;
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
 * Created by Gigabit101 on 16/06/2016.
 */
public class BlockGrinder extends BlockBase
{
    public BlockGrinder()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".grinder");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileGrinder();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        playerIn.openGui(Crystek.instance, GuiHandler.grinder, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
