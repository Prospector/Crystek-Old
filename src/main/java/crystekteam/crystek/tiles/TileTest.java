package crystekteam.crystek.tiles;

import crystekteam.crystek.container.ContainerCrystek;
import crystekteam.crystek.guis.GuiCrystek;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class TileTest extends TileCrystek
{
    public TileTest()
    {
        setContainer(new ContainerCrystek());
        setGuiContainer(new GuiCrystek(getContainer()));
        setGUI_ID(1);
    }
}
