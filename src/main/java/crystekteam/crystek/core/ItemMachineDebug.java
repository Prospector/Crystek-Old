package crystekteam.crystek.core;

import crystekteam.crystek.items.ItemCrystek;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 17/01/2017.
 */
public class ItemMachineDebug extends ItemCrystek
{
    public ItemMachineDebug()
    {
        super("machine_debug");
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof Machine && !worldIn.isRemote)
        {
            Machine machine = (Machine) worldIn.getTileEntity(pos);

            player.sendMessage(new TextComponentString("Machine Name = " + machine.getName()));
            player.sendMessage(new TextComponentString("Inv Size = " + machine.getInv().getSlots()));
            player.sendMessage(new TextComponentString("Gui ID = " + machine.guiID()));
            player.sendMessage(new TextComponentString("Sored power = " + machine.getTeslaContainer().getStoredPower() + "   Capacity = " + machine.getTeslaContainer().getCapacity()));
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
