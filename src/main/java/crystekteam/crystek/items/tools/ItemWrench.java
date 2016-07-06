package crystekteam.crystek.items.tools;

import crystekteam.crystek.api.IWrenchable;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 06/07/2016.
 */
public class ItemWrench extends ItemBase
{
    public ItemWrench()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".wrench");
        setRegistryName("wrench");
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote && playerIn.isSneaking() && worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof IWrenchable)
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            IWrenchable wrenchable = (IWrenchable) tileEntity;
            ItemStack returnstack = wrenchable.returnStack();
            if(wrenchable.isWrenchable() && returnstack != null)
            {
                worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), returnstack));
                worldIn.destroyBlock(pos, false);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }
}
