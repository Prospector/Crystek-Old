package crystekteam.crystek.guis;

import crystekteam.crystek.Crystek;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;
import reborncore.client.guibuilder.GuiBuilder;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class CrystekGuiBuilder extends GuiBuilder
{
    public static final ResourceLocation resourceLocation = new ResourceLocation(Crystek.MOD_ID.toLowerCase() + ":" + "textures/gui/builder.png");

    public CrystekGuiBuilder()
    {
        super(resourceLocation);
    }

    public void drawTankForground(GuiScreen gui, FluidTank tank, int x, int y, float zLevel, int width, int height, int mouseX, int mouseY, int guiLeft, int guiTop)
    {
        drawTank(gui, tank, x, y, zLevel, width, height, mouseX - guiLeft, mouseY - guiTop);
    }
}
