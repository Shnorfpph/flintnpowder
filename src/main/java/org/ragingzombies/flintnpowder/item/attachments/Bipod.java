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
package org.ragingzombies.flintnpowder.item.attachments;

import com.livelandr.flintcore.core.util.HookSystem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import org.ragingzombies.flintnpowder.Flintnpowder;
import com.livelandr.flintcore.core.attachments.AttachmentBase;
import com.livelandr.flintcore.core.guns.GunBase;
import org.ragingzombies.flintnpowder.item.ModItemsAttachments;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = Flintnpowder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Bipod extends AttachmentBase {
    public Bipod(Properties pProperties) {
        super(pProperties);
        setSlot("underbarrel");

        HookSystem.addHook(HookSystem.CALCULATE_RECOIL_MODIFIER_X, (context -> {
            LivingEntity shooter = context.getShooter();
            ItemStack gun = context.getGun();

            if (shooter.isCrouching() && GunBase.getGunBase(gun).isAttachmentSpecific(gun, "underbarrel", ModItemsAttachments.BIPOD.get())) {
                return 0.025F;
            };
            return 1F;
        }));

        HookSystem.addHook(HookSystem.CALCULATE_RECOIL_MODIFIER_Y, (context -> {
            LivingEntity shooter = context.getShooter();
            ItemStack gun = context.getGun();

            if (shooter.isCrouching() && GunBase.getGunBase(gun).isAttachmentSpecific(gun, "underbarrel", ModItemsAttachments.BIPOD.get())) {
                return 0.025F;
            };
            return 1F;
        }));
    }

    @Override
    public void onAttach(LivingEntity player, ItemStack attachment, ItemStack gun) {
        attachment.shrink(1);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.flintnpowder.bipod.description"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
