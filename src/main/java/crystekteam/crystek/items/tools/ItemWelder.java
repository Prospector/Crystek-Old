package crystekteam.crystek.items.tools;

import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.api.item.IWelderItem;
import crystekteam.crystek.items.ItemBase;

public class ItemWelder extends ItemBase implements IWelderItem
{
    public ItemWelder()
    {
        setMaxStackSize(1);
        setRegistryName("welder");
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".welder");
    }
}
