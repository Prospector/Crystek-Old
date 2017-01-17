package crystekteam.crystek.core;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

    public Machine(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public int getGuiID()
    {
        return guiID;
    }

    public void setGuiID(@Nullable int guiID)
    {
        this.guiID = guiID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void openGui(EntityPlayer player, TileMachine tile)
    {
        player.openGui(Crystek.MOD_CL, tile.getMachine(tile).getGuiID(), tile.getWorld(), tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ());
    }

    /**
     * TILE
     */
    public void update(){}

    /**
     * GUI
     */
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

    public Slot addSlotToContainer(int ID, int x, int y)
    {
        Slot s = new SlotItemHandler(null, ID, x, y);
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
    int invSize = 0;

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

    int tankSize = 0;

    public int getTankSize()
    {
        return tankSize;
    }

    public void setTankSize(int tankSize)
    {
        this.tankSize = tankSize;
    }

    /**
     * Power
     */
}
