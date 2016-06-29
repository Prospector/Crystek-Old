package crystekteam.crystek.tiles.machines;


import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.api.recipe.RecipeCrystallizer;
import crystekteam.crystek.tiles.prefab.TileMachine;
import crystekteam.crystek.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Gigabit101 on 27/06/2016.
 */
public class TileCrystallizer extends TileMachine
{
    int INPUT_SLOT = 0;
    int OUTPUT_SLOT = 1;
    long cost = 20;

    public TileCrystallizer()
    {
        super(6, "crystallizer", 64, 10000, 500, 500, 4000, 100);
    }

    @Override
    public void update()
    {
        if(canWork())
        {
            addProgress();
            if(getProgress() >= getMaxProgress())
                work();

            if(this.getProgress() == 1)
                this.updateState();
            else if(this.getProgress() == 0)
                this.updateState();

        }
        if(getStackInSlot(INPUT_SLOT) == null)
        {
            resetProgress();
            this.updateState();
        }
        handleChargeSlots(0, false, 5, true);
    }

    public void work()
    {
        usePower(cost);
        if(getStackInSlot(OUTPUT_SLOT) == null)
        {
            setInventorySlotContents(this.OUTPUT_SLOT, getOutput());
            decrStackSize(INPUT_SLOT, 1);
            tank.drain(getDrainAmount(), true);
        }
        else if(ItemUtils.isItemEqual(getStackInSlot(this.OUTPUT_SLOT), getOutput(), true, true) && getStackInSlot(OUTPUT_SLOT).stackSize != 64)
        {
            getStackInSlot(OUTPUT_SLOT).stackSize += getOutput().stackSize;
            decrStackSize(INPUT_SLOT, 1);
            tank.drain(getDrainAmount(), true);
        }
        if(inv.hasChanged)
        {
            resetProgress();
        }
        syncWithAll();
    }

    public boolean canWork()
    {
        if(getOutput() != null && getStoredPower() >= cost)
        {
            return true;
        }
        return false;
    }

    //Gets the ItemStack the recipe will craft
    public ItemStack getOutput()
    {
        if(getStackInSlot(INPUT_SLOT) != null && tank.getFluid() != null)
        {
            ItemStack input = getStackInSlot(this.INPUT_SLOT);
            FluidStack fluid = tank.getFluid();
            for (RecipeCrystallizer recipe : CrystekApi.crystallizerRecipes)
            {
                if (recipe.matches(input, fluid) || recipe.getOutput().getItem() == Item.getItemFromBlock(getBlockType()))
                {
                    ItemStack output = recipe.getOutput().copy();
                    return output;
                }
            }
        }
        return null;
    }

    public int getDrainAmount()
    {
        if(getStackInSlot(INPUT_SLOT) != null && tank.getFluid() != null)
        {
            ItemStack input = getStackInSlot(this.INPUT_SLOT);
            FluidStack fluid = tank.getFluid();
            for (RecipeCrystallizer recipe : CrystekApi.crystallizerRecipes)
            {
                if (recipe.matches(input, fluid) || recipe.getOutput().getItem() == Item.getItemFromBlock(getBlockType()))
                {
                    return recipe.getFluidStack().amount;
                }
            }
        }
        return 0;
    }
}
