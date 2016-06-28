package crystekteam.crystek.tiles.machines;

import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.api.recipe.RecipeGrinder;
import crystekteam.crystek.items.misc.ItemGrindingBlade;
import crystekteam.crystek.tiles.prefab.TileMachine;
import crystekteam.crystek.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * Created by Gigabit101 on 16/06/2016.
 */
public class TileGrinder extends TileMachine
{
	int ORE_SLOT = 0;
	int OUTPUT_SLOT = 1;
	int GRINDING_BLADE_SLOT = 2;
	long cost = 40;

	public TileGrinder()
	{
		super(3, "grinder", 64, 10000, 50, 50, 0, 100);
	}

	@Override public void update()
	{
		if (canWork())
		{
			addProgress();
			if (getProgress() >= 100)
				work();

			if (this.getProgress() == 1)
				this.updateState();
			else if (this.getProgress() == 0)
				this.updateState();

		}
		if (getStackInSlot(ORE_SLOT) == null)
		{
			resetProgress();
			this.updateState();
		}
		syncWithAll();
	}

	public void work()
	{
		usePower(cost);
		if (getStackInSlot(OUTPUT_SLOT) == null)
		{
			setInventorySlotContents(this.OUTPUT_SLOT, getOutput());
			decrStackSize(ORE_SLOT, 1);
		} else if (ItemUtils.isItemEqual(getStackInSlot(this.OUTPUT_SLOT), getOutput(), true, true)
				&& getStackInSlot(OUTPUT_SLOT).stackSize != 64)
		{
			getStackInSlot(OUTPUT_SLOT).stackSize += getOutput().stackSize;
			decrStackSize(ORE_SLOT, 1);
			ItemStack grindingBlade = getStackInSlot(GRINDING_BLADE_SLOT);
			if (grindingBlade.getMaxDamage() != 0)
			{
				System.out.println("BLADE WITH META: "+grindingBlade.getMetadata()+ " AND MAX: "+grindingBlade.getMaxDamage());
				if (grindingBlade.getMetadata() == grindingBlade.getMaxDamage())
				{
					removeStackFromSlot(GRINDING_BLADE_SLOT);
				} else
				{
					getStackInSlot(GRINDING_BLADE_SLOT).attemptDamageItem(1, new Random());
				}
			}
		}

		if (inv.hasChanged)
		{
			resetProgress();
		}
		syncWithAll();
	}

	public boolean canWork()
	{
		syncWithAll();
		if (getOutput() != null && getStoredPower() >= cost && getStackInSlot(GRINDING_BLADE_SLOT) != null
				&& getStackInSlot(GRINDING_BLADE_SLOT).getItem() instanceof ItemGrindingBlade)
		{
			return true;
		}
		return false;
	}

	//Gets the ItemStack the recipe will craft
	public ItemStack getOutput()
	{
		if (getStackInSlot(ORE_SLOT) != null)
		{
			ItemStack input = getStackInSlot(this.ORE_SLOT);
			for (RecipeGrinder recipe : CrystekApi.smasherRecipes)
			{
				if (recipe.matches(input) || recipe.getOutput().getItem() == Item.getItemFromBlock(getBlockType()))
				{
					ItemStack output = recipe.getOutput().copy();
					return output;
				}
			}
		}
		return null;
	}
}
