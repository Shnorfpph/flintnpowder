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
package org.ragingzombies.flintnpowder.item.ammo.clips;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import com.livelandr.flintcore.core.ammo.BaseAmmo;
import org.ragingzombies.flintnpowder.item.ModItemsAmmo;

import javax.annotation.Nullable;
import java.util.List;

public class RifleRoundClip extends BaseAmmo {
    ItemStack dummy = ItemStack.EMPTY;
    public RifleRoundClip(Properties pProperties) {
        super(pProperties);
        damage = 19;

        addRequiredTag("rifleroundclip");
    }

    @Override
    public int ammoCountInOne(ItemStack ammo) {
        return 4;
    }

    @Override
    public ItemStack getAmmoItemStack(ItemStack ammo) {
        return new ItemStack(ModItemsAmmo.RIFLEROUND.get());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("flintcore.contains").
                append(String.valueOf(ammoCountInOne(dummy))).
                append(" ").
                append(getAmmoItemStack(dummy).getDisplayName())
        );

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
