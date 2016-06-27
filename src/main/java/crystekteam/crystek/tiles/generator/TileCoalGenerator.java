package crystekteam.crystek.tiles.generator;

import crystekteam.crystek.tiles.prefab.TileGenerator;
import crystekteam.crystek.config.ConfigAE;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by Gigabit101 on 31/05/2016.
 */
public class TileCoalGenerator extends TileGenerator
{
    public int fuelSlot = 0;
    public int output;
    public long baseoutput = ConfigAE.generatorTick;

    public boolean isBurning;
    public boolean lastTickBurning;
    ItemStack burnItem;

    public TileCoalGenerator()
    {
        super(2, "coalgenerator", 64, 10000, 50, 50, 0, null);
    }

    @Override
    public void update()
    {
        if (worldObj.isRemote)
        {
            return;
        }
        if (getStoredPower() < getMaxCapacity())
        {
            if (burnTime > 0)
            {
                burnTime--;
                generatePower(baseoutput);
                isBurning = true;
            }
        }
        else
        {
            isBurning = false;
        }
        if (burnTime == 0)
        {
            this.updateState();
            burnTime = totalBurnTime = getItemBurnTime(getStackInSlot(fuelSlot));
            if (burnTime > 0)
            {
                this.updateState();
                burnItem = getStackInSlot(fuelSlot);
                if (getStackInSlot(fuelSlot).stackSize == 1)
                {
                    setInventorySlotContents(fuelSlot, null);
                }
                else
                {
                    decrStackSize(fuelSlot, 1);
                }
            }
        }
        lastTickBurning = isBurning;
    }
    public static int getItemBurnTime(ItemStack stack)
    {
        return TileEntityFurnace.getItemBurnTime(stack) / 4;
    }
}
