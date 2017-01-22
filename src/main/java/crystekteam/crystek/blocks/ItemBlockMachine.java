package crystekteam.crystek.blocks;

import crystekteam.crystek.core.Machine;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.itemblock.ItemBlockBase;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class ItemBlockMachine extends ItemBlock
{
    public ItemBlockMachine(Block block)
    {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState)) {
            return false;
        }
        if (world.getBlockState(pos).getBlock() == block) {
            world.getBlockState(pos).getBlock().onBlockPlacedBy(world, pos, newState, player, stack);
        }
        if (stack != ItemStack.EMPTY && stack.hasTagCompound()) {
            ((Machine) world.getTileEntity(pos)).readFromNBTWithoutCoords(stack.getTagCompound().getCompoundTag("tileEntity"));
        }
        return true;
    }
}
