package crystekteam.crystek.client.partciles;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by modmuss50 on 29/06/2016.
 */
public class PartcileEngine {

    public static void renderEnergyBeam(World world, BlockPos pos, BlockPos pos2){
        for (int i = 0; i < 30; i++) {
            ParticleEnergyBeam energyBeam = new ParticleEnergyBeam(world, pos, pos2);
            Minecraft.getMinecraft().effectRenderer.addEffect(energyBeam);
        }
    }


}
