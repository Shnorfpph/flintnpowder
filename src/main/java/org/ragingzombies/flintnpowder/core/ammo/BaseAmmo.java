/*
 * Copyright (C) 2026 RagingZombies
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
package org.ragingzombies.flintnpowder.core.ammo;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.ragingzombies.flintnpowder.core.guns.GunBase;
import org.ragingzombies.flintnpowder.sound.ModSounds;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class BaseAmmo extends Item {

    public float damage = 0;
    public boolean customDescription = false;

    public BaseAmmo(Properties pProperties) {
        super(pProperties);
    }

    public void onAmmoShot(LivingEntity shooter, ItemStack gun, Level level) {
    }

    public int ammoCountInOne(ItemStack ammo) {
        return 1;
    }

    public ItemStack getAmmoItemStack(ItemStack ammo) {
        return ammo;
    }

    public float PropellantMultiplier(LivingEntity shooter, ItemStack gun) {
        return 1;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (!customDescription) {
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.translatable("flintnpowder.bullet_description"));
            pTooltipComponents.add(Component.translatable("flintnpowder.projectile_damage")
                    .append(String.valueOf(Math.round(this.damage))).withStyle(ChatFormatting.DARK_GREEN));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
