package crystekteam.crystek.container.slots;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class SlotTeslaCharge extends SlotItemHandler
{
    public SlotTeslaCharge(IItemHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        if(stack != ItemStack.EMPTY && stack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, null) || stack.hasCapability(TeslaCapabilities.CAPABILITY_PRODUCER, null) || stack.hasCapability(TeslaCapabilities.CAPABILITY_PRODUCER, null))
        {
            return true;
        }
        return false;
    }
}
