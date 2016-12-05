package crystekteam.crystekold.compat;


import crystekteam.crystekold.compat.waila.CompatModuleWaila;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Gigabit101 on 11/05/2016.
 */
public class CompatHandler
{
    public static void init(FMLInitializationEvent event)
    {
        if (Loader.isModLoaded("Waila"))
        {
            new CompatModuleWaila().init(event);
        }
    }
}
