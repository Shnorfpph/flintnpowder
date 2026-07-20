package org.ragingzombies.flintnpowder.item.ammo.magazines;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.ragingzombies.flintnpowder.core_modified.BaseMagazineEnchantable;
import org.ragingzombies.flintnpowder.sound.ModSounds;

public class MachineGunMag extends BaseMagazineEnchantable {
    public MachineGunMag(Item.Properties pProperties) {
        super(pProperties);
        this.maxAmmo = 50;
        this.requiredMagazineTags.add("machinegunmag");
        this.allowedCalibersTags.add("rifleround");
    }
    @Override
    public void onAmmoInsert(LivingEntity ply, ItemStack mag) {
        ply.level().playSound(null, ply,
                ModSounds.MAGAZINERELOAD.get(), SoundSource.NEUTRAL, 1.0F, 1.2F);

        if (ply instanceof Player p) {
            p.getCooldowns().addCooldown(mag.getItem(), 2);
        }
    }
}