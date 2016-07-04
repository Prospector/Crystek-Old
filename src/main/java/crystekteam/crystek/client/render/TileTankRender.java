package crystekteam.crystek.client.render;

import crystekteam.crystek.tiles.TileTank;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

/**
 * Created by Gigabit101 on 01/07/2016.
 */
public class TileTankRender extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        TileTank tileTank = (TileTank) te;
        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);
        GL11.glRotatef(180, 0, 0, 5);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if(tileTank.tank.getFluid() != null)
            renderFluid(tileTank.tank.getFluid());

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    //Todo fix
    public void renderFluid(FluidStack fluidStack)
    {
        int colour = fluidStack.getFluid().getColor();
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef(0f, 2.0F, 0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        setGLColorFromIntPlusAlpha(colour);

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void setGLColorFromIntPlusAlpha(int color)
    {
        float alpha = (color >> 24 & 255) / 255.0F;
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;

        GlStateManager.color(red, green, blue, alpha);
    }
}
