package crystekteam.crystek.blocks;

import crystekteam.crystek.lib.ModInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineFrame extends BlockBase {
    public BlockMachineFrame() {
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".machineframe");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }
}
