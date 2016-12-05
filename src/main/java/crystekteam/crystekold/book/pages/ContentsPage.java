package crystekteam.crystekold.book.pages;

import crystekteam.crystekold.book.PageCollection;
import crystekteam.crystekold.book.Reference;
import crystekteam.crystekold.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import reborncore.client.gui.GuiButtonItemTexture;

import java.awt.*;

/**
 * Created by Gigabit101 on 08/07/2016.
 */
public class ContentsPage extends TitledPage
{
    public ContentsPage(String name, PageCollection collection)
    {
        super(name, false, collection, Reference.CONTENTS_KEY, Color.white.getRGB());
//        this.drawTitle = false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui()
    {
        buttonList.clear();
        buttonList.add(new GuiButtonItemTexture(0, getXMin() + 20, getYMin() + 20, 0, 46, 100, 20,
                new ItemStack(ModBlocks.coalGen), Reference.pageNames.GETTINGSTARTED_PAGE, ttl(Reference.GETTINGSTARTED_KEY)));
    }

    @Override
    public void renderBackgroundLayer(Minecraft minecraft, int offsetX, int offsetY, int mouseX, int mouseY)
    {
        super.renderBackgroundLayer(minecraft, offsetX, offsetY, mouseX, mouseY);
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
            collection.changeActivePage(Reference.pageNames.GETTINGSTARTED_PAGE);
    }
}
