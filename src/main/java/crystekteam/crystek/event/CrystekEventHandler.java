package crystekteam.crystek.event;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by Prospector
 */
public class CrystekEventHandler {
	@SubscribeEvent
	public void worldEventLoad(WorldEvent.Load event) {
		event.getWorld().addEventListener(new CrystekWorldEventListener(event.getWorld()));
	}

	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event){

	}
}
