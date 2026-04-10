package org.ragingzombies.flintnpowder.core.attachments;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AttachmentBase extends Item {
    protected String type = "Misc";

    public AttachmentBase(Properties pProperties) {
        super(pProperties);
    }

    public String getType() {
        return type;
    }

    public void onAttach(LivingEntity player, ItemStack attachment, ItemStack gun) {}
    public void onDetach(LivingEntity player, ItemStack attachment, ItemStack gun) {}
}
