package crystekteam.crystek.container;

import crystekteam.crystek.tiles.TileCrystek;
import net.minecraft.entity.player.EntityPlayer;
import reborncore.common.container.RebornContainer;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class ContainerCrystek extends RebornContainer
{
    public ContainerCrystek(EntityPlayer player, TileCrystek tile)
    {
        drawPlayersInv(player);
    }

    public ContainerCrystek()
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
