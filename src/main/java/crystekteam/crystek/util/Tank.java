package crystekteam.crystek.util;

import crystekteam.crystek.network.PacketHandler;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class Tank extends FluidTank
{
	public TileEntity tile;
	private FluidStack lastBeforeUpdate = null;

	public Tank(int capacity, TileEntity tile)
	{
		super(capacity);
		this.tile = tile;
	}

	public boolean isEmpty()
	{
		return getFluid() == null || getFluid().amount <= 0;
	}

	public boolean isFull()
	{
		return getFluid() != null && getFluid().amount >= getCapacity();
	}

	public Fluid getFluidType()
	{
		return getFluid() != null ? getFluid().getFluid() : null;
	}

	public void compareAndUpdate()
	{
		if (tile == null || tile.getWorld().isRemote)
		{
			return;
		}
		FluidStack current = this.getFluid();
		if (current != null)
		{
			if (lastBeforeUpdate != null)
			{
				if (Math.abs(current.amount - lastBeforeUpdate.amount) >= 500)
				{
					PacketHandler.sendPacketToAllPlayers(tile.getUpdatePacket(), tile.getWorld());
					lastBeforeUpdate = current.copy();
				} else if (lastBeforeUpdate.amount < this.getCapacity() && current.amount == this.getCapacity()
						|| lastBeforeUpdate.amount == this.getCapacity() && current.amount < this.getCapacity())
				{
					PacketHandler.sendPacketToAllPlayers(tile.getUpdatePacket(), tile.getWorld());
					lastBeforeUpdate = current.copy();
				}
			} else
			{
				PacketHandler.sendPacketToAllPlayers(tile.getUpdatePacket(), tile.getWorld());
				lastBeforeUpdate = current.copy();
			}
		} else if (lastBeforeUpdate != null)
		{
			PacketHandler.sendPacketToAllPlayers(tile.getUpdatePacket(), tile.getWorld());
			lastBeforeUpdate = null;
		}
	}
}
