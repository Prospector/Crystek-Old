package crystekteam.crystek.tiles;

import crystekteam.crystek.Crystek;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class TileCrystek extends TileEntity
{
    @Nonnull boolean hasInventory = false;
    @Nullable boolean hasGui = false;
    @Nonnull int invSize = 0;
    @Nullable ItemStackHandler inv = new StackHandler(getInvSize());
    @Nullable GuiContainer guiContainer = null;
    @Nullable Container container = null;
    @Nullable int GUI_ID = -1;

    //Inv
    public boolean isHasInventory()
    {
        return this.hasInventory;
    }

    public int getInvSize()
    {
        return this.invSize;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> cap, @Nonnull EnumFacing side)
    {
        return hasInventory && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
    }

    @Nonnull
    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, @Nonnull EnumFacing side)
    {
        if (hasInventory && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        inv.deserializeNBT(nbt);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt = super.writeToNBT(nbt);
        nbt.merge(inv.serializeNBT());
        return nbt;
    }

    @Nullable
    public ItemStackHandler getInv()
    {
        return inv;
    }

    @Nullable
    public int getGUI_ID()
    {
        return GUI_ID;
    }

    public void setGUI_ID(@Nullable int GUI_ID)
    {
        this.GUI_ID = GUI_ID;
    }

    //Gui
    public boolean isHasGui()
    {
        return this.hasGui;
    }

    public void setHasGui(boolean hasGui)
    {
        this.hasGui = hasGui;
    }

    @Nullable
    public GuiContainer getGuiContainer()
    {
        return guiContainer;
    }

    public void setGuiContainer(GuiContainer guiContainer)
    {
        this.guiContainer = guiContainer;
    }

    //Container
    @Nullable
    public Container getContainer()
    {
        return container;
    }

    public void setContainer(Container container)
    {
        this.container = container;
    }

    public boolean hasContainer()
    {
        if(getContainer() != null)
        {
            return true;
        }
        return false;
    }

    public void openGui(EntityPlayer player)
    {
        player.openGui(Crystek.modcl, getGUI_ID(), this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
    }

    //TODO move
    class StackHandler extends ItemStackHandler
    {
        StackHandler(int size)
        {
            super(size);
        }
    }
}
