package crystekteam.crystek.items.tools;

import crystekteam.crystek.api.IWrenchable;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.laser.TileLaser;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.util.ItemNBTHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
                worldIn.removeTileEntity(pos);
                worldIn.setBlockToAir(pos);
                return EnumActionResult.SUCCESS;
            }
        }
        //Todo clean this up
        if(!worldIn.isRemote && !playerIn.isSneaking())
        {
            if(worldIn.getTileEntity(pos) instanceof TileLaser)
            {
                TileLaser laser = (TileLaser) worldIn.getTileEntity(pos);
                if(ItemNBTHelper.detectNBT(stack))
                {
                    ItemNBTHelper.setInt(stack, "xPos", pos.getX());
                    ItemNBTHelper.setInt(stack, "yPos", pos.getY());
                    ItemNBTHelper.setInt(stack, "zPos", pos.getZ());
                }
                else if(ItemNBTHelper.getInt(stack, "xPos", 0) != pos.getX() && ItemNBTHelper.getInt(stack, "zPos", 0)!= pos.getZ())
                {
                    int x = ItemNBTHelper.getInt(stack, "xPos", 0);
                    int y = ItemNBTHelper.getInt(stack, "yPos", 0);
                    int z = ItemNBTHelper.getInt(stack, "zPos", 0);
                    if(laser.getConnectedTile() == null)
                    {
                        laser.setConnectedTile(worldIn.getTileEntity(new BlockPos(x, y, z)));
                        System.out.print("fafasf");

                        stack.getTagCompound().removeTag("xPos");
                        stack.getTagCompound().removeTag("yPos");
                        stack.getTagCompound().removeTag("zPos");
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
        if(ItemNBTHelper.verifyExistance(stack, "xPos"))
        {
            tooltip.add("X= " + ItemNBTHelper.getInt(stack, "xPos", 0) + " Y= " + ItemNBTHelper.getInt(stack, "yPos", 0) + " Z= " + ItemNBTHelper.getInt(stack, "zPos", 0));
        }
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
