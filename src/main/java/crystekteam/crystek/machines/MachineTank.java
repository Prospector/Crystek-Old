package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 17/01/2017.
 */
public class MachineTank extends Machine {
	@Override
	public int invSize() {
		return 0;
	}

	@Override
	public int guiID() {
		return 2;
	}

	@Override
	public String getName() {
		return "tank";
	}

	@Override
	public int getTankSize() {
		return 16000;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		getTank().setFluid(new FluidStack(FluidRegistry.LAVA, 16000));
		builder.drawTankForground(gui, getTank(), 80, 12, 10, 16, 60, mouseX, mouseY, guiLeft, guiTop);
	}

	@Override
	public long maxCapacity() {
		return 0;
	}

	@Override
	public long maxInput() {
		return 0;
	}

	@Override
	public long maxOutput() {
		return 0;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.NULL;
	}
}
