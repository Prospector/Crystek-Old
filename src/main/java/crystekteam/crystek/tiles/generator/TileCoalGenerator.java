package crystekteam.crystek.tiles.generator;

import crystekteam.crystek.config.ConfigAE;
import crystekteam.crystek.tiles.prefab.TileGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;

/**
 * Created by Gigabit101 on 31/05/2016.
 */
public class TileCoalGenerator extends TileGenerator {
    public int fuelSlot = 0;
    public int output;
    public long baseoutput = ConfigAE.generatorTick;

    public boolean isBurning;
    public boolean lastTickBurning;
    ItemStack burnItem;

    public NBTTagCompound stackTagCompound;
    public int networkID;

    public TileCoalGenerator() {
        super(2, "coalgenerator", 64, 10000, 50, 50, 0, 100);
        this.hasInv = true;
        this.hasTank = false;
        this.hasTesla = true;
    }

    @Override
    public void update() {
        if (worldObj.isRemote) {
            return;
        }
        if (getStoredPower() < getMaxCapacity()) {
            if (burnTime > 0) {
                burnTime--;
                generatePower(baseoutput);
                isBurning = true;
            }
        } else {
            isBurning = false;
        }
        if (burnTime == 0) {
            this.updateState();
            burnTime = totalBurnTime = getItemBurnTime(getStackInSlot(fuelSlot));
            if (burnTime > 0) {
                this.updateState();
                burnItem = getStackInSlot(fuelSlot);
                if (getStackInSlot(fuelSlot).stackSize == 1) {
                    setInventorySlotContents(fuelSlot, null);
                } else {
                    decrStackSize(fuelSlot, 1);
                }
            }
        }
        lastTickBurning = isBurning;
        for (final EnumFacing side : EnumFacing.values()) {
            transferPowerTo(side);
        }
        if (stackTagCompound == null) {
            stackTagCompound = new NBTTagCompound();
        }
        stackTagCompound.setInteger("burnTime", burnTime);
        stackTagCompound.setInteger("totalBurnTime", totalBurnTime);
        writeToNBT(stackTagCompound);
    }

    public static int getItemBurnTime(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack) / 4;
    }

}
