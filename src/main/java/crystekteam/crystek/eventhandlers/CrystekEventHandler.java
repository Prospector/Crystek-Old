package crystekteam.crystek.eventhandlers;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Prospector on 27/06/16
 */
public class CrystekEventHandler
{
	@SubscribeEvent
	public void worldEventLoad(WorldEvent.Load event){
		event.getWorld().addEventListener(new CrystekWorldEventListener(event.getWorld()));
	}

}
