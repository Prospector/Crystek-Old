package crystekteam.crystek.items.tools;

import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Gigabit101 on 03/06/2016.
 */
@Deprecated
public class ItemLinkDevice extends ItemBase
{
    public BlockPos connection1;
    public BlockPos connection2;

    public ItemLinkDevice()
    {
        setMaxStackSize(1);
        setRegistryName("linkdevice");
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".linkdevice");
        connection1 = null;
        connection2 = null;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(playerIn.isSneaking())
        {
//            TileLaser tile = (TileLaser) worldIn.getTileEntity(pos);
//            if(connection1 == null)
//            {
//                if(connection1 == null && connection2 == null)
//                    setConnection1(pos);
//                else if(connection1 != null && connection2 == null)
//                    setConnection2(pos);
//            }
//            if(connection1 != null && worldIn.getTileEntity(pos) instanceof TileLaser)
//            {
//                if(connection1 != null && tile.getConnection1() == null)
//                {
//                    tile.setConnection1(connection1);
//                    TileLaser tile2 = (TileLaser) worldIn.getTileEntity(connection1);
//                    if(tile2.getConnection1() == connection1)
//                    {
//                        tile2.setConnection1(pos);
//                        //connection1 = null;
//                    }
//                }
//                else if(tile.getConnection1() != null && tile.getConnection2() == null)
//                {
//                    tile.setConnection2(connection2);
//                    connection1 = null;
//                    connection2 = null;
//                }
//            }
        }
        return EnumActionResult.SUCCESS;
    }

    public void setConnection1(BlockPos pos)
    {
        connection1 = pos;
    }

    public void setConnection2(BlockPos pos)
    {
        connection2 = pos;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        if(connection1 != null)
        {
            tooltip.add("Connection 1 " + connection1.getX() + " " + connection1.getY() + " " + connection1.getZ());
        }
        if(connection2 != null)
        {
            tooltip.add("Connection 2 " + connection2.getX() + " " + connection2.getY() + " " + connection2.getZ());
        }
    }
}
