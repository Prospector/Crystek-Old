package crystekteam.crystek.items.tools;

import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ItemCircuit extends ItemBase
{
    public static final String[] types = new String[]{"circuitSpeed", "circuitStorage", "circuitEfficiency"};

    public ItemCircuit()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".circuit");
        setHasSubtypes(true);
        setRegistryName("circuit");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (int meta = 0; meta < types.length; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    // gets Unlocalized Name depending on meta data
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta >= types.length)
        {
            meta = 0;
        }
        return super.getUnlocalizedName() + "." + types[meta];
    }

    public static ItemStack getStack(String name, int count)
    {
        for (int i = 0; i < types.length; i++)
        {
            if (types[i].equalsIgnoreCase(name))
            {
                return new ItemStack(ModItems.circuit, count, i);
            }
        }
        throw new InvalidParameterException("Stack not found: " + name);
    }

    public static ItemStack getStack(String name)
    {
        return getStack(name, 1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocal("desc.circuit"));
        tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.translateToLocal("desc.circuit." + types[stack.getMetadata()].substring(7).toLowerCase() + ".1"));
        tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.translateToLocal("desc.circuit." + types[stack.getMetadata()].substring(7).toLowerCase() + ".2"));
    }
}
