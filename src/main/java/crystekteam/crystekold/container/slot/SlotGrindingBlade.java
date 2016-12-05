package crystekteam.crystekold.container.slot;

import crystekteam.crystekold.items.misc.ItemGrindingBlade;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 25/06/2016.
 */
public class SlotGrindingBlade extends SlotBase
{
    public SlotGrindingBlade(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack)
    {
        if (stack.getItem() instanceof ItemGrindingBlade)
            return true;
        return false;
    }
}
