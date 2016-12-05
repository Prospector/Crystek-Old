package crystekteam.crystekold.api;


import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 06/07/2016.
 */
public interface IWrenchable
{
    boolean isWrenchable();

    ItemStack returnStack();
}
