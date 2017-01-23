package crystekteam.crystek.network;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import reborncore.common.network.RegisterPacketEvent;

/**
 * Created by Gigabit101 on 23/01/2017.
 */
public class Packets
{
    @SubscribeEvent
    public static void loadPackets(RegisterPacketEvent event)
    {
        event.registerPacket(PacketFacing.class, Side.SERVER);
    }
}
