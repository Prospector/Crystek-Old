package crystekteam.crystekold.blocks.generator;

import crystekteam.crystekold.Crystek;
import crystekteam.crystekold.GuiHandler;
import crystekteam.crystekold.blocks.BlockBase;
import crystekteam.crystekold.lib.ModInfo;
import crystekteam.crystekold.tiles.generator.TileSolarPanel;
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
 * Created by Gigabit101 on 25/06/2016.
 */
public class BlockSolarPanel extends BlockBase
{
    public BlockSolarPanel()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".solarpanel");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileSolarPanel();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!playerIn.isSneaking())
            playerIn.openGui(Crystek.instance, GuiHandler.solarPanel, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
