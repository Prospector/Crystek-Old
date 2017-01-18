package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.init.CrystekBlocks;
import crystekteam.crystek.init.MachinesInit;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
