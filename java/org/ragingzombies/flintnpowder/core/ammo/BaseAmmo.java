package org.ragingzombies.flintnpowder.core.ammo;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.ragingzombies.flintnpowder.core.guns.GunBase;
import org.ragingzombies.flintnpowder.sound.ModSounds;

import java.util.Map;

public class BaseAmmo extends Item {

    public BaseAmmo(Properties pProperties) {
        super(pProperties);
    }

    public void onAmmoShot(LivingEntity shooter, GunBase gun, Level level) {
    }
}
