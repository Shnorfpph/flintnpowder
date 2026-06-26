package org.ragingzombies.flintnpowder.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.client.ModModelLayers;
import org.ragingzombies.flintnpowder.entity.client.models.CannonModel;
import org.ragingzombies.flintnpowder.entity.client.models.MortarModel;
import org.ragingzombies.flintnpowder.entity.client.models.OldMortarModel;

@Mod.EventBusSubscriber(modid = Flintnpowder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MORTAR_LAYER, MortarModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.OLDMORTAR_LAYER, OldMortarModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CANNON_LAYER, CannonModel::createBodyLayer);
    }
}
