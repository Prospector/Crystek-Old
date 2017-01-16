package crystekteam.crystek.container;

import crystekteam.crystek.core.Machine;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.entity.player.EntityPlayer;
import reborncore.common.container.RebornContainer;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class ContainerCrystek extends RebornContainer
{
    public Machine machine;

    public ContainerCrystek(EntityPlayer player, Machine machine)
    {
        drawPlayersInv(player);
        drawPlayersHotBar(player);
        this.machine = machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
