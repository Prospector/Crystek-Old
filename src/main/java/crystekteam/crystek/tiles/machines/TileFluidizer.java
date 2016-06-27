package crystekteam.crystek.tiles.machines;


import crystekteam.crystek.init.ModFluids;
import crystekteam.crystek.tiles.prefab.TileMachine;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class TileFluidizer extends TileMachine
{
    long cost = 40;

    public TileFluidizer()
    {
        super(4, "fluidizer", 64, 10000, 500, 500, 4000);
    }

    @Override
    public void update()
    {
        if(canWork())
        {
            addProgress();
            if(getProgress() >= 100) {
                work();
                resetProgress();
            }

            if(this.getProgress() == 1)
                this.updateState();
            else if(this.getProgress() == 0)
                this.updateState();
        }
    }

    public void work()
    {
        usePower(cost);
        if(tank.getFluid() == null)
        {
            tank.setFluid(new FluidStack(ModFluids.fluidTesla, 50));
        }
        else if (tank.getFluid().isFluidEqual(new FluidStack(ModFluids.fluidTesla, 50)))
        {
            tank.fill(new FluidStack(ModFluids.fluidTesla, 50), true);
        }
    }

    public boolean canWork()
    {
        if(getStoredPower() >= cost && getFluidAmount() != tank.getCapacity())
        {
            return true;
        }
        return false;
    }
}
