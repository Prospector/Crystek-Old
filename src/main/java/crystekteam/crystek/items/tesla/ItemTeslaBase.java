package crystekteam.crystek.items.tesla;

import crystekteam.crystek.items.ItemCrystek;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class ItemTeslaBase extends ItemCrystek
{
    private long maxCapacity;
    private long output;
    private long input;

    public ItemTeslaBase(String name, long maxCapacity, long input, long output)
    {
        super(name);
        setMaxStackSize(1);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        ItemStack powered = createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    public static ItemStack createChargedStack(ItemStack stack)
    {
        ItemStack chargedstack = stack.copy();
        BaseTeslaContainer container = (BaseTeslaContainer) chargedstack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, null);
        container.setInputRate(container.getCapacity());
        container.givePower(container.getCapacity(), false);
        container.setInputRate(container.getInputRate());
        return chargedstack;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, null);
        return (1 - (double) container.getStoredPower() / (double) container.getCapacity());
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean isRepairable()
    {
        return false;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new BaseTeslaContainerProvider(new BaseTeslaContainer(maxCapacity, output, input));
    }
}
