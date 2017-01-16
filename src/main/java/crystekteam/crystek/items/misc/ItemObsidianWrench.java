package crystekteam.crystek.items.misc;

import crystekteam.crystek.items.ItemCrystek;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.IWrenchable;

/**
 * Created by Prospector
 */
public class ItemObsidianWrench extends ItemCrystek {

	public ItemObsidianWrench() {
		super("obsidian_wrench");
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.isSneaking() && world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof IWrenchable) {
			TileEntity tileEntity = world.getTileEntity(pos);
			IWrenchable wrenchable = (IWrenchable) tileEntity;
			ItemStack returnstack = wrenchable.getWrenchDrop(player);
			if (wrenchable.wrenchCanRemove(player) && returnstack != null) {
				world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), returnstack));
				world.removeTileEntity(pos);
				world.setBlockToAir(pos);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}
}
