package crystekteam.crystek.tesla;

import net.darkhax.tesla.api.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class TeslaUtils
{
    public static boolean isPoweredItem(ItemStack stack)
    {
        if(stack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN))
            return true;
        return false;
    }

    public static long getStoredPower(ItemStack stack)
    {
        if(isPoweredItem(stack))
        {
            BaseTeslaContainer container = getContainer(stack);
            return getContainer(stack).getStoredPower();
        }
        else return 0;
    }

    public static long getMaxCapacity(ItemStack stack)
    {
        if(isPoweredItem(stack))
        {
            return getContainer(stack).getCapacity();
        }
        else return 0;
    }

    public static void addPower(ItemStack stack, long amount)
    {
        if(isPoweredItem(stack))
        {
            getContainer(stack).givePower(amount, false);
        }
    }

    public static BaseTeslaContainer getContainer(ItemStack stack)
    {
        if(isPoweredItem(stack))
        {
            BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
            return container;
        }
        return null;
    }

    public static void setMaxCapacity(ItemStack stack, long amount)
    {
        if(isPoweredItem(stack))
        {
            getContainer(stack).setCapacity(amount);
        }
    }

    public static void usePower(ItemStack stack, long amount)
    {
        if(isPoweredItem(stack))
        {
            getContainer(stack).takePower(amount, false);
        }
    }

    public static long getMaxInput(ItemStack stack)
    {
        if(isPoweredItem(stack))
        {
            return getContainer(stack).getInputRate();
        }
        return 0;
    }

    public static long getMaxOutput(ItemStack stack)
    {
        if(isPoweredItem(stack))
        {
            return getContainer(stack).getOutputRate();
        }
        return 0;
    }
}
