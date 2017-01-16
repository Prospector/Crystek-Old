package crystekteam.crystek.core;

import crystekteam.crystek.Crystek;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import reborncore.client.guibuilder.GuiBuilder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class Machine
{
    @Nullable int guiID;
    String name;
    @Nullable TileEntity tileEntity;

    public Machine(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public TileEntity getTileEntity()
    {
        return this.tileEntity;
    }

    public int getGuiID()
    {
        return guiID;
    }

    public void setTileEntity(@Nullable TileEntity tileEntity)
    {
        this.tileEntity = tileEntity;
    }

    public void setGuiID(@Nullable int guiID)
    {
        this.guiID = guiID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void openGui(EntityPlayer player)
    {
        player.openGui(Crystek.MOD_CL, getGuiID(), getWorld(), getPos().getX(), getPos().getY(), getPos().getZ());
    }

    public World getWorld()
    {
        return getTileEntity().getWorld();
    }

    public BlockPos getPos()
    {
        return getTileEntity().getPos();
    }

    /**
     * TILE
     */
    public void update(){}

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        if(inv.getSlots() != 0)
        {
            compound.merge(inv.serializeNBT());
        }
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        inv.deserializeNBT(compound);
    }

    /**
     * GUI
     */
    @SideOnly(Side.CLIENT)
    public GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);

    @SideOnly(Side.CLIENT)
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, GuiContainer gui)
    {
        builder.drawDefaultBackground(gui, guiLeft, guiTop, xSize, ySize);
        builder.drawPlayerSlots(gui, guiLeft + xSize / 2, guiTop + 80, true);
        if(getSlots() != null)
        {
            for(Slot s: getSlots())
            {
                builder.drawSlot(gui, guiLeft + s.xPos -1, guiTop + s.yPos -1);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui)
    {

    }

    /**
     * Container
     */
    public List<Slot> slots = new ArrayList<Slot>();

    public Slot addSlotToContainer(ItemStackHandler inv, int ID, int x, int y)
    {
        Slot s = new SlotItemHandler(inv, ID, x, y);
        if(!getSlots().contains(s))
        {
            getSlots().add(s);
        }
        return s;
    }

    public List<Slot> getSlots()
    {
        return slots;
    }

    /**
     * Inv
     */
    ItemStackHandler inv = new StackHandler(getInvSize());
    int invSize = 0;

    public boolean hasInv()
    {
        if(getInvSize() != 0)
        {
            return true;
        }
        return false;
    }

    public ItemStackHandler getInv()
    {
        return inv;
    }

    class StackHandler extends ItemStackHandler
    {
        StackHandler(int size)
        {
            super(size);
        }
    }

    public int getInvSize()
    {
        return invSize;
    }

    public void setInvSize(int invSize)
    {
        this.invSize = invSize;
    }

    /**
     * Tank
     */



    /**
     * Power
     */
}
