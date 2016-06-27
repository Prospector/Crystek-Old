package crystekteam.crystek.items;

import crystekteam.crystek.CreativeTabCrystek;
import net.minecraft.item.Item;

public class ItemBase extends Item
{
    public ItemBase()
    {
        setCreativeTab(CreativeTabCrystek.instance);
    }
}
