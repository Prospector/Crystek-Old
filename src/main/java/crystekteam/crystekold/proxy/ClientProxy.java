package crystekteam.crystekold.proxy;

import crystekteam.crystekold.blocks.fluids.FluidBlockBase;
import crystekteam.crystekold.client.render.TileTankRender;
import crystekteam.crystekold.client.render.TileTinkerRender;
import crystekteam.crystekold.init.ModFluids;
import crystekteam.crystekold.init.ModelHandler;
import crystekteam.crystekold.laser.TileLaser;
import crystekteam.crystekold.laser.TileLaserRender;
import crystekteam.crystekold.lib.ModInfo;
import crystekteam.crystekold.tiles.TileTank;
import crystekteam.crystekold.tiles.TileTinkerTable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenders()
    {
        ModelHandler.init();
        registerFluidRenders();
        ClientRegistry.bindTileEntitySpecialRenderer(TileTank.class, new TileTankRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaser.class, new TileLaserRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTinkerTable.class, new TileTinkerRender());

    }

    private static void registerFluidRenders()
    {
        registerFluid(ModFluids.fluidTeslaBlock);
    }

    private static void registerFluid(FluidBlockBase fluid)
    {
        Item fluidTeslaItem = Item.getItemFromBlock(fluid);
        final ModelResourceLocation fluidTeslaLocation = new ModelResourceLocation(ModInfo.MOD_ID.toLowerCase() + ":fluids", fluid.getFluid().getName().toLowerCase());
        ModelBakery.registerItemVariants(fluidTeslaItem);
        ModelLoader.setCustomMeshDefinition(fluidTeslaItem, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return fluidTeslaLocation;
            }
        });
        ModelLoader.setCustomStateMapper(fluid, new StateMapperBase()
        {
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return fluidTeslaLocation;
            }
        });
    }
}
