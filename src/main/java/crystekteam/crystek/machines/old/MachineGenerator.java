package crystekteam.crystek.machines.old;

import crystekteam.crystek.configs.ConfigCrystek;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachineGenerator extends Machine {
	int burnTime = 0;
	boolean isBurning = false;

	public static int getItemBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) / 4;
	}

	@Override
	public int invSize() {
		return 1;
	}

	@Override
	public int guiID() {
		return 1;
	}

	@Override
	public String getName() {
		return "coalgenerator";
	}

	@Override
	public int getTankSize() {
		return 0;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(new SlotItemHandler(getInv(), 0, 80, 50));
		return slots;
	}

	@Override
	public long maxCapacity() {
		return ConfigCrystek.GENERATOR_MAX_CAPACITY;
	}

	@Override
	public long maxInput() {
		return ConfigCrystek.GENERATOR_GENERATION;
	}

	@Override
	public long maxOutput() {
		return ConfigCrystek.GENERATOR_MAX_OUTPUT;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.PRODUCER;
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		builder.drawProgressBar(gui, this.getProgress(), this.getMaxProgress(), 75, 35, mouseX, mouseY, GuiCrystek.Layer.FOREGROUND);
		builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), energyGain, mouseX, mouseY, layer);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new MachineGenerator();
	}

	@Override
	public void update() {
		super.update();
		if (world.isRemote) {
			return;
		}
		if (getTeslaContainer().getStoredPower() < maxCapacity()) {
			if (burnTime > 0) {
				burnTime--;
				getTeslaContainer().givePower(maxOutput(), false);
				isBurning = true;
			} else {
				isBurning = false;
			}
			if (burnTime == 0) {
				this.updateState(false);
				burnTime = getItemBurnTime(getInv().getStackInSlot(0));
				if (burnTime > 0) {
					this.updateState(true);
					getInv().extractItem(0, 1, false);
				}
			}
		}
//		sync();
	}
}
