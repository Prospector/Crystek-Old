package crystekteam.crystek.container.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 21/01/2017.
 */
public class SlotFiltered extends SlotItemHandler
{
    List<ItemStack> list = new ArrayList<ItemStack>();

    public SlotFiltered(IItemHandler itemHandler, int index, int xPosition, int yPosition, List<ItemStack> list)
    {
        super(itemHandler, index, xPosition, yPosition);
        this.list = list;
    }

    public SlotFiltered(IItemHandler itemHandler, int index, int xPosition, int yPosition, ItemStack list)
    {
        super(itemHandler, index, xPosition, yPosition);
        this.list.add(list);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        if(this.list.contains(stack))
        {
            return true;
        }
        return false;
    }
}
