package org.ragingzombies.flintnpowder.item.ammo.magazines;

import net.minecraft.world.item.ItemStack;
import org.ragingzombies.flintnpowder.core.ammo.BaseMagazine;
import org.ragingzombies.flintnpowder.item.ammo.PistolRound;

public class HandgunMag extends BaseMagazine {

    public HandgunMag(Properties pProperties) {
        super(pProperties);
        maxAmmo = 8;
    }

    @Override
    public boolean allowAmmo(ItemStack ammo) {
        if (ammo.getItem() instanceof PistolRound) return true;

        return false;
    }
}
