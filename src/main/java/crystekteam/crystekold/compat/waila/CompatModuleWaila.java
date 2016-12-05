package crystekteam.crystekold.compat.waila;

import crystekteam.crystekold.tiles.prefab.TileBase;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * Created by Gigabit101 on 11/05/2016.
 */
public class CompatModuleWaila
{
    public void init(FMLInitializationEvent event)
    {
        FMLInterModComms.sendMessage("Waila", "register", getClass().getName() + ".callbackRegister");
    }

    public static void callbackRegister(IWailaRegistrar registar)
    {
        registar.registerBodyProvider(new WailaProviderAE(), TileBase.class);
    }
}
