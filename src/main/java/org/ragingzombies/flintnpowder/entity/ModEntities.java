package org.ragingzombies.flintnpowder.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.custom.MortarEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Flintnpowder.MOD_ID);

    public static final RegistryObject<EntityType<MortarEntity>> MORTAR =
            ENTITY_TYPES.register("mortar", () -> EntityType.Builder.of(MortarEntity::new, MobCategory.MISC).
                    build("mortar"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
