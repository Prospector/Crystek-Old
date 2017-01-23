package crystekteam.crystek.network;

import crystekteam.crystek.core.Machine;
import io.netty.buffer.ByteBuf;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import reborncore.common.network.ExtendedPacketBuffer;
import reborncore.common.network.INetworkPacket;

import java.io.IOException;

/**
 * Created by Gigabit101 on 23/01/2017.
 */
public class PacketFacing implements INetworkPacket<PacketFacing>
{
    private int facing;
    private BlockPos pos;

    public PacketFacing(BlockPos pos, EnumFacing facing)
    {
        this.pos = pos;
        this.facing = facing.getIndex();
    }

    public PacketFacing(){}

    @Override
    public void writeData(ExtendedPacketBuffer extendedPacketBuffer) throws IOException
    {
        extendedPacketBuffer.writeInt(facing);
        extendedPacketBuffer.writeBlockPos(pos);
    }

    @Override
    public void readData(ExtendedPacketBuffer extendedPacketBuffer) throws IOException
    {
        facing = extendedPacketBuffer.readInt();
        pos = extendedPacketBuffer.readBlockPos();
    }

    @Override
    public void processData(PacketFacing packetFacing, MessageContext messageContext)
    {
        World world = messageContext.getServerHandler().playerEntity.world;
        if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof Machine)
        {
            Machine machine = (Machine) world.getTileEntity(pos);
            machine.getInv().setStackInSlot(0, new ItemStack(Items.APPLE));
        }
    }
}
