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
        this.addSlotToContainer(new SlotCircuit(tile.getInv(), upgradeslotStart, 152, 15));
        this.addSlotToContainer(new SlotCircuit(tile.getInv(), upgradeslotStart + 1, 152, 35));
        this.addSlotToContainer(new SlotCircuit(tile.getInv(), upgradeslotStart + 2, 152, 55));
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
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack originalStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        int numSlots = inventorySlots.size();
        if (slot != null && slot.getHasStack())
        {
            ItemStack stackInSlot = slot.getStack();
            originalStack = stackInSlot.copy();
            if (slotIndex >= numSlots - 9 * 4 && tryShiftItem(stackInSlot, numSlots))
            {
                // NOOP
            } else if (slotIndex >= numSlots - 9 * 4 && slotIndex < numSlots - 9)
            {
                if (!shiftItemStack(stackInSlot, numSlots - 9, numSlots))
                {
                    return null;
                }
            } else if (slotIndex >= numSlots - 9 && slotIndex < numSlots)
            {
                if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots - 9))
                {
                    return null;
                }
            } else if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots))
            {
                return null;
            }
            slot.onSlotChange(stackInSlot, originalStack);
            if (stackInSlot.stackSize <= 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if (stackInSlot.stackSize == originalStack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, stackInSlot);
        }
        return originalStack;
    }

    protected boolean shiftItemStack(ItemStack stackToShift, int start, int end)
    {
        boolean changed = false;
        if (stackToShift.isStackable())
        {
            for (int slotIndex = start; stackToShift.stackSize > 0 && slotIndex < end; slotIndex++)
            {
                Slot slot = (Slot) inventorySlots.get(slotIndex);
                ItemStack stackInSlot = slot.getStack();
                if (stackInSlot != null && canStacksMerge(stackInSlot, stackToShift))
                {
                    int resultingStackSize = stackInSlot.stackSize + stackToShift.stackSize;
                    int max = Math.min(stackToShift.getMaxStackSize(), slot.getSlotStackLimit());
                    if (resultingStackSize <= max)
                    {
                        stackToShift.stackSize = 0;
                        stackInSlot.stackSize = resultingStackSize;
                        slot.onSlotChanged();
                        changed = true;
                    } else if (stackInSlot.stackSize < max)
                    {
                        stackToShift.stackSize -= max - stackInSlot.stackSize;
                        stackInSlot.stackSize = max;
                        slot.onSlotChanged();
                        changed = true;
                    }
                }
            }
        }
        if (stackToShift.stackSize > 0)
        {
            for (int slotIndex = start; stackToShift.stackSize > 0 && slotIndex < end; slotIndex++)
            {
                Slot slot = (Slot) inventorySlots.get(slotIndex);
                ItemStack stackInSlot = slot.getStack();
                if (stackInSlot == null)
                {
                    int max = Math.min(stackToShift.getMaxStackSize(), slot.getSlotStackLimit());
                    stackInSlot = stackToShift.copy();
                    stackInSlot.stackSize = Math.min(stackToShift.stackSize, max);
                    stackToShift.stackSize -= stackInSlot.stackSize;
                    slot.putStack(stackInSlot);
                    slot.onSlotChanged();
                    changed = true;
                }
            }
        }
        return changed;
    }

    private boolean tryShiftItem(ItemStack stackToShift, int numSlots)
    {
        for (int machineIndex = 0; machineIndex < numSlots - 9 * 4; machineIndex++)
        {
            Slot slot = (Slot) inventorySlots.get(machineIndex);
            if (!slot.isItemValid(stackToShift))
                continue;
            if (shiftItemStack(stackToShift, machineIndex, machineIndex + 1))
                return true;
        }
        return false;
    }

    public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2)
    {
        if (stack1 == null || stack2 == null)
        {
            return false;
        }
        if (!stack1.isItemEqual(stack2))
        {
            return false;
        }
        if (!ItemStack.areItemStackTagsEqual(stack1, stack2))
        {
            return false;
        }
        return true;

    }
}
