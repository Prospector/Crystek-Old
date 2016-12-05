package crystekteam.crystekold.api;

import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class FluidFuelHandler
{
    FluidStack fluidStack;
    long amount;
    long output;

    public FluidFuelHandler(FluidStack fluidStack, long amount, long output)
    {
        this.fluidStack = fluidStack;
        this.amount = amount;
        this.output = output;
    }

    public boolean fluidMatches(FluidStack fluidStack)
    {
        FluidStack fluidStackCopy = this.fluidStack.copy();
        return fluidStack.isFluidEqual(fluidStackCopy);
    }

    public FluidStack getFluidStack()
    {
        return fluidStack;
    }

    public long getAmount()
    {
        return amount;
    }

    public long getOutput()
    {
        return output;
    }
}
