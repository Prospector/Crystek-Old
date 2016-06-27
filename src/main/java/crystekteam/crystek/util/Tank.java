package crystekteam.crystek.util;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;

public class Tank extends FluidTank
{
	public TileBase tile;

	public Tank(int capacity, TileBase tile)
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
}
