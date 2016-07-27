package crystekteam.crystek.tiles;

import crystekteam.crystek.tiles.prefab.TileBase;

/**
 * Created by Gigabit101 on 28/07/2016.
 */
public class TileCrate extends TileBase
{
    public TileCrate()
    {
        super(27, "crate", 64, 0, 0, 0, 0, 0);
        this.hasTesla = false;
        this.hasInv = true;
        this.hasTank = false;
    }
}
