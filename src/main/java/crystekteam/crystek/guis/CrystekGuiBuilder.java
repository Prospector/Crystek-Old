package crystekteam.crystek.guis;

import crystekteam.crystek.Crystek;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidTank;
import reborncore.client.guibuilder.GuiBuilder;

import java.util.ArrayList;
import java.util.List;

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

    public void drawTeslaEnergyBar(GuiScreen gui, int x, int y, int energyStored, int maxEnergyStored, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);

        gui.drawTexturedModalRect(x, y, 0, 150, 14, 50);
        if (maxEnergyStored <= 0) {
            gui.drawTexturedModalRect(x + 1, y + 1, 14, 198, 12, 48);
            if (isInRect(x, y, 14, 50, mouseX, mouseY)) {
                List<String> list = new ArrayList<String>();
                list.add(TextFormatting.RED + "" + TextFormatting.BOLD + "" + TextFormatting.UNDERLINE + "ERROR: maxEnergyStored Out of Bounds - value=" + maxEnergyStored);
                list.add(TextFormatting.RED + "Report to https://github.com/CrystekTeam/Crystek/issues");
                list.add(TextFormatting.RED + "Describe how you got this error to show up in the issue");
                list.add(TextFormatting.GOLD + "Screenshot this tooltip and put it in the issue report");
                list.add(TextFormatting.GRAY + "ver:" + Crystek.MOD_VERSION + " energy:" + energyStored + "/" + maxEnergyStored);
                list.add(TextFormatting.GRAY + "gui:" + gui.getClass().toString());
                list.add(TextFormatting.GREEN + "Please verify you're running the latest version first!");
                net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX-100, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
                GlStateManager.disableLighting();
            }
        } else {

            int draw = (int) ((double) energyStored / (double) maxEnergyStored * (50));
            gui.drawTexturedModalRect(x + 1, y + 51 - draw, 14, 50 + 150 - draw, 12, draw);

            if (isInRect(x, y, 14, 50, mouseX, mouseY)) {
                List<String> list = new ArrayList<String>();
                list.add(energyStored + " / " + maxEnergyStored + " Tesla");
                net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
                GlStateManager.disableLighting();
            }
        }
    }

    public void drawBurnBar(GuiScreen gui, int burnTime, int totalBurnTime, int x, int y) {
        gui.mc.getTextureManager().bindTexture(resourceLocation);
        gui.drawTexturedModalRect(x, y, 42, 217, 13, 13);
        int j = (int) (12 - getScaledBurnTime(12, burnTime, totalBurnTime));
        if (j > 0) {
            gui.drawTexturedModalRect(x, y + j, 28, 217 + j, 13, 13 - j);
        }
    }

    public int getScaledBurnTime(int scale, int burnTime, int totalBurnTime) {
        return (int) (((float) burnTime / (float) totalBurnTime) * scale);
    }
}
