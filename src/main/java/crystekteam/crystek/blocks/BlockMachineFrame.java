package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.GuiHandler;
import crystekteam.crystek.api.item.IWelderItem;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.TileMachineFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMachineFrame extends BlockBase
{
    public BlockMachineFrame()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".machineframe");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileMachineFrame();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileMachineFrame tile = (TileMachineFrame) worldIn.getTileEntity(pos);

        if(playerIn.getHeldItemMainhand() != null && playerIn.getHeldItemMainhand().getItem() instanceof IWelderItem)
        {
            tile.updateRecipe(playerIn);
        }
        else
        {
            playerIn.openGui(Crystek.instance, GuiHandler.machineFrame, worldIn, pos.getX(), pos.getY(), pos.getZ());
            tile.setStacks();
        }
        return true;
    }
}
