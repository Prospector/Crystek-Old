package crystekteam.crystek.util;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.Iterator;
import java.util.List;

public class GuiButtonItemTexture extends GuiButtonExt
{

	public int textureU;
	public int textureV;
	public ItemStack itemstack;

	public GuiButtonItemTexture(int id, int xPos, int yPos, int u, int v, int width, int height, ItemStack stack)
	{
		super(id, xPos, yPos, width, height, "_");
		textureU = u;
		textureV = v;
		itemstack = stack;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
					&& mouseY < this.yPosition + this.height;
			mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
			int u = textureU;
			int v = textureV;
			if (flag)
			{
				GL11.glPushMatrix();
				GL11.glColor4f(0f, 0f, 0f, 1f);
				this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, mc.fontRendererObj.getStringWidth("") + 16, height);
                drawItemStackTooltip(itemstack, this.xPosition, this.yPosition);
				GL11.glPopMatrix();
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(32826);
			RenderHelper.enableStandardItemLighting();
			RenderHelper.enableGUIStandardItemLighting();
			RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
			itemRenderer.renderItemIntoGUI(itemstack, this.xPosition, this.yPosition);
		}
	}

	protected void drawHoveringText(List list, int x, int y, FontRenderer font)
	{
		if (!list.isEmpty())
		{
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int k = 0;
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				String s = (String)iterator.next();
				int l = font.getStringWidth(s);

				if (l > k)
				{
					k = l;
				}
			}

			int adjX = x + 12;
			int adjY = y - 12;
			int i1 = 8;

			if (list.size() > 1)
			{
				i1 += 2 + (list.size() - 1) * 10;
			}

			if (adjX + k > width)
			{
				adjX -= 28 + k;
			}

			if (adjY + i1 + 6 > height)
			{
				adjY = height - i1 - 6;
			}

			this.zLevel = 300.0F;
//			itemRender.zLevel = 300.0F;
			int j1 = -267386864;
			this.drawGradientRect(adjX - 3, adjY - 4, adjX + k + 3, adjY - 3, j1, j1);
			this.drawGradientRect(adjX - 3, adjY + i1 + 3, adjX + k + 3, adjY + i1 + 4, j1, j1);
			this.drawGradientRect(adjX - 3, adjY - 3, adjX + k + 3, adjY + i1 + 3, j1, j1);
			this.drawGradientRect(adjX - 4, adjY - 3, adjX - 3, adjY + i1 + 3, j1, j1);
			this.drawGradientRect(adjX + k + 3, adjY - 3, adjX + k + 4, adjY + i1 + 3, j1, j1);
			int k1 = 1347420415;
			int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
			this.drawGradientRect(adjX - 3, adjY - 3 + 1, adjX - 3 + 1, adjY + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(adjX + k + 2, adjY - 3 + 1, adjX + k + 3, adjY + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(adjX - 3, adjY - 3, adjX + k + 3, adjY - 3 + 1, k1, k1);
			this.drawGradientRect(adjX - 3, adjY + i1 + 2, adjX + k + 3, adjY + i1 + 3, l1, l1);

			for (int i2 = 0; i2 < list.size(); ++i2)
			{
				String s1 = (String)list.get(i2);
				font.drawStringWithShadow(s1, adjX, adjY, -1);

				if (i2 == 0)
				{
					adjY += 2;
				}

				adjY += 10;
			}

			this.zLevel = 0.0F;
//			itemRender.zLevel = 0.0F;
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
//			RenderHelper.enableStandardItemLighting();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}

    protected void drawItemStackTooltip(ItemStack stack, int x, int y)
    {
        final Minecraft mc = Minecraft.getMinecraft();
        FontRenderer font = Objects.firstNonNull(stack.getItem().getFontRenderer(stack), mc.fontRendererObj);

        @SuppressWarnings("unchecked")
        java.util.List<String> list = stack.getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips);

        java.util.List<String> colored = Lists.newArrayListWithCapacity(list.size());
        colored.add(stack.getRarity().rarityColor + list.get(0));
        for (String line : list)
            colored.add(TextFormatting.GRAY + line);

        if (colored.size() >= 2)
            colored.remove(1);
        drawHoveringText(colored, x, y, font);
    }

	public boolean getIsHovering()
	{
		return hovered;
	}

}
