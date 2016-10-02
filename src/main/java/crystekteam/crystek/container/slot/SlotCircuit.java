package crystekteam.crystek.container.slot;

import crystekteam.crystek.items.tools.ItemCircuit;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 25/06/2016.
 */
public class SlotCircuit extends SlotBase
{
    public SlotCircuit(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack)
    {
        if (stack.getItem() instanceof ItemCircuit)
            return true;
        return false;
    }
}
