package crystekteam.crystek.core;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import reborncore.client.guibuilder.GuiBuilder;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class Machine
{
    @Deprecated
    @Nullable GuiContainer guiContainer;
    @Deprecated
    @Nullable Container container;


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

    @Deprecated
    public void setContainer(@Nullable Container container)
    {
        this.container = container;
    }

    @Deprecated
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

    //TILE
    public void update(){}

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {

    }

    //GUI
    public GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);

    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, GuiContainer gui)
    {
        builder.drawDefaultBackground(gui, guiLeft, guiTop, xSize, ySize);
        builder.drawPlayerSlots(gui, guiLeft + xSize / 2, guiTop + 80, true);
    }

    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui)
    {

    }


    //Container



    //Inv



    //Tank



    //Power
}
