package crystekteam.crystekold.tiles;

import crystekteam.crystekold.config.ConfigAE;
import crystekteam.crystekold.tesla.TeslaUtils;
import crystekteam.crystekold.tiles.prefab.TileTeslaStorage;
import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 31/05/2016.
 */
public class TileTeslaCell extends TileTeslaStorage
{
    int CHARGE_SLOT = 0;
    int DISCHARGE_SLOT = 1;

    public TileTeslaCell()
    {
        super(6, "teslacell", 16, ConfigAE.teslacellBaseStorage, 50, 50, 0, 0);
        this.hasInv = true;
        this.hasTank = false;
        this.hasTesla = true;
    }

    @Override
    public void update()
    {
        handleChargeSlots(CHARGE_SLOT, true, DISCHARGE_SLOT, true);
        handleUpgrades(2, ConfigAE.teslacellBaseStorage);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return TeslaUtils.isPoweredItem(stack);
    }
}
