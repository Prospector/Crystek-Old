package crystekteam.crystek.machines;

import crystekteam.crystek.container.slots.SlotItemHandlerOutput;
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
 * Created by Gigabit101 on 16/01/2017.
 */
public class MachineFurnace extends Machine {
	@Override
	public int invSize() {
		return 3;
	}

	@Override
	public int guiID() {
		return 0;
	}

	@Override
	public String getName() {
		return "furnace";
	}

	@Override
	public int getTankSize() {
		return 0;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(new SlotItemHandler(getInv(), 0, 55, 45));
		slots.add(new SlotItemHandlerOutput(getInv(), 1, 105, 45));
		slots.add(new SlotTeslaCharge(getInv(), 2, 8, 72));
		return slots;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		builder.drawProgressBar(gui, this.getProgress(), this.getMaxProgress(), 75, 50, mouseX, mouseY, GuiCrystek.Layer.FOREGROUND);
		builder.drawTeslaEnergyBar(gui, 9, 18, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX, mouseY, layer);
	}

	@Override
	public long maxCapacity() {
		return 100000;
	}

	@Override
	public long maxInput() {
		return 100;
	}

	@Override
	public long maxOutput() {
		return 100;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.CONSUMER;
	}

	@Override
	public void update() {
		super.update();
		if (isBurning() && canSmelt()) {
			addProgress();
			updateState(true);
			if (getProgress() % 10 == 0) {
				getTeslaContainer().takePower(5, false);
			}
			if (getProgress() >= getMaxProgress()) {
				updateState(false);
				cookItems();
				resetProgress();
			}
		}
		if (getInv().getStackInSlot(0) == ItemStack.EMPTY) {
			updateState(false);
			resetProgress();
		}
	}

	public void cookItems() {
		if (!world.isRemote) {
			if (this.canSmelt()) {
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

	public boolean canSmelt() {
		if (getInv().getStackInSlot(0) == ItemStack.EMPTY) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getInv().getStackInSlot(0));
			if (itemstack == ItemStack.EMPTY)
				return false;
			if (getInv().getStackInSlot(1) == ItemStack.EMPTY)
				return true;
			if (!getInv().getStackInSlot(1).isItemEqual(itemstack))
				return false;
			int result = getInv().getStackInSlot(1).getCount() + itemstack.getCount();
			return (result <= 64 && result <= itemstack.getMaxStackSize());
		}
	}

	public boolean isBurning() {
		return getTeslaContainer().getStoredPower() > 5;
	}
}
