package crystekteam.crystek.client.render;

import crystekteam.crystek.tiles.TileTinkerTable;
import crystekteam.crystek.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class TileTinkerRender extends TileEntitySpecialRenderer<TileTinkerTable>
{
    @Override
    public void renderTileEntityAt(TileTinkerTable te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if(te != null)
        {
            if(te.getStackInSlot(1) != null)
            {
                GlStateManager.pushMatrix();

                ItemStack stack = te.getStackInSlot(1);

                double spin = Minecraft.getSystemTime()/800D;
                GlStateManager.translate(x + .5, y + 1.5, z + .5);
                GlStateManager.rotate((float)(((spin*40D)%360)), 0, 1, 0);

                RenderUtil.renderItemInWorld(stack);
                GlStateManager.popMatrix();
            }
        }
    }
}
