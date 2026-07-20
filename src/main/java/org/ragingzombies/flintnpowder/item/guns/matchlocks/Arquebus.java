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
package org.ragingzombies.flintnpowder.item.guns.matchlocks;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.ragingzombies.flintnpowder.core_modified.guns.MatchlockBaseEnchantable;
import org.ragingzombies.flintnpowder.sound.ModSounds;

import javax.annotation.Nullable;
import java.util.List;

public class Arquebus extends MatchlockBaseEnchantable {

    public Arquebus(Properties pProperties) {
        super(pProperties);

        shootCooldownTicks = 30;
        gunpowderCooldownTicks = 20;
        ramrodCooldownTicks = 60;

        noCock = true;

        addCompatibleCaliberTag("roundshot");

        addAttachmentSlot("optic");
    }


    @Override
    public float accuracyModifier(LivingEntity ply, ItemStack gun) {
        return 1 * super.accuracyModifier(ply, gun);
    }

    public void onStuff(Level pLevel, LivingEntity shooter, ItemStack gun, InteractionHand pUsedHand) {
        pLevel.playSound(null, shooter,
                ModSounds.RAMROD.get(), SoundSource.NEUTRAL, 1F, 1.0F);

        setAimAnimation(gun);

        if (shooter instanceof Player ply) {
            setCooldown(ply, gun,  ramrodCooldown(ply, gun));
        }
    }

    @Override
    public void onShoot(float rotationX, float rotationY, Level pLevel, LivingEntity shooter, ItemStack gunStack) {
        pLevel.playSound(null, shooter,
                ModSounds.MUSKETFIRE.get(), SoundSource.NEUTRAL, 3.0F, 1.0F);
        pLevel.playSound(null, shooter,
                ModSounds.GUNSHOTDISTANT.get(), SoundSource.NEUTRAL, 9.0F, 1.0F);

        setReloadAnimation(gunStack);

        super.onShoot(rotationX, rotationY, pLevel, shooter, gunStack);
    }

    @Override
    public boolean allowPressingTrigger(Level pLevel, LivingEntity pPlayer, ItemStack gun, InteractionHand pUsedHand) {
        ItemStack secondItemStack;
        if (pUsedHand == InteractionHand.MAIN_HAND)
            secondItemStack = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
        else
            secondItemStack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);

        if (secondItemStack.is(Items.AIR)) return true;

        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.flintnpowder.arquebus.description_0"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
