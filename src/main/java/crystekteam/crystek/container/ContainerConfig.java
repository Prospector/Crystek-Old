package crystekteam.crystek.container;

import crystekteam.crystek.core.Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Gigabit101 on 23/01/2017.
 */
public class ContainerConfig extends Container
{
    EntityPlayer player;
    Machine machine;

    public ContainerConfig(EntityPlayer player, Machine machine)
    {
        this.player = player;
        this.machine = machine;
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
