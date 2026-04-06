package org.ragingzombies.flintnpowder.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ragingzombies.flintnpowder.Flintnpowder;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Flintnpowder.MOD_ID);

    public static final RegistryObject<SoundEvent> RAMROD = registerSoundEvents("ramrod");
    public static final RegistryObject<SoundEvent> GUNSWING = registerSoundEvents("gunswing");
    public static final RegistryObject<SoundEvent> MUSKETFIRE = registerSoundEvents("musketfire");

    public static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Flintnpowder.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
