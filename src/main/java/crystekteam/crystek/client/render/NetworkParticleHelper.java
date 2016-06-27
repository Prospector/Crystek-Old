package crystekteam.crystek.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

/**
 * Created by Gigabit101 on 04/06/2016.
 */
//TODO
    @Deprecated
public class NetworkParticleHelper
{
    public static void runWifiFX(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, int age)
    {
        ParticleEnergyBeam wifi = new ParticleEnergyBeam(par1World, x, y, z, tx, ty, tz, red, green, blue, age);
        Minecraft.getMinecraft().effectRenderer.addEffect(wifi);
    }
}
