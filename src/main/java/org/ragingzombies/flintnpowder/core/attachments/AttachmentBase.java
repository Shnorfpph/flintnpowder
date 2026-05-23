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
package org.ragingzombies.flintnpowder.core.attachments;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttachmentBase extends Item {
    public static final List<String> attachmentTypes = new ArrayList<>(Arrays.asList("Underbarrel","Silencer","Muzzle","Optic"));

    protected String type = "Misc";

    public AttachmentBase(Properties pProperties) {
        super(pProperties);
    }
    public static void RegisterAttachmentType(String type) {
        attachmentTypes.add(type);
    }

    public String getType() {
        return type;
    }

    public void onAttach(LivingEntity player, ItemStack attachment, ItemStack gun) {}
    public void onDetach(LivingEntity player, ItemStack attachment, ItemStack gun) {}
}
