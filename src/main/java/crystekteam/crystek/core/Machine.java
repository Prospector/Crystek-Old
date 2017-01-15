package crystekteam.crystek.core;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class Machine
{
    String name;
    @Nullable TileEntity tileEntity;
    @Nullable GuiContainer guiContainer;
    @Nullable Container container;
    @Nullable int guiID;

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

    public GuiContainer getGuiContainer()
    {
        return this.guiContainer;
    }

    public Container getContainer()
    {
        return container;
    }

    public int getGuiID()
    {
        return guiID;
    }

    public void setTileEntity(@Nullable TileEntity tileEntity)
    {
        this.tileEntity = tileEntity;
    }

    public void setContainer(@Nullable Container container)
    {
        this.container = container;
    }

    public void setGuiContainer(@Nullable GuiContainer guiContainer)
    {
        this.guiContainer = guiContainer;
    }

    public void setGuiID(@Nullable int guiID)
    {
        this.guiID = guiID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void update(){}

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {

    }
}
