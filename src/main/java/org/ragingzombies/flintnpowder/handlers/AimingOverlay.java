package org.ragingzombies.flintnpowder.handlers;

import com.livelandr.flintcore.core.guns.GunBase;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.item.ModItemsAttachments;

import static org.ragingzombies.flintnpowder.handlers.ZoomComputing.isAiming;

public class AimingOverlay implements IGuiOverlay {
    private static final ResourceLocation SCOPE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Flintnpowder.MOD_ID, "textures/misc/scope.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int width, int height) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ItemStack mainHand = player.getMainHandItem();

        if (player == null) return;
        if (!(mainHand.getItem() instanceof GunBase)) return;
        GunBase gun = GunBase.getGunBase(mainHand);

        if (com.livelandr.flintcore.core.handlers.KeyBindings.ZOOM_KEY.isDown() && isAiming(player) &&
                (gun.getAttachmentItem(mainHand, "optic") == ModItemsAttachments.HIGHPROFILEOPTIC.get())) {

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            guiGraphics.blit(
                    SCOPE_TEXTURE,
                    0, 0,
                    0, 0,
                    width, height,
                    width, height
            );

            RenderSystem.disableBlend();
            }
    }
}
