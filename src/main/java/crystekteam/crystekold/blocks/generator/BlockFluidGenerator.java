package crystekteam.crystekold.blocks.generator;

import crystekteam.crystekold.Crystek;
import crystekteam.crystekold.GuiHandler;
import crystekteam.crystekold.blocks.BlockBase;
import crystekteam.crystekold.lib.ModInfo;
import crystekteam.crystekold.tiles.generator.TileFluidGenerator;
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
public class BlockFluidGenerator extends BlockBase
{
    public BlockFluidGenerator()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".fluidgenerator");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileFluidGenerator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!fillBlockWithFluid(world, pos, playerIn, heldItem, side))
        {
            super.onBlockActivated(world, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
            playerIn.openGui(Crystek.instance, GuiHandler.fluidGenerator, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
