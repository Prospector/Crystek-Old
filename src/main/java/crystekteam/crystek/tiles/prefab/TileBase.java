package crystekteam.crystek.tiles.prefab;

import crystekteam.crystek.api.IWrenchable;
import crystekteam.crystek.blocks.BlockBase;
import crystekteam.crystek.network.PacketHandler;
import crystekteam.crystek.tesla.TeslaUtils;
import crystekteam.crystek.util.Inventory;
import crystekteam.crystek.util.Tank;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class TileBase extends TileEntity implements IInventory, ITickable, IWrenchable
{
    public Inventory inv;
    public Tank tank;
    public BaseTeslaContainer container;
    public int progress;
    public int fuelScale;
    public int burnTime;
    public int totalBurnTime;
    public boolean isActive;
    public int maxProgress;

    public TileBase(int invSize, String invName, int invStackLimit, long maxCapacity, long input, long output, int tankCapacity, int maxProgress)
    {
        if(invSize != 0)
        {
            this.inv = new Inventory(invSize, invName, invStackLimit, this);
        }
        if(maxCapacity != 0)
        {
            this.container = new BaseTeslaContainer(maxCapacity, input, output);
        }
        if(tankCapacity != 0)
        {
            this.tank = new Tank(tankCapacity, this);
        }
        this.maxProgress=maxProgress;
    }

    public void generatePower(long amount)
    {
        this.container.givePower(amount, false);
    }

    public long getStoredPower()
    {
        return this.container.getStoredPower();
    }

    public long getMaxCapacity(){ return this.container.getCapacity(); }

    public void usePower(long amount)
    {
        this.container.takePower(amount, false);
    }

    public Inventory getInv()
    {
        return this.inv;
    }

    @Override
    public int getSizeInventory()
    {
        return inv.getSizeInventory();
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inv.getStackInSlot(index);
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return inv.decrStackSize(index, count);
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return inv.removeStackFromSlot(index);
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
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
        return inv.isUseableByPlayer(player);
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return inv.isItemValidForSlot(index, stack);
    }

    @Override
    public int getField(int id) {return 0;}

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {return 0;}

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
        return inv.hasCustomName();
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return inv.getDisplayName();
    }

    public void syncWithAll()
    {
        if (!worldObj.isRemote)
        {
            PacketHandler.sendPacketToAllPlayers(getUpdatePacket(), worldObj);
        }
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(getPos(), getBlockMetadata(), writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
    {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void update() {}

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        writeToNBTWithoutCoords(compound);
        return super.writeToNBT(compound);
    }

    public NBTTagCompound writeToNBTWithoutCoords(NBTTagCompound tagCompound)
    {
        if(tank != null)
         tank.writeToNBT(tagCompound);
        if(inv != null)
            inv.writeToNBT(tagCompound);
        tagCompound.setTag("TeslaContainer", this.container.serializeNBT());
        return tagCompound;
    }


    public void readFromNBTWithoutCoords(NBTTagCompound tagCompound)
    {
        if(tank != null)
            tank.readFromNBT(tagCompound);
        if(inv != null)
            inv.readFromNBT(tagCompound);
        this.container = new BaseTeslaContainer(tagCompound.getCompoundTag("TeslaContainer"));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        readFromNBTWithoutCoords(compound);
    }

    public long getPowerScaled (int scale)
    {
        return (this.getStoredPower() * scale) / this.getMaxCapacity();
    }

    public int getProgress()
    {
        return this.progress;
    }

    public void addProgress()
    {
        this.progress++;
    }

    public void addProgress(int amount)
    {
        this.progress += amount;
    }

    public void resetProgress()
    {
        this.progress = 0;
    }

    public int getBurnTime(){ return this.burnTime; }

    public int getFuelScale()
    {
        return this.fuelScale;
    }

    public void setBurnTime(int amount)
    {
        this.burnTime = amount;
    }

    public int getProgressScaled(int scale)
    {
        return ((this.getProgress() * scale) / this.getFuelScale());
    }

    public int getScaledBurnTime(int i)
    {
        return (int) (((float) burnTime / (float) totalBurnTime) * i);
    }

    public int getMaxProgress() {return maxProgress;}

    public void setMaxProgress(int amount) {maxProgress = amount;}

    //Tank
    public int getFluidAmount()
    {
        return tank.getFluidAmount();
    }

    public void setFluid(FluidStack fluidStack)
    {
        tank.setFluid(fluidStack);
    }

    public static <T> List<T> getConnectedCapabilities (Capability<T> capability, World world, BlockPos pos)
    {
        final List<T> capabilities = new ArrayList<T>();

        for (final EnumFacing side : EnumFacing.values())
        {
            final TileEntity tile = world.getTileEntity(pos.offset(side));

            if (tile != null && !tile.isInvalid() && tile.hasCapability(capability, side.getOpposite()))
                capabilities.add(tile.getCapability(capability, side.getOpposite()));
        }

        return capabilities;
    }

    //Sets the block to active
    public void updateState()
    {
        IBlockState BlockStateContainer = worldObj.getBlockState(pos);
        if (BlockStateContainer.getBlock() instanceof BlockBase)
        {
            BlockBase blockBase = (BlockBase) BlockStateContainer.getBlock();
            if (BlockStateContainer.getValue(BlockBase.ACTIVE) != burnTime > 0)
                blockBase.setActive(burnTime > 0, worldObj, pos);


           else if (BlockStateContainer.getValue(BlockBase.ACTIVE) != progress > 0)
                blockBase.setActive(progress > 0, worldObj, pos);
        }
    }

    //Stops the block from clearing inv when state changed
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        if (oldState.getBlock() != newSate.getBlock())
        {
            return true;
        }
        return false;
    }

    public void handleUpgrades(int upgradeslotstart, long baseoutput){}

    public void handleChargeSlots(int chargeSlotID, boolean hasChargeSlot, int dischargeSlotID, boolean hasDischargeSlot)
    {
        if(hasChargeSlot)
        {
            if(getStackInSlot(chargeSlotID) != null)
            {
                if(TeslaUtils.isPoweredItem(getStackInSlot(chargeSlotID)))
                {
                    ItemStack stack = getStackInSlot(chargeSlotID);
                    if(TeslaUtils.getStoredPower(stack) != TeslaUtils.getMaxCapacity(stack))
                    {
                        long neededPower = TeslaUtils.getMaxCapacity(stack) - TeslaUtils.getStoredPower(stack);
                        if (getStoredPower() >= TeslaUtils.getMaxInput(stack))
                        {
                            TeslaUtils.addPower(stack, TeslaUtils.getMaxInput(stack));
                            usePower(TeslaUtils.getMaxInput(stack));
                        }
                        else if(getStoredPower() >= TeslaUtils.getMaxInput(stack) && neededPower > TeslaUtils.getMaxInput(stack))
                        {
                            TeslaUtils.addPower(stack, neededPower);
                            usePower(neededPower);
                        }
                    }
                }
            }
        }
        if(hasDischargeSlot)
        {
            if(getStackInSlot(dischargeSlotID) != null)
            {
                if(TeslaUtils.isPoweredItem(getStackInSlot(dischargeSlotID)))
                {
                    ItemStack stack = getStackInSlot(dischargeSlotID);
                    long missingPower = getMaxCapacity() - getStoredPower();

                    if (TeslaUtils.getMaxOutput(stack) <= missingPower && TeslaUtils.getStoredPower(stack) >= TeslaUtils.getMaxOutput(stack))
                    {
                        TeslaUtils.usePower(stack, TeslaUtils.getMaxOutput(stack));
                        generatePower(TeslaUtils.getMaxOutput(stack));
                    }
                    if (TeslaUtils.getStoredPower(stack) < TeslaUtils.getMaxOutput(stack))
                    {
                        long remainingpower = TeslaUtils.getStoredPower(stack);
                        TeslaUtils.usePower(stack, remainingpower);
                        generatePower(remainingpower);
                    }
                }
            }
        }
    }

    //Adds info to waila
    public void addWailaInfo(List<String> info)
    {
//        if(container != null)
//        {
//            info.add(this.container.getStoredPower() + " / " + getMaxCapacity());
//        }
    }

    public ItemStack getDropWithNBT()
    {
        NBTTagCompound tileEntity = new NBTTagCompound();
        ItemStack dropStack = new ItemStack(this.getBlockType(), 1);
        writeToNBTWithoutCoords(tileEntity);
        dropStack.setTagCompound(new NBTTagCompound());
        dropStack.getTagCompound().setTag("tileEntity", tileEntity);
        return dropStack;
    }

    @Override
    public boolean isWrenchable()
    {
        return true;
    }

    @Override
    public ItemStack returnStack()
    {
        return getDropWithNBT();//new ItemStack(this.getBlockType());
    }
}
