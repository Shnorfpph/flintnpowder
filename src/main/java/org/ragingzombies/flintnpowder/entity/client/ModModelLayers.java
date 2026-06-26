package org.ragingzombies.flintnpowder.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import org.ragingzombies.flintnpowder.Flintnpowder;

public class ModModelLayers {
    public static final ModelLayerLocation MORTAR_LAYER = new ModelLayerLocation(
            new ResourceLocation(Flintnpowder.MOD_ID, "mortar_layer"), "main");

    public static final ModelLayerLocation OLDMORTAR_LAYER = new ModelLayerLocation(
            new ResourceLocation(Flintnpowder.MOD_ID, "oldmortar_layer"), "main");

    public static final ModelLayerLocation CANNON_LAYER = new ModelLayerLocation(
            new ResourceLocation(Flintnpowder.MOD_ID, "cannon_layer"), "main");

}
