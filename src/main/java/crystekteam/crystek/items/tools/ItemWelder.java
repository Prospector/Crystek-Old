package crystekteam.crystek.items.tools;

import crystekteam.crystek.api.item.IWelderItem;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;

public class ItemWelder extends ItemBase implements IWelderItem
{
    public ItemWelder()
    {
        setMaxStackSize(1);
        setRegistryName("welder");
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".welder");
    }
}
