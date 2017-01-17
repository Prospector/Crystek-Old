package crystekteam.crystek.container;

import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;
import reborncore.common.container.RebornContainer;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class ContainerCrystek extends RebornContainer
{
    public TileMachine machine;

    public ContainerCrystek(EntityPlayer player, TileMachine machine)
    {
        super();
        this.machine = machine;
        for (Slot s : machine.getMachine(machine).getSlots())
        {
            addSlotToContainer(new SlotItemHandler(machine.getInv(), s.getSlotIndex(), s.xPos, s.yPos));
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
