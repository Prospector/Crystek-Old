package crystekteam.crystek.tiles;

import crystekteam.crystek.api.AdvancedEngineeringApi;
import crystekteam.crystek.api.CraftingBlocks;
import crystekteam.crystek.api.recipe.CrafterRecipe;
import crystekteam.crystek.util.Inventory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class TileMachineFrame extends TileEntity implements IInventory
{
    private static int TARGET_SLOT = 0;
    public static int progress;
    public static int shift = 9;

    public Inventory inv = new Inventory(100, "machineframe", 64, this);

    public TileMachineFrame()
    {
        this.progress = 0;
    }

    //Sets the ItemStacks from the Registered ItemStacks
    public void setStacks()
    {
        int i;
        for (i = 0; i < CraftingBlocks.stacks.size(); ++i)
        {
            if(!worldObj.isRemote)
            {
                if(CraftingBlocks.stacks.get(i) != null && getStackInSlot(i + shift) == null)
                {
                    setInventorySlotContents(i + shift, CraftingBlocks.stacks.get(i));
                    System.out.println("Adding Stacks To " + i + " " + CraftingBlocks.stacks.get(i).getDisplayName());
                }
            }
        }
    }

    public List<Object> getRecipe()
    {
        for(CrafterRecipe recipe : AdvancedEngineeringApi.crafterRecipes)
        {
            return recipe.getInputs(getStackInSlot(TARGET_SLOT));
        }
        return null;
    }

    public void updateRecipe(EntityPlayer player)
    {
        if(!worldObj.isRemote)
        if(getTargetSlot() != null)
        {
            InventoryPlayer playerinv = player.inventory;
            if(getProgress() >= 0)
            {
                copyItemStack(player, (ItemStack) getRecipe().get(0), 1);
                copyItemStack(player, (ItemStack) getRecipe().get(1), 2);
                copyItemStack(player, (ItemStack) getRecipe().get(2), 3);
                copyItemStack(player, (ItemStack) getRecipe().get(3), 4);
                copyItemStack(player, (ItemStack) getRecipe().get(4), 5);
            }
            if(getStackInSlot(1) == getRecipe().get(0) && getStackInSlot(2) == getRecipe().get(1) && getStackInSlot(3) == getRecipe().get(2)
                    && getStackInSlot(4) == getRecipe().get(3) && getStackInSlot(5) == getRecipe().get(4))
            {
                increaseProgress();
                if(getProgress() == 100)
                {
                    worldObj.setBlockState(pos, Block.getBlockFromItem(getStackInSlot(TARGET_SLOT).getItem()).getDefaultState());
                }
            }
        }
    }

    public boolean copyItemStack(EntityPlayer player, ItemStack stack, int slot)
    {
        InventoryPlayer playerinv = player.inventory;
        if(getStackInSlot(slot) == null)
        {
            if(stack != null && playerinv.hasItemStack(stack))
            {
                playerinv.decrStackSize(playerinv.getSlotFor((ItemStack) stack), 1);
                setInventorySlotContents(slot, stack);
                increaseProgress();
                return true;
            }
            else if(stack == null)
            {
                increaseProgress();
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void consumeItem(EntityPlayer player, ItemStack stack)
    {
        InventoryPlayer playerinv = player.inventory;
        playerinv.decrStackSize(playerinv.getSlotFor((ItemStack) stack), 1);
    }

    public void setTargetSlot(ItemStack output)
    {
        if(getStackInSlot(TARGET_SLOT) == null)
            setInventorySlotContents(TARGET_SLOT, output);
    }

    public ItemStack getTargetSlot()
    {
        if(getStackInSlot(TARGET_SLOT) != null)
            return getStackInSlot(TARGET_SLOT);
        return null;
    }

    public void increaseProgress()
    {
        progress++;
    }

    public int getProgress()
    {
        return progress;
    }

    @Override
    public int getSizeInventory()
    {
        return inv.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inv.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return inv.decrStackSize(index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return inv.removeStackFromSlot(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        inv.setInventorySlotContents(index, stack);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return inv.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value){}

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear() {}

    @Override
    public String getName()
    {
        return inv.getName();
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return inv.getDisplayName();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        inv.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        inv.readFromNBT(compound);
    }

    public void addWailaInfo(List<String> info)
    {
        if (getStackInSlot(TARGET_SLOT) != null)
        {
            info.add(TextFormatting.DARK_PURPLE + "Target = " + getStackInSlot(TARGET_SLOT).getDisplayName());
            if (getRecipe() != null)
            {
                info.add(TextFormatting.AQUA + "Progress " + getProgress());
                if(getStackInSlot(1) == null)
                    info.add(((ItemStack) getRecipe().get(0)).getDisplayName());
                if(getStackInSlot(2) == null)
                    info.add(((ItemStack) getRecipe().get(1)).getDisplayName());
                if(getStackInSlot(3) == null)
                    info.add(((ItemStack) getRecipe().get(2)).getDisplayName());
                if(getStackInSlot(4) == null)
                    info.add(((ItemStack) getRecipe().get(3)).getDisplayName());
                if(getStackInSlot(5) == null)
                    info.add(((ItemStack) getRecipe().get(4)).getDisplayName());

                if(getStackInSlot(1) != null)
                    info.add(TextFormatting.GREEN + getStackInSlot(1).getDisplayName());
                if(getStackInSlot(2) != null)
                    info.add(TextFormatting.GREEN + getStackInSlot(2).getDisplayName());
                if(getStackInSlot(3) != null)
                    info.add(TextFormatting.GREEN + getStackInSlot(3).getDisplayName());
                if(getStackInSlot(4) != null)
                    info.add(TextFormatting.GREEN + getStackInSlot(4).getDisplayName());
                if(getStackInSlot(5) != null)
                    info.add(TextFormatting.GREEN + getStackInSlot(5).getDisplayName());

            }
        }
        else
        {
            info.add("Target = null");
        }
    }
}
