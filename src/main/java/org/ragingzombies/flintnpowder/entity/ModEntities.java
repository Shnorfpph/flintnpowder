package org.ragingzombies.flintnpowder.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.custom.*;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Flintnpowder.MOD_ID);

    public static final RegistryObject<EntityType<MortarEntity>> MORTAR =
            ENTITY_TYPES.register("mortar", () -> EntityType.Builder.of(MortarEntity::new, MobCategory.MISC)
                    .sized(1.0F, 0.8F)
                    .build("mortar"));

    public static final RegistryObject<EntityType<OldMortarEntity>> OLDMORTAR =
            ENTITY_TYPES.register("old_mortar", () -> EntityType.Builder.of(OldMortarEntity::new, MobCategory.MISC)
                    .sized(1.75F, 1.75F)
                    .build("mortar"));

    public static final RegistryObject<EntityType<CannonEntity>> CANNON =
            ENTITY_TYPES.register("cannon", () -> EntityType.Builder.of(CannonEntity::new, MobCategory.MISC)
                    .sized(1.75F, 1.75F)
                    .build("cannon"));

    public static final RegistryObject<EntityType<SmokeEntity>> SMOKE_GAS =
            ENTITY_TYPES.register("smoke_gas", () -> EntityType.Builder.of(SmokeEntity::new, MobCategory.MISC)
                    .sized(0.95F, 0.95F)
                    .build("smoke_gas"));

    public static final RegistryObject<EntityType<HyperSkeletonEntity>> HYPER_SKELETON =
            ENTITY_TYPES.register("hyper_skelet", () -> EntityType.Builder.of(HyperSkeletonEntity::new, MobCategory.MONSTER)
                    .build("hyper_skelet"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
