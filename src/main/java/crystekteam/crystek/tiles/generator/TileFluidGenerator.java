package crystekteam.crystek.tiles.generator;

import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.api.FluidFuelHandler;
import crystekteam.crystek.tiles.prefab.TileGenerator;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Gigabit101 on 31/05/2016.
 */
public class TileFluidGenerator extends TileGenerator
{
    public int output;
    public boolean isBurning;
    public boolean lastTickBurning;

    public TileFluidGenerator()
    {
        super(3, "fluidgen", 16, 10000, 50, 50, 8000, 0);
        this.hasInv = true;
        this.hasTank = true;
        this.hasTesla = true;
    }

    @Override
    public void update()
    {
        if (world.isRemote)
        {
            return;
        }
        if (getStoredPower() < getMaxCapacity())
        {
            if (burnTime > 0)
            {
                burnTime--;
                generatePower(getOutput());
                isBurning = true;
            }
        } else
        {
            isBurning = false;
        }
        if (burnTime == 0)
        {
            this.updateState();
            burnTime = totalBurnTime = (int) getAmount();
            if (burnTime > 0)
            {
                this.updateState();
                tank.drain(1000, true);
            }
        }
        lastTickBurning = isBurning;
        for (final EnumFacing side : EnumFacing.values())
        {
            transferPowerTo(side);
        }
    }

    public long getOutput()
    {
        if (tank.getFluid() != null)
        {
            FluidStack fluid = tank.getFluid();
            for (FluidFuelHandler recipe : CrystekApi.fluidFuelHandlers)
            {
                if (recipe.fluidMatches(fluid))
                {
                    return recipe.getOutput();
                }
            }
        }
        return 0;
    }

    public long getAmount()
    {
        if (tank.getFluid() != null)
        {
            FluidStack fluid = tank.getFluid();
            for (FluidFuelHandler recipe : CrystekApi.fluidFuelHandlers)
            {
                if (recipe.fluidMatches(fluid))
                {
                    return recipe.getAmount();
                }
            }
        }
        return 0;
    }
}
