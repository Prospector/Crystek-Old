package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerPoweredFurnace;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileFurnace;
import crystekteam.crystek.tiles.prefab.TileBase;
import crystekteam.crystek.util.GuiButtonItemTexture;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiPoweredFurnace extends GuiBase
{
    TileFurnace tileFurnace;

    public GuiPoweredFurnace(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerPoweredFurnace(tile, player), "crystek.poweredfurnace");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        builder.drawSlot(this, guiLeft + 47, guiTop + 34);
        builder.drawSlot(this, guiLeft + 107, guiTop + 34);
        builder.drawProgressBar(this, container.progress, guiLeft + 76, guiTop + 34);
    }
}
