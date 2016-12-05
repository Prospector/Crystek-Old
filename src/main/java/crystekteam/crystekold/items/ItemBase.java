package crystekteam.crystekold.items;

import crystekteam.crystekold.CreativeTabCrystek;
import net.minecraft.item.Item;

public class ItemBase extends Item
{
    public ItemBase()
    {
        setCreativeTab(CreativeTabCrystek.instance);
    }
}
