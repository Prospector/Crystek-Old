package crystekteam.crystek.tiles.machines;

import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.api.recipe.RecipeGrinder;
import crystekteam.crystek.items.misc.ItemGrindingBlade;
import crystekteam.crystek.tiles.prefab.TileMachine;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import reborncore.common.util.ItemUtils;

import java.util.Random;

/**
 * Created by Gigabit101 on 16/06/2016.
 */
public class TileGrinder extends TileMachine {
    int ORE_SLOT = 0;
    int OUTPUT_SLOT = 1;
    int GRINDING_BLADE_SLOT = 2;
    long cost = 40;

    public TileGrinder() {
        super(3, "grinder", 64, 10000, 50, 50, 0, 600);
        this.hasInv = true;
        this.hasTank = false;
        this.hasTesla = true;
    }

    @Override
    public void update() {
        if (getStackInSlot(GRINDING_BLADE_SLOT) != null && getStackInSlot(GRINDING_BLADE_SLOT)
                .getItem() instanceof ItemGrindingBlade) {
            ItemGrindingBlade grindingBladeItem = (ItemGrindingBlade) getStackInSlot(GRINDING_BLADE_SLOT).getItem();
            maxProgress = maxProgress - grindingBladeItem.speed * 100;
        }

        if (canWork()) {
            addProgress();
            if (getProgress() >= 100)
                work();

            if (this.getProgress() == 1)
                this.updateState();
            else if (this.getProgress() == 0)
                this.updateState();

        }
        if (getStackInSlot(ORE_SLOT) == null) {
            resetProgress();
            this.updateState();
        }
        syncWithAll();
    }

    public void work() {
        if (!world.isRemote) {
            usePower(cost);
            ItemStack grindingBlade = getStackInSlot(GRINDING_BLADE_SLOT);

            if (getStackInSlot(OUTPUT_SLOT) == null) {
                setInventorySlotContents(this.OUTPUT_SLOT, getOutput());
                decrStackSize(ORE_SLOT, 1);
                resetProgress();
            } else if (ItemUtils.isItemEqual(getStackInSlot(this.OUTPUT_SLOT), getOutput(), true, true)
                    && getStackInSlot(OUTPUT_SLOT).getCount() != 64) {
                getStackInSlot(OUTPUT_SLOT).setCount(getStackInSlot(OUTPUT_SLOT).getCount() + getOutput().getCount());
                decrStackSize(ORE_SLOT, 1);
                resetProgress();
            }
            if (grindingBlade.getMaxDamage() != 0) {
                if (grindingBlade.getMetadata() == grindingBlade.getMaxDamage()) {
                    removeStackFromSlot(GRINDING_BLADE_SLOT);
                } else {
                    getStackInSlot(GRINDING_BLADE_SLOT).attemptDamageItem(1, new Random());
                }
            }
            syncWithAll();
        }
    }

    public boolean canWork() {
        syncWithAll();
        if (getOutput() != null && getStoredPower() >= cost && getStackInSlot(GRINDING_BLADE_SLOT) != null
                && getStackInSlot(GRINDING_BLADE_SLOT).getItem() instanceof ItemGrindingBlade) {
            return true;
        }
        return false;
    }

    //Gets the ItemStack the recipe will craft
    public ItemStack getOutput() {
        if (getStackInSlot(ORE_SLOT) != null) {
            ItemStack input = getStackInSlot(this.ORE_SLOT);
            for (RecipeGrinder recipe : CrystekApi.grinderRecipes) {
                if (recipe.matches(input) || recipe.getOutput().getItem() == Item.getItemFromBlock(getBlockType())) {
                    ItemStack output = recipe.getOutput().copy();
                    return output;
                }
            }
        }
        return null;
    }
}
