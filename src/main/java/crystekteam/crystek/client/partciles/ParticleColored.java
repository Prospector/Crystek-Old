package crystekteam.crystek.client.partciles;

import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Gigabit101 on 12/07/2016.
 */
@SideOnly(Side.CLIENT)
public class ParticleColored extends ParticleRedstone
{
    public ParticleColored(World world, double x, double y, double z, float size, float r, float g, float b, float ageMulti)
    {
        super(world, x, y, z, size, r, g, b);
        this.particleRed = ((float)(Math.random()*0.20000000298023224D)+0.8F)*r*((float)Math.random()*0.4F + 0.6F);
        this.particleMaxAge = (int)((8.0D/(Math.random()*0.8D+0.2D))*ageMulti);
    }
}
