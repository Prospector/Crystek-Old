package crystekteam.crystek.core;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Created by Gigabit101 on 21/01/2017.
 */
public class TeslaContainerAdvanced implements ITeslaHolder, ITeslaProducer, ITeslaConsumer, IEnergyStorage
{
    private long storedPower = 0;
    private long capacity = 0;
    private long maxInput = 0;
    private long maxOutput = 0;

    public TeslaContainerAdvanced(long capacity, long maxInput, long maxOutput, long storedPower)
    {
        this.storedPower = storedPower;
        this.capacity = capacity;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
    }

    public TeslaContainerAdvanced(long capacity, long maxInput, long maxOutput)
    {
        this.storedPower = 0;
        this.capacity = capacity;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
    }

    @Override
    public long getStoredPower()
    {
        return this.storedPower;
    }

    @Override
    public long getCapacity()
    {
        return this.capacity;
    }

    @Override
    public long takePower(long tesla, boolean simulated)
    {
        final long removedPower = Math.min(this.storedPower, Math.min(maxOutput, tesla));
        if (!simulated)
        {
            this.storedPower -= removedPower;
        }
        return removedPower;
    }

    protected void setPower (long power)
    {
        this.storedPower = power;
    }


    @Override
    public long givePower(long l, boolean b)
    {
        final long acceptedTesla = Math.min(this.getCapacity() - this.storedPower, Math.min(this.maxInput, l));

        if (!b)
            this.storedPower += acceptedTesla;

        return acceptedTesla;
    }

    /**
     * Converts from long to int in a same way. If the long is outside of the int scope, the
     * max int scope value will be used.
     *
     * @param power The power to convert.
     * @return An int safe version of the power.
     */
    public static int getIntPower (long power)
    {
        if (power < Integer.MIN_VALUE)
        {
            return Integer.MIN_VALUE;
        }
        if (power > Integer.MAX_VALUE)
        {
            return Integer.MAX_VALUE;
        }
        return (int) power;
    }

    /*
     * =========================== FORGE START ===========================
     */

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        return getIntPower(this.takePower(maxExtract, simulate));
    }

    @Override
    public int getEnergyStored()
    {
        return getIntPower(this.storedPower);
    }

    @Override
    public int getMaxEnergyStored()
    {
        return getIntPower(this.getCapacity());
    }

    @Override
    public boolean canExtract()
    {
        return true;
    }

    @Override
    public boolean canReceive()
    {
        return false;
    }
}
