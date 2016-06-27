package crystekteam.crystek.items.tools;

import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.items.ItemBase;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ItemPowerScanner extends ItemBase
{
    public ItemPowerScanner()
    {
        setMaxStackSize(1);
        setRegistryName("powerscanner");
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".powerscanner");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote && playerIn.isSneaking())
        {
            final TileEntity tile = worldIn.getTileEntity(pos);
            if (tile.hasCapability(TeslaCapabilities.CAPABILITY_HOLDER, side))
            {
                final ITeslaHolder holder = tile.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, side);
                playerIn.addChatMessage(new TextComponentString(tile.getBlockType().getLocalizedName() +  " " + holder.getStoredPower() + " / " + holder.getCapacity() + " power."));
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(TextFormatting.GRAY+""+TextFormatting.ITALIC+I18n.translateToLocal("desc.teslometer"));
    }
}
