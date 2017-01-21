package crystekteam.crystek.machines;

import crystekteam.crystek.container.slots.SlotItemHandlerOutput;
import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
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
		slots.add(new SlotItemHandler(getInv(), 0, 50, 35));
		slots.add(new SlotItemHandlerOutput(getInv(), 1, 105, 35));
		slots.add(new SlotTeslaCharge(getInv(), 2, 8, 60));
		return slots;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		builder.drawProgressBar(gui, this.getProgress(), this.getMaxProgress(), 75, 35, mouseX, mouseY, GuiCrystek.Layer.FOREGROUND);
		builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX, mouseY, layer);
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
		return 0;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.CONSUMER;
	}

    @Override
	public void update() {
		if (getInv().getStackInSlot(0) != ItemStack.EMPTY) {
			if (getProgress() != getMaxProgress()) {
				progress++;
			} else {
				progress = 0;
			}
		}
		sync();
	}
}
