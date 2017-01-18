package crystekteam.crystek.container;

import crystekteam.crystek.core.Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;
import reborncore.common.container.RebornContainer;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class ContainerCrystek extends RebornContainer
{
    public Machine machine;

    public ContainerCrystek(EntityPlayer player, Machine machine)
    {
        super();
        this.machine = machine;
        if(machine.getSlots() != null)
        {
            for (Slot s : machine.getSlots())
            {
                addSlotToContainer(new SlotItemHandler(machine.getInv(), s.getSlotIndex(), s.xPos, s.yPos));
            }
        }
        drawPlayersInv(player);
        drawPlayersHotBar(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}
