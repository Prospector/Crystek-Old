package crystekteam.crystek.core;

import crystekteam.crystek.tiles.TileCrystek;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class Machine
{
    String name;
    @Nullable TileCrystek tileEntity;
    @Nullable GuiContainer guiContainer;

    public Machine(String name, TileCrystek tileEntity)
    {
        this.name = name;
        this.tileEntity = tileEntity;
        if(tileEntity != null && tileEntity.getGuiContainer() != null)
        {
            this.guiContainer = tileEntity.getGuiContainer();
        }
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
}
