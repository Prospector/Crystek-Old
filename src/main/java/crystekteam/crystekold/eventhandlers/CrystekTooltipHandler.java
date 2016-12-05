package crystekteam.crystekold.eventhandlers;

import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by McKeever on 29-Oct-16.
 */
public class CrystekTooltipHandler {

    @SubscribeEvent
    public static void onToolTipRender(RenderTooltipEvent.PostText event) {
        event.setCanceled(true);

    }
}
