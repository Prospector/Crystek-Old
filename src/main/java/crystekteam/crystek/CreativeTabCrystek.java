package crystekteam.crystek;

import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCrystek extends CreativeTabs
{
    public static CreativeTabCrystek instance = new CreativeTabCrystek();

    public CreativeTabCrystek()
    {
        super(ModInfo.MOD_ID);
    }

    @Override
    public Item getTabIconItem()
    {
        return ModItems.ironGrindingBlade;
    }
}
