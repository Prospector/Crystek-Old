package crystekteam.crystek.blocks.machines;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.GuiHandler;
import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.init.ModFluids;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileCrystallizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class BlockCrystallizer extends BlockBase
{
    public BlockCrystallizer()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".crystallizer");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!playerIn.isSneaking()) {
            playerIn.openGui(Crystek.instance, GuiHandler.crystallizer, worldIn, pos.getX(), pos.getY(), pos.getZ());

        }
        //DEBUG
        if(playerIn.isSneaking()) {
            TileCrystallizer tile = (TileCrystallizer) worldIn.getTileEntity(pos);
            tile.setFluid(new FluidStack(ModFluids.fluidTesla, 4000));
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileCrystallizer();
    }
}