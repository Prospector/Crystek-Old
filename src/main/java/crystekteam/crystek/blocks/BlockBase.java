package crystekteam.crystek.blocks;

import crystekteam.crystek.CreativeTabCrystek;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBase extends BlockContainer implements ITileEntityProvider
{
    public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static PropertyBool ACTIVE = PropertyBool.create("active");
    boolean hasCustomStaes;

    public BlockBase()
    {
        super(Material.IRON);
        setCreativeTab(CreativeTabCrystek.instance);
        setHardness(2.0F);
        isBlockContainer = true;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        super.onBlockActivated(world, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);

        if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileBase)
        {
            TileBase tileBase = (TileBase) world.getTileEntity(pos);
            tileBase.syncWithAll();
        }
        return true;
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        dropInventory(worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }

    //Copied from ReboronCore https://github.com/TechReborn/RebornCore/blob/1.9.4/src/main/java/reborncore/common/blocks/BlockMachineBase.java#L82
    protected BlockStateContainer createBlockState()
    {
        FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
        ACTIVE = PropertyBool.create("active");
        return new BlockStateContainer(this, FACING, ACTIVE);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if(hasCustomStaes){
            return;
        }
        if (!worldIn.isRemote)
        {
            IBlockState sate = worldIn.getBlockState(pos.north());
            Block block = sate.getBlock();
            IBlockState state1 = worldIn.getBlockState(pos.south());
            Block block1 = state1.getBlock();
            IBlockState state2 = worldIn.getBlockState(pos.west());
            Block block2 = state2.getBlock();
            IBlockState state3 = worldIn.getBlockState(pos.east());
            Block block3 = state3.getBlock();
            EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock(state) && !block1.isFullBlock(state1))
            {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock(state1) && !block.isFullBlock(state))
            {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && block2.isFullBlock(state2) && !block3.isFullBlock(state2))
            {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && block3.isFullBlock(state3) && !block2.isFullBlock(state2))
            {
                enumfacing = EnumFacing.WEST;
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        setFacing(placer.getHorizontalFacing().getOpposite(), worldIn, pos);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int facingInt = getSideFromEnum(state.getValue(FACING));
        int activeInt = state.getValue(ACTIVE) ? 0 : 4;
        return facingInt + activeInt;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        boolean active = false;
        int facingInt = meta;
        if (facingInt > 4)
        {
            active = true;
            facingInt = facingInt - 4;
        }
        EnumFacing facing = getSideFromint(facingInt);
        return this.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, active);
    }

    public boolean isActive(IBlockState state)
    {
        return state.getValue(ACTIVE);
    }

    public EnumFacing getFacing(IBlockState state)
    {
        return state.getValue(FACING);
    }

    public void setFacing(EnumFacing facing, World world, BlockPos pos)
    {
        if(hasCustomStaes){
            return;
        }
        world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING, facing));
    }

    public EnumFacing getSideFromint(int i)
    {
        if (i == 0)
        {
            return EnumFacing.NORTH;
        }
        else if (i == 1)
        {
            return EnumFacing.SOUTH;
        }
        else if (i == 2)
        {
            return EnumFacing.EAST;
        }
        else if (i == 3)
        {
            return EnumFacing.WEST;
        }
        return EnumFacing.NORTH;
    }

    public int getSideFromEnum(EnumFacing facing)
    {
        if (facing == EnumFacing.NORTH)
        {
            return 0;
        }
        else if (facing == EnumFacing.SOUTH)
        {
            return 1;
        }
        else if (facing == EnumFacing.EAST)
        {
            return 2;
        }
        else if (facing == EnumFacing.WEST)
        {
            return 3;
        }
        return 0;
    }

    protected void dropInventory(World world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity == null) { return; }
        if (!(tileEntity instanceof IInventory)) { return; }

        IInventory inventory = (IInventory) tileEntity;
        if(inventory.getSizeInventory() != 0)
        {
            List<ItemStack> items = new ArrayList<ItemStack>();

            for (int i = 0; i < inventory.getSizeInventory(); i++)
            {
                ItemStack itemStack = inventory.getStackInSlot(i);

                if (itemStack == null)
                {
                    continue;
                }
                if (itemStack != null && itemStack.stackSize > 0)
                {
                    if (itemStack.getItem() instanceof ItemBlock)
                    {
                        if (((ItemBlock) itemStack.getItem()).block instanceof BlockFluidBase
                                || ((ItemBlock) itemStack.getItem()).block instanceof BlockStaticLiquid
                                || ((ItemBlock) itemStack.getItem()).block instanceof BlockDynamicLiquid)
                        {
                            continue;
                        }
                    }
                }
                items.add(itemStack.copy());
            }
            for (ItemStack itemStack : items)
            {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, pos.getX() + dX, pos.getY() + dY, pos.getZ() + dZ, itemStack.copy());
                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }

    public void setActive(Boolean active, World world, BlockPos pos)
    {
        if(hasCustomStaes)
        {
            return;
        }
        EnumFacing facing = world.getBlockState(pos).getValue(FACING);
        IBlockState state = world.getBlockState(pos).withProperty(ACTIVE, active).withProperty(FACING, facing);
        world.setBlockState(pos, state, 3);
    }

    public boolean fillBlockWithFluid(World worldIn, BlockPos pos, EntityPlayer playerIn, ItemStack heldItem, EnumFacing side)
    {
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile == null || !tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side))
        {
            return false;
        }

        IFluidHandler fluidHandler = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
        FluidUtil.interactWithFluidHandler(heldItem, fluidHandler, playerIn);
        return heldItem != null && !(heldItem.getItem() instanceof ItemBlock);
    }
}
