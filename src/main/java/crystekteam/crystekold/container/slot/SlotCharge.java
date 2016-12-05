package crystekteam.crystekold.container.slot;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class SlotCharge extends SlotBase
{
    public SlotCharge(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack)
    {
        if (stack.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN))
            return true;
        return false;
    }
}
