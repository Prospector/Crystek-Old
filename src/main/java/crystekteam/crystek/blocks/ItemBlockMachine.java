package crystekteam.crystek.blocks;

import net.minecraft.block.Block;
import reborncore.common.itemblock.ItemBlockBase;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class ItemBlockMachine extends ItemBlockBase
{
    public ItemBlockMachine(Block block)
    {
        super(block, block, new String[]{});
    }

//    @Override
//    public String getUnlocalizedName(ItemStack stack)
//    {
//        int meta = stack.getItemDamage();
//        String name = "tile." + Crystek.MOD_ID.toLowerCase() + "." +  MachinesInit.getMachineList().get(meta).getName();
//        return name;
//    }
}
