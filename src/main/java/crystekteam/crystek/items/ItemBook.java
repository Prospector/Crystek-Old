package crystekteam.crystek.items;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.GuiHandler;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Gigabit101 on 08/07/2016.
 */
public class ItemBook extends ItemBase
{
    public ItemBook()
    {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".book");
        setRegistryName("book");
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(Crystek.instance, GuiHandler.book, worldIn, 0, 0, 0);
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(TextFormatting.RED + "" + TextFormatting.BOLD + "<WIP>");
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
