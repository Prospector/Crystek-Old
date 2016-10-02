package crystekteam.crystek.tiles.machines;

import crystekteam.crystek.blocks.machines.BlockAccelerator;
import crystekteam.crystek.config.ConfigAE;
import crystekteam.crystek.tiles.prefab.TileMachine;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

/**
 * Created by Gigabit101 on 12/07/2016.
 */
public class TileAccelerator extends TileMachine
{
    int range = ConfigAE.acceleratorRange;
    int secondsBetweenGrowthTicks = ConfigAE.acceleratorSecondsBetweenGrowthTicks;
    long cost = ConfigAE.acceleratorCost;

    public TileAccelerator()
    {
        super(0, "", 0, 100000, 500, 500, 0, 0);
        this.hasInv = false;
        this.hasTank = false;
        this.hasTesla = true;
    }

    @Override
    public void update()
    {
        if (getStoredPower() >= cost)
        {
            growCropsNearby(worldObj, pos, worldObj.getBlockState(pos));
        }
    }

    public void growCropsNearby(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            int xO = pos.getX();
            int yO = pos.getY();
            int zO = pos.getZ();

            for (int xD = -range; xD <= range; xD++)
            {
                for (int yD = -1; yD <= range; yD++)
                {
                    for (int zD = -range; zD <= range; zD++)
                    {
                        int x = xO + xD;
                        int y = yO + yD;
                        int z = zO + zD;
                        double distance = Math.sqrt(Math.pow(x - xO, 2) + Math.pow(y - yO, 2) + Math.pow(z - zO, 2));
                        distance -= range;
                        distance = Math.min(1D, distance);
                        double distanceCoefficient = 1D - (distance / range);

                        IBlockState cropState = world.getBlockState(new BlockPos(x, y, z));
                        Block cropBlock = cropState.getBlock();

                        if (cropBlock instanceof IPlantable || cropBlock instanceof IGrowable)
                        {
                            if (!(cropBlock instanceof BlockAccelerator))
                            {
                                world.scheduleBlockUpdate(new BlockPos(x, y, z), cropBlock, (int) (distanceCoefficient * (float) secondsBetweenGrowthTicks * 20F), 1);
                                cropBlock.updateTick(world, new BlockPos(x, y, z), cropState, world.rand);
                                usePower(cost);
                            }
                        }
                    }
                }
            }
        }
        world.scheduleBlockUpdate(pos, state.getBlock(), secondsBetweenGrowthTicks * 20, 1);
    }
}
