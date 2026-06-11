package org.ragingzombies.flintnpowder.item.ammo.magazines;

import net.minecraft.world.item.Item;
import org.ragingzombies.flintnpowder.core_modified.BaseMagazineEnchantable;

public class MachineGunMag extends BaseMagazineEnchantable {
    public MachineGunMag(Item.Properties pProperties) {
        super(pProperties);
        this.maxAmmo = 50;
        this.requiredMagazineTags.add("machinegunmag");
        this.allowedCalibersTags.add("rifleround");
    }
}