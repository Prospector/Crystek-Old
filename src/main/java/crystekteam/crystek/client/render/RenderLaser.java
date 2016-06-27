package crystekteam.crystek.client.render;

import crystekteam.crystek.laser.TileLaser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

/**
 * Created by mark on 10/06/2016.
 */
public class RenderLaser extends TileEntitySpecialRenderer<TileLaser> {

	private static int sphereId;
	private static boolean hasInit = false;

	@Override
	public void renderTileEntityAt(TileLaser te, double x, double y, double z, float partialTicks, int destroyStage) {

		if(!hasInit){
			Sphere sphere = new Sphere();
			sphere.setDrawStyle(GLU.GLU_FILL);
			sphere.setNormals(GLU.GLU_SMOOTH);
			sphere.setOrientation(GLU.GLU_OUTSIDE);
			sphereId = GL11.glGenLists(1);
			GL11.glNewList(sphereId, GL11.GL_COMPILE);
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("crystek", "textures/blocks/basicmachine.png"));
			sphere.draw(0.5F, 32, 32);
			GL11.glEndList();
			hasInit = true;
		}
		GL11.glPushMatrix();

		GL11.glTranslated(x + 0.5, y + 0.5 ,z + 0.5);

		GL11.glScalef(0.9F, 0.9F, 0.9F);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);

		GL11.glCallList(sphereId);
		GL11.glDisable(GL11.GL_BLEND);

		GL11.glPopMatrix();
	}
}
