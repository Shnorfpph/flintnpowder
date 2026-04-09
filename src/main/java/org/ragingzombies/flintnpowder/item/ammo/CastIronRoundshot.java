package org.ragingzombies.flintnpowder.item.ammo;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.ragingzombies.flintnpowder.core.ammo.BaseAmmo;
import org.ragingzombies.flintnpowder.core.guns.GunBase;
import org.ragingzombies.flintnpowder.item.ModItems;
import org.ragingzombies.flintnpowder.item.ammo.projectiles.CastIronRoundshotProjectile;
import org.ragingzombies.flintnpowder.sound.ModSounds;

import java.util.Random;

import static org.ragingzombies.flintnpowder.core.util.CameraWork.OffsetEntityCamera;

public class CastIronRoundshot extends BaseAmmo {
    public CastIronRoundshot(Properties pProperties) {
        super(pProperties);
        this.damage = 18;
    }

    @Override
    public void onAmmoShot(LivingEntity shooter, GunBase gun, Level level) {
        CastIronRoundshotProjectile proj = new CastIronRoundshotProjectile(level, shooter);

        proj.damage = this.damage * gun.damageModifier();
        proj.setOwner(shooter);
        proj.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, 10F, 4F * gun.accuracyModifier());

        // Recoil
        Random rand = new Random();
        float angleX = rand.nextFloat(4.0F);
        OffsetEntityCamera(shooter,(-15+(angleX-2))*gun.recoilModifierX(),(angleX-2)*gun.recoilModifierY());

        level.addFreshEntity(proj);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("flintnpowder.bullet_description"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
