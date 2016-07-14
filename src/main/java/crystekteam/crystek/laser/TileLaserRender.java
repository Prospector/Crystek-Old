package crystekteam.crystek.laser;

import crystekteam.crystek.lib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;


/**
 * Created by Gigabit101 on 14/07/2016.
 */
public class TileLaserRender extends TileEntitySpecialRenderer<TileLaser>
{
    ResourceLocation laser = new ResourceLocation(ModInfo.MOD_ID.toLowerCase(), "textures/laser.png");

    @Override
    public void renderTileEntityAt(TileLaser te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if (te.connectedTile != null)
        {
            GlStateManager.pushMatrix();

            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);

            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP p = mc.thePlayer;
            double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX);
            double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY);
            double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ);

            Vector start = new Vector(te.getPos().getX() + .5f, te.getPos().getY() + .5f, te.getPos().getZ() + .5f);
            Vector player = new Vector((float) doubleX, (float) doubleY + p.getEyeHeight(), (float) doubleZ);

            GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer buffer = tessellator.getBuffer();

            this.bindTexture(laser);

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);

            BlockPos destination = new BlockPos(te.connectedTile.getPos());
            Vector end = new Vector(destination.getX() + .5f, destination.getY() + .5f, destination.getZ() + .5f);

            drawBeam(start, end, player, .1f);

            tessellator.draw();

            GlStateManager.popMatrix();
        }
    }

    /*
    * copied from McjtyLib https://github.com/McJty/McJtyLib/blob/1.10/src/main/java/mcjty/lib/gui/RenderHelper.java
    */

    public static void drawBeam(Vector S, Vector E, Vector P, float width)
    {
        Vector PS = Sub(S, P);
        Vector SE = Sub(E, S);

        Vector normal = Cross(PS, SE);
        normal = normal.normalize();

        Vector half = Mul(normal, width);
        Vector p1 = Add(S, half);
        Vector p2 = Sub(S, half);
        Vector p3 = Add(E, half);
        Vector p4 = Sub(E, half);

        drawQuad(Tessellator.getInstance(), p1, p3, p4, p2);
    }

    private static Vector Cross(Vector a, Vector b)
    {
        float x = a.y*b.z - a.z*b.y;
        float y = a.z*b.x - a.x*b.z;
        float z = a.x*b.y - a.y*b.x;
        return new Vector(x, y, z);
    }

    private static void drawQuad(Tessellator tessellator, Vector p1, Vector p2, Vector p3, Vector p4)
    {
        int brightness = 240;
        int b1 = brightness >> 16 & 65535;
        int b2 = brightness & 65535;

        VertexBuffer buffer = tessellator.getBuffer();
        buffer.pos(p1.getX(), p1.getY(), p1.getZ()).tex(0.0D, 0.0D).lightmap(b1, b2).color(255, 255, 255, 128).endVertex();
        buffer.pos(p2.getX(), p2.getY(), p2.getZ()).tex(1.0D, 0.0D).lightmap(b1, b2).color(255, 255, 255, 128).endVertex();
        buffer.pos(p3.getX(), p3.getY(), p3.getZ()).tex(1.0D, 1.0D).lightmap(b1, b2).color(255, 255, 255, 128).endVertex();
        buffer.pos(p4.getX(), p4.getY(), p4.getZ()).tex(0.0D, 1.0D).lightmap(b1, b2).color(255, 255, 255, 128).endVertex();
    }

    public static class Vector
    {
        public final float x;
        public final float y;
        public final float z;

        public Vector(float x, float y, float z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public float getX()
        {
            return x;
        }

        public float getY()
        {
            return y;
        }

        public float getZ()
        {
            return z;
        }

        public float norm()
        {
            return (float) Math.sqrt(x * x + y * y + z * z);
        }

        public Vector normalize()
        {
            float n = norm();
            return new Vector(x / n, y / n, z / n);
        }
    }

    private static Vector Sub(Vector a, Vector b)
    {
        return new Vector(a.x-b.x, a.y-b.y, a.z-b.z);
    }

    private static Vector Add(Vector a, Vector b)
    {
        return new Vector(a.x+b.x, a.y+b.y, a.z+b.z);
    }

    private static Vector Mul(Vector a, float f)
    {
        return new Vector(a.x * f, a.y * f, a.z * f);
    }
}
