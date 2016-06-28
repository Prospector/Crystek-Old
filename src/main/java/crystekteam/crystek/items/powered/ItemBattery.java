package crystekteam.crystek.items.powered;

import crystekteam.crystek.items.ItemTeslaBase;
import crystekteam.crystek.lib.ModInfo;

/**
 * Created by Gigabit101 on 25/06/2016.
 */
public class ItemBattery extends ItemTeslaBase
{
    public ItemBattery()
    {
        super(50000, 100, 100);
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".battery");
        setRegistryName("battery");
    }
}
