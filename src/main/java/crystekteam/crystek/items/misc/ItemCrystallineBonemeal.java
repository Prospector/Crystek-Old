package crystekteam.crystek.items.misc;

import crystekteam.crystek.items.ItemCrystek;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Prospector
 */
public class ItemCrystallineBonemeal extends ItemCrystek {

	public ItemCrystallineBonemeal() {
		super("crystalline_bonemeal");
	}

	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player) {
		IBlockState iblockstate = worldIn.getBlockState(target);

		int hook = net.minecraftforge.event.ForgeEventFactory
			.onApplyBonemeal(player, worldIn, target, iblockstate, stack);
		if (hook != 0)
			return hook > 0;

		if (iblockstate.getBlock() instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) iblockstate.getBlock();

			if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)) {
				if (!worldIn.isRemote) {
					if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)) {
						igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
					}

					stack.setCount(stack.getCount() - 1);
				}

				return true;
			}
		}

		return false;
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		} else {
			if (applyBonemeal(stack, worldIn, pos, playerIn)) {
				for (int i = 0; i < 20; i++) {
					applyBonemeal(stack, worldIn, pos, playerIn);
				}

				if (!worldIn.isRemote) {
					worldIn.playEvent(2005, pos, 0);
				}

				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.PASS;
		}

	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.GRAY + "Fully Grows Crops");
	}

}
