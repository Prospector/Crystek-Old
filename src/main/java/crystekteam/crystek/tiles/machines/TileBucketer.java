package crystekteam.crystek.tiles.machines;


import crystekteam.crystek.tiles.prefab.TileMachine;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class TileBucketer extends TileMachine
{
    long cost = 40;
    int IN_SLOT = 0;
    int OUT_SLOT = 1;

    public TileBucketer()
    {
        super(6, "bucketer", 64, 10000, 500, 500, 4000, 100);
    }

    @Override
    public void update()
    {
        if(canWork())
        {
            //TODO
        }
        handleChargeSlots(0, false, 2, true);
    }

    public void work()
    {

    }

    public boolean canWork()
    {
        if(getStackInSlot(IN_SLOT) != null && tank.getFluid() != null)
        {
            return true;
        }
        return false;
    }
}
