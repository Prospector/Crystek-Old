package crystekteam.crystek.laser;

import crystekteam.crystek.client.partciles.ParticleColored;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class LaserUtil
{
    @SideOnly(Side.CLIENT)
    public static void renderParticlesFromAToB(double startX, double startY, double startZ, double endX, double endY, double endZ, int particleAmount, float particleSize, float[] color, float ageMultiplier)
    {
        World world = Minecraft.getMinecraft().theWorld;

        if (Minecraft.getMinecraft().thePlayer.getDistance(startX, startY, startZ) <= 64 || Minecraft.getMinecraft().thePlayer.getDistance(endX, endY, endZ) <= 64)
        {
            double difX = startX - endX;
            double difY = startY - endY;
            double difZ = startZ - endZ;
            double distance = new Vec3d(startX, startY, startZ).distanceTo(new Vec3d(endX, endY, endZ));

            for (int times = 0; times < Math.max(particleAmount / 2, 1); times++)
            {
                for (double i = 0; i <= 1; i += 1 / (distance * particleAmount))
                {
                    ParticleColored fx = new ParticleColored(world, (difX * i) + endX + 0.5, (difY * i) + endY + 0.5, (difZ * i) + endZ + 0.5, particleSize, color[0], color[1], color[2], ageMultiplier);
                    Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                }
            }
        }
    }
}
