package crystekteam.crystekold.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class SlotBase extends Slot
{
    public SlotBase(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean canWorldBlockRemove()
    {
        return true;
    }
}
