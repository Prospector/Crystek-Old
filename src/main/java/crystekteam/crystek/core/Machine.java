package crystekteam.crystek.core;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.guis.CrystekBuilder;
import crystekteam.crystek.guis.GuiCrystek;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import reborncore.common.util.Tank;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 17/01/2017.
 */
public abstract class Machine extends TileEntity implements ITickable {
	/**
	 * Inv
	 */
	public ItemStackHandler inv = new ItemStackHandler(invSize());
	/**
	 * Tank
	 */
	public Tank tank = new Tank(getName(), getTankSize(), this);
	/**
	 * GUI
	 */
	public int xSize = 176;
	public int ySize = 176;
	public CrystekBuilder builder = new CrystekBuilder();
	/**
	 * Tesla
	 */
	public TeslaContainerAdvanced teslaContainer = new TeslaContainerAdvanced(maxCapacity(), maxInput(), maxOutput());

	public abstract int invSize();

	public abstract int guiID();

	public abstract String getName();

	public boolean hasInv() {
		if (invSize() != 0) {
			return true;
		}
		return false;
	}

	public ItemStackHandler getInv() {
		return inv;
	}

	public void openGui(EntityPlayer player, Machine machine) {
		player.openGui(Crystek.MOD_CL, machine.guiID(), machine.world, machine.pos.getX(), machine.pos.getY(), machine.pos.getZ());
	}

	public abstract int getTankSize();

	public Tank getTank() {
		return tank;
	}

	public boolean hasTank() {
		if (getTank().getCapacity() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * NBT
	 */
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if (hasInv()) {
			compound = super.writeToNBT(compound);
			compound.merge(inv.serializeNBT());
		}
		if (hasTank()) {
			compound = super.writeToNBT(compound);
			tank.writeToNBT(compound);
		}
		if (teslaType() != EnumTeslaType.NULL) {
            compound.setLong("StoredPower", this.teslaContainer.getStoredPower());
		}
        compound.setInteger("Progress", this.progress);
        compound.setInteger("MaxProgress", this.maxProgress);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (hasInv()) {
			inv.deserializeNBT(compound);
		}
		if (hasTank()) {
			tank.readFromNBT(compound);
		}
		if (teslaType() != EnumTeslaType.NULL) {
            this.teslaContainer.setPower(compound.getLong("StoredPower"));
		}
        compound.setInteger("Progress", this.progress);
        compound.setInteger("MaxProgress", this.maxProgress);
	}

    @Override
    public SPacketUpdateTileEntity getUpdatePacket ()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket (NetworkManager net, SPacketUpdateTileEntity packet)
    {
        super.onDataPacket(net, packet);
        this.readFromNBT(packet.getNbtCompound());
    }

    @SideOnly(Side.CLIENT)
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, GuiCrystek gui, GuiCrystek.Layer layer) {
		builder.drawDefaultBackground(gui, guiLeft, guiTop, xSize, ySize);
		builder.drawPlayerSlots(gui, guiLeft + xSize / 2, guiTop + 80, true);
		if (getSlots() != null) {
			for (Slot s : getSlots()) {
				if (s instanceof SlotTeslaCharge) {
					builder.drawSlot(gui, guiLeft + s.xPos - 1, guiTop + s.yPos - 1, CrystekBuilder.SlotSprite.BATTERY);
				} else {
					builder.drawSlot(gui, guiLeft + s.xPos - 1, guiTop + s.yPos - 1);
				}
			}
		}
	}

	/**
	 * Container
	 */

	@Nullable
	public abstract List<Slot> getSlots();

	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {}

	/**
	 * Tile
	 */
	@Override
	public void update()
    {
        sync();
    }

    public void sync()
    {
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

	public abstract long maxCapacity();

	public abstract long maxInput();

	public abstract long maxOutput();

	public abstract EnumTeslaType teslaType();

	public TeslaContainerAdvanced getTeslaContainer() {
		return teslaContainer;
	}

	/**
	 * Capability
	 */
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (hasInv() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		if (hasTank() && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
		if (teslaType() == EnumTeslaType.GENERATOR && capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER) {
			return true;
		}
		if (teslaType() == EnumTeslaType.CONSUMER && capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_HOLDER) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (hasInv() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getInv());
		}
		if (hasTank() && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(getTank());
		}
		if (teslaType() != EnumTeslaType.NULL)
		{
			return (T) getTeslaContainer();
		}
		return super.getCapability(capability, facing);
	}
    /**
     * Progress
     */
    public int progress = 0;
    public int maxProgress = 100;

    public int getProgress()
    {
        return progress;
    }

    public int getMaxProgress()
    {
        return maxProgress;
    }
}
