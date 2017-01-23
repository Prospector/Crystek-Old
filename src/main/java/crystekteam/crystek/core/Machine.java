package crystekteam.crystek.core;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.blocks.BlockCrystekMachine;
import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.guis.CrystekBuilder;
import crystekteam.crystek.guis.GuiCrystek;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import reborncore.common.IWrenchable;
import reborncore.common.util.Tank;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 17/01/2017.
 */
public abstract class Machine extends TileEntity implements ITickable, IWrenchable {
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
	public CrystekBuilder builder = new CrystekBuilder();
	/**
	 * Tesla
	 */
	public TeslaContainerAdvanced teslaContainer = new TeslaContainerAdvanced(maxCapacity(), maxInput(), maxOutput());
	/**
	 * Progress
	 */
	public int progress = 0;
	public int maxProgress = 100;
	boolean requireUpdate = false;

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
	public NBTTagCompound writeToNBTWithoutCoords(NBTTagCompound compound) {
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		writeToNBTWithoutCoords(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		readFromNBTWithoutCoords(compound);
	}

	public void readFromNBTWithoutCoords(NBTTagCompound compound) {
		if (hasInv()) {
			inv.deserializeNBT(compound);
		}
		if (hasTank()) {
			tank.readFromNBT(compound);
		}
		if (teslaType() != EnumTeslaType.NULL) {
			this.teslaContainer.setPower(compound.getLong("StoredPower"));
		}
		progress = compound.getInteger("Progress");
		maxProgress = compound.getInteger("MaxProgress");
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		if (oldState.getBlock() != newSate.getBlock()) {
			return true;
		}
		return false;
	}

	public void updateState(boolean active) {
		IBlockState BlockStateContainer = world.getBlockState(pos);
		if (BlockStateContainer.getBlock() instanceof BlockCrystekMachine) {
			BlockCrystekMachine blockBase = (BlockCrystekMachine) BlockStateContainer.getBlock();
			blockBase.setActive(active, world, pos);
		}
	}

	@SideOnly(Side.CLIENT)
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, GuiCrystek gui, GuiCrystek.Layer layer) {
		builder.drawDefaultBackground(gui, guiLeft, guiTop, xSize, ySize);
		builder.drawPlayerSlots(gui, guiLeft + xSize / 2, guiTop + 93, true);
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
	public void update() {
		if (world.isRemote) {
			return;
		}
		sync();
	}

	public void sync() {
		if (requireUpdate || getTeslaContainer().shouldUpdate()) {
			requireUpdate = false;
			getTeslaContainer().setShouldUpdate(false);
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
		}
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
	public boolean hasCapability(Capability<?> capability,
	                             @Nullable
		                             EnumFacing facing) {
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
		if (teslaType() == EnumTeslaType.STORAGE && (capability == TeslaCapabilities.CAPABILITY_CONSUMER && capability == TeslaCapabilities.CAPABILITY_PRODUCER) || capability == TeslaCapabilities.CAPABILITY_HOLDER) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability,
	                           @Nullable
		                           EnumFacing facing) {
		if (hasInv() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getInv());
		}
		if (hasTank() && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(getTank());
		}
		if (teslaType() != EnumTeslaType.NULL) {
			return (T) getTeslaContainer();
		}
		return super.getCapability(capability, facing);
	}

	public void addProgress() {
		progress++;
		requireUpdate = true;
	}

	public void resetProgress() {
		progress = 0;
		requireUpdate = true;
	}

	public int getProgress() {
		return progress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	/**
	 * IWrenchable
	 */

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return entityPlayer.isSneaking();
	}

	@Override
	public EnumFacing getFacing() {
		return null;
	}

	@Override
	public void setFacing(EnumFacing enumFacing) {}

	@Override
	public float getWrenchDropRate() {
		return 0F;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return getDropWithNBT();
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, EnumFacing enumFacing) {
		return false;
	}

	public ItemStack getDropWithNBT() {
		NBTTagCompound tileEntity = new NBTTagCompound();
		ItemStack dropStack = new ItemStack(this.getBlockType(), 1);
		writeToNBTWithoutCoords(tileEntity);
		dropStack.setTagCompound(new NBTTagCompound());
		dropStack.getTagCompound().setTag("tileEntity", tileEntity);
		return dropStack;
	}

    //Block
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

    public boolean isFullCube(IBlockState state)
    {
        return true;
    }

    public boolean isFullBlock(IBlockState state)
    {
        return true;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return true;
    }
}
