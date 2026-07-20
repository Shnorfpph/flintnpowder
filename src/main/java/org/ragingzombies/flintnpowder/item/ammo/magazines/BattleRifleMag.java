/*
 * Copyright (C) 2026 Livelandr
 *
 * This file is part of Flint'N'Powder.
 *
 * Flint'N'Powder is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Flint'N'Powder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.ragingzombies.flintnpowder.item.ammo.magazines;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.ragingzombies.flintnpowder.core_modified.BaseMagazineEnchantable;
import org.ragingzombies.flintnpowder.sound.ModSounds;

public class BattleRifleMag extends BaseMagazineEnchantable {

    public BattleRifleMag(Properties pProperties) {
        super(pProperties);
        maxAmmo = 8;
        this.requiredMagazineTags.add("brmag");



        this.allowedCalibersTags.add("rifleround");
    }

    @Override
    public void onAmmoInsert(LivingEntity ply, ItemStack mag) {
        ply.level().playSound(null, ply,
                ModSounds.MAGAZINERELOAD.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);

        if (ply instanceof Player p) {
            p.getCooldowns().addCooldown(mag.getItem(), 5);
        }
    }
}
