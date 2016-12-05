package crystekteam.crystekold.network;

import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Created by Travis on 28/06/2016.
 */
public class AddDiscriminatorEvent extends Event
{
    public PacketHandler packetHandler;

    public AddDiscriminatorEvent(PacketHandler packetHandler)
    {
        this.packetHandler = packetHandler;
    }

    public PacketHandler getPacketHandler()
    {
        return packetHandler;
    }

    @Override
    public boolean isCancelable()
    {
        return false;
    }
}
