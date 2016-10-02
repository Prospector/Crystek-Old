package crystekteam.crystek.items.Itemblocks;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Gigabit101 on 06/07/2016.
 */
public class ItemBlockMachine extends ItemBlock
{
    public ItemBlockMachine(Block block)
    {
        super(block);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (stack != null && stack.hasTagCompound())
        {
            if (stack.getTagCompound().getCompoundTag("tileEntity").getCompoundTag("TeslaContainer") != null)
            {
                list.add("" + TextFormatting.DARK_AQUA + stack.getTagCompound().getCompoundTag("tileEntity").getCompoundTag("TeslaContainer").getLong("TeslaPower") + "/"
                        + stack.getTagCompound().getCompoundTag("tileEntity").getCompoundTag("TeslaContainer").getLong("TeslaCapacity") + " Tesla");
            }
        }
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState))
        {
            return false;
        }
        if (world.getBlockState(pos).getBlock() == block)
        {
            world.getBlockState(pos).getBlock().onBlockPlacedBy(world, pos, newState, player, stack);
        }
        if (stack != null && stack.hasTagCompound())
        {
            ((TileBase) world.getTileEntity(pos)).readFromNBTWithoutCoords(stack.getTagCompound().getCompoundTag("tileEntity"));
        }
        return true;
    }
}
