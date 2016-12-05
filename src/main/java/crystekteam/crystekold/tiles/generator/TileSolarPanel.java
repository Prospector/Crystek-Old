package crystekteam.crystekold.tiles.generator;

import crystekteam.crystekold.config.ConfigAE;
import crystekteam.crystekold.tiles.prefab.TileGenerator;
import net.minecraft.util.EnumFacing;

/**
 * Created by Gigabit101 on 25/06/2016.
 */
public class TileSolarPanel extends TileGenerator
{
    public long baseoutput = ConfigAE.solarTick;

    public TileSolarPanel()
    {
        super(3, "solar", ConfigAE.solarPanelMaxUpgrades, 10000, 100, 100, 0, 0);
        this.hasInv = true;
        this.hasTank = false;
        this.hasTesla = true;
    }

    @Override
    public void update()
    {
        if (this.hasWorldObj() && !this.worldObj.provider.getHasNoSky() && this.worldObj.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.worldObj.isRaining() && this.worldObj.getSkylightSubtracted() == 0 && this.container.getStoredPower() != this.container.getCapacity())
        {
            generatePower(baseoutput);
//            handleUpgrades(0, baseoutput);
            updateState();
        }
        transferPowerTo(EnumFacing.DOWN);
    }
}
