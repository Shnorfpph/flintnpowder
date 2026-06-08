package org.ragingzombies.flintnpowder.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.ModEntities;
import org.ragingzombies.flintnpowder.entity.custom.MortarEntity;

@Mod.EventBusSubscriber(modid = Flintnpowder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MORTAR.get(), MortarEntity.createAttributes().build() );
    }
}
