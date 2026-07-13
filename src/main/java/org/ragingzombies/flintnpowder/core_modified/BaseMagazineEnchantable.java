package org.ragingzombies.flintnpowder.core_modified;

import com.livelandr.flintcore.core.ammo.BaseMagazine;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.ragingzombies.flintnpowder.enchantments.ModEnchantments;
import org.ragingzombies.flintnpowder.sound.ModSounds;

public class BaseMagazineEnchantable extends BaseMagazine {
    public BaseMagazineEnchantable(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAmmo(ItemStack mag) {
        int amoLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.GHOST_LOADING.get(), mag);
        return (int) (this.maxAmmo * (1F + 0.1F * amoLevel));
    }

}
