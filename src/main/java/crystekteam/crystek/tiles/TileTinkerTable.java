package crystekteam.crystek.tiles;

import crystekteam.crystek.tiles.prefab.TileMachine;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class TileTinkerTable extends TileMachine
{
    public TileTinkerTable()
    {
        super(10, "tinkertable", 1, 10000, 512, 512, 1000, 100);
        this.hasInv = true;
        this.hasTank = true;
        this.hasTesla = true;
    }

    @Override
    public void update()
    {

    }
}
