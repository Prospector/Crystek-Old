package crystekteam.crystek.book.pages;

import crystekteam.crystek.book.PageCollection;
import net.minecraft.client.Minecraft;

/**
 * Created by Gigabit101 on 08/07/2016.
 */
public class TitledPage extends BasePage
{
    public boolean drawTitle = true;
    private String title;
    private int colour;

    public TitledPage(String name, boolean showInMenue, PageCollection collection, String unlocalizedTitle, int colour)
    {
        super(name, showInMenue, collection);
        this.title = unlocalizedTitle;
        this.colour = colour;
    }

    @Override
    public void renderOverlayComponents(Minecraft minecraft, int offsetX, int offsetY, int mouseX, int mouseY)
    {
        if (title == null)
            title = INDEX_NAME;
        if (drawTitle)
            drawCenteredString(minecraft.fontRendererObj, ttl(title), offsetX + 70, offsetY + 10, colour);
    }
}
