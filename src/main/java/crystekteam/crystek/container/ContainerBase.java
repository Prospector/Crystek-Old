package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCircuit;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class ContainerBase extends Container
{
    EntityPlayer player;
    TileBase tile;
    public int progress;
    public long power;
    public int burnTime;

    public ContainerBase(TileBase tile, EntityPlayer player)
    {
        super();
        this.player = player;
        this.tile = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    public void addPlayersHotbar()
    {
        int i;
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    public void addPlayersInventory()
    {
        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    public void addUpgradeSlots(TileBase tile, int upgradeslotStart)
    {
        this.addSlotToContainer(new SlotCircuit(tile.getInv(), upgradeslotStart, 152, 26));
        this.addSlotToContainer(new SlotCircuit(tile.getInv(), upgradeslotStart + 1, 152, 44));
        this.addSlotToContainer(new SlotCircuit(tile.getInv(), upgradeslotStart + 2, 152, 62));
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++)
        {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.power != tile.getPowerScaled(47)) {

                IContainerListener.sendProgressBarUpdate(this, 0, (int) tile.getPowerScaled(47));
            }
            if (this.progress != tile.getProgress())
            {
                IContainerListener.sendProgressBarUpdate(this, 1, tile.getProgress());
            }
            if (this.burnTime != tile.getScaledBurnTime(13))
            {
                IContainerListener.sendProgressBarUpdate(this, 2, tile.getScaledBurnTime(13));
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting)
    {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 0, (int) tile.getPowerScaled(47));
        crafting.sendProgressBarUpdate(this, 1, tile.getProgress());
        crafting.sendProgressBarUpdate(this, 2, tile.getScaledBurnTime(13));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value)
    {
        if (id == 0)
        {
            this.power = value;
        }
        else if (id == 1)
        {
            this.progress = value;
        }
        else if (id == 2)
        {
            this.burnTime = value;
        }
    }

    /*
    * Handles shift clicking for all machines
    */

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i)
    {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            int playerInv = 30;
            if (i < this.tile.getSizeInventory() + playerInv)
            {
                if (!this.mergeItemStack(itemstack1, this.tile.getSizeInventory() + playerInv, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.tile.getSizeInventory() + playerInv, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
