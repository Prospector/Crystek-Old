package crystekteam.crystek.tiles;

import crystekteam.crystek.tiles.prefab.TileTeslaStorage;

/**
 * Created by Gigabit101 on 31/05/2016.
 */
public class TileTeslaCell extends TileTeslaStorage
{
    int CHARGE_SLOT = 0;
    int DISCHARGE_SLOT = 1;

    public TileTeslaCell()
    {
        super(2, "teslacell", 1, 50000, 50, 50, 0);
    }

    @Override
    public void update()
    {
        handleChargeSlots(CHARGE_SLOT, true, DISCHARGE_SLOT, true);
    }
}
