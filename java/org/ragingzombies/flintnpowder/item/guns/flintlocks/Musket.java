package org.ragingzombies.flintnpowder.item.guns.flintlocks;

import net.minecraft.world.item.Item;
import org.ragingzombies.flintnpowder.core.guns.FlintlockBase;
import org.ragingzombies.flintnpowder.item.ammo.CastIronRoundshot;
import org.ragingzombies.flintnpowder.item.ammo.ShotgunShell;

public class Musket extends FlintlockBase {
    public Musket(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean checkAmmo(Item ammo) {
        if (ammo instanceof ShotgunShell) {
            return true;
        }
        if (ammo instanceof CastIronRoundshot) {
            return true;
        }

        return false;
    }
}
