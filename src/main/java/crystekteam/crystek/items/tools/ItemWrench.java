package crystekteam.crystek.items.tools;

import crystekteam.crystek.api.IWrenchable;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.laser.TileLaser;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.util.ItemNBTHelper;

import java.util.List;

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
    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY) {
        if (!playerIn.isRemote && stack.isSneaking() && playerIn.getTileEntity(worldIn) != null && playerIn.getTileEntity(worldIn) instanceof IWrenchable)
        {
            TileEntity tileEntity = playerIn.getTileEntity(worldIn);
            IWrenchable wrenchable = (IWrenchable) tileEntity;
            ItemStack returnstack = wrenchable.returnStack();
            if (wrenchable.isWrenchable() && returnstack != null)
            {
                playerIn.spawnEntity(new EntityItem(playerIn, worldIn.getX(), worldIn.getY(), worldIn.getZ(), returnstack));
                playerIn.removeTileEntity(worldIn);
                playerIn.setBlockToAir(worldIn);
                return EnumActionResult.SUCCESS;
            }
        }
        //Todo clean this up
        if (!stack.isSneaking())
        {
            if (playerIn.getTileEntity(worldIn) instanceof TileLaser)
            {
                TileLaser laser = (TileLaser) playerIn.getTileEntity(worldIn);
                if (!ItemNBTHelper.verifyExistance(stack.getHeldItem(pos), "xworldIn"))
                {
                    ItemNBTHelper.setInt(stack.getHeldItem(pos), "xworldIn", worldIn.getX());
                    ItemNBTHelper.setInt(stack.getHeldItem(pos), "yworldIn", worldIn.getY());
                    ItemNBTHelper.setInt(stack.getHeldItem(pos), "zworldIn", worldIn.getZ());
                } else if (ItemNBTHelper.verifyExistance(stack.getHeldItem(pos), "xworldIn"))
                {
                    int x = ItemNBTHelper.getInt(stack.getHeldItem(pos), "xworldIn", 0);
                    int y = ItemNBTHelper.getInt(stack.getHeldItem(pos), "yworldIn", 0);
                    int z = ItemNBTHelper.getInt(stack.getHeldItem(pos), "zworldIn", 0);
                    if (laser.getConnectedLaser() == null)
                    {
                        laser.setConnectedLaser(new BlockPos(x, y, z));
                        System.out.print("fafasf");

                        stack.getHeldItem(pos).getTagCompound().removeTag("xworldIn");
                        stack.getHeldItem(pos).getTagCompound().removeTag("yworldIn");
                        stack.getHeldItem(pos).getTagCompound().removeTag("zworldIn");
                    }
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        if (ItemNBTHelper.verifyExistance(stack, "xPos"))
        {
            tooltip.add("X= " + ItemNBTHelper.getInt(stack, "xPos", 0) + " Y= " + ItemNBTHelper.getInt(stack, "yPos", 0) + " Z= " + ItemNBTHelper.getInt(stack, "zPos", 0));
        }
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
