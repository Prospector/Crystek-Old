package crystekteam.crystek.machines;

import crystekteam.crystek.api.CrystekAPI;
import crystekteam.crystek.api.recipe.RecipeFluidizer;
import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class MachineFluidizer extends Machine {
	@Override
	public int invSize() {
		return 2;
	}

	@Override
	public int guiID() {
		return 9;
	}

	@Override
	public String getName() {
		return "fluidizer";
	}

	@Override
	public int getTankSize() {
		return 4000;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		builder.drawProgressBar(gui, this.getProgress(), this.getMaxProgress(), 75, 50, mouseX, mouseY, GuiCrystek.Layer.FOREGROUND);
		builder.drawTeslaEnergyBar(gui, 9, 18, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX, mouseY, layer);
	}

	@Override
	public void update() {
		super.update();
		if (isBurning() && canWork()) {
			addProgress();
			updateState(true);
			if (getProgress() % 3 == 0) {
				getTeslaContainer().takePower(5, false);
			}
			if (getProgress() >= getMaxProgress()) {
				updateState(false);
				makeFluid();
				resetProgress();
			}
		}
		if (getInv().getStackInSlot(0) == ItemStack.EMPTY) {
			updateState(false);
			resetProgress();
		}
	}

	public void makeFluid() {
		if (!world.isRemote) {
			if (this.canWork()) {
				ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getInv().getStackInSlot(0));

				if (getInv().getStackInSlot(1) == ItemStack.EMPTY) {
					getInv().insertItem(1, itemstack.copy(), false);
				} else if (getInv().getStackInSlot(1).isItemEqual(itemstack)) {
					getInv().getStackInSlot(1).setCount(getInv().getStackInSlot(1).getCount() + itemstack.getCount());
				}
				if (getInv().getStackInSlot(0).getCount() > 1) {
					getInv().extractItem(0, 1, false);
				} else {
					getInv().setStackInSlot(0, ItemStack.EMPTY);
				}
			}
		}
	}

	public boolean canWork() {
		if (!getTank().isFull()) {
			for (RecipeFluidizer f : CrystekAPI.FLUIDIZER_RECIPES)
				if (getTank().canFillFluidType(f.getOutput()))
					return true;
		}
		return false;
	}

	public boolean isBurning() {
		return getTeslaContainer().getStoredPower() > 5;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(new SlotItemHandler(getInv(), 0, 55, 45));
		slots.add(new SlotTeslaCharge(getInv(), 1, 8, 72));
		return slots;
	}

	@Override
	public long maxCapacity() {
		return 1000;
	}

	@Override
	public long maxInput() {
		return 100;
	}

	@Override
	public long maxOutput() {
		return 0;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.CONSUMER;
	}
}
