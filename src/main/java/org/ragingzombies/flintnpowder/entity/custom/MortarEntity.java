package org.ragingzombies.flintnpowder.entity.custom;

import com.livelandr.flintcore.core.SiegeEngineAdapter;
import com.livelandr.flintcore.core.guns.GunBase;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.ragingzombies.flintnpowder.ModItems;
import org.ragingzombies.flintnpowder.item.ModItemsAmmo;
import org.ragingzombies.flintnpowder.item.ModItemsGuns;
import org.ragingzombies.flintnpowder.item.guns.flintlocks.Bruttbuss;

public class MortarEntity extends Mob {
    SiegeEngineAdapter weapon;

    private static final EntityDataAccessor<Float> TARGET_YAW = SynchedEntityData.defineId(MortarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> TARGET_PITCH = SynchedEntityData.defineId(MortarEntity.class, EntityDataSerializers.FLOAT);

    public MortarEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.navigation.setMaxVisitedNodesMultiplier(0);

        weapon = new SiegeEngineAdapter();

        weapon.createGun(ModItemsGuns.BRUTTBUSS.get());
        weapon.setScapegoat(this);

        this.setCustomName(Component.literal("Mortar [Livelandr]"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    public void tick() {
        weapon.tick();
        super.tick();

        if (this.level().isClientSide) {
            float currentYaw = this.getYRot();

            this.yHeadRot = currentYaw;
            this.yBodyRot = currentYaw;
            this.yHeadRotO = currentYaw;
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.isCrouching() && !player.level().isClientSide()) {
            this.setYRot(this.getYRot() + 10);
            weapon.setRotationY(this.getYRot());

            player.sendSystemMessage(Component.translatable("flintnpowder.mortarrotation").append(Float.toString((weapon.getRotationY()) % 360)));

            return InteractionResult.SUCCESS;
        }

        weapon.setRotationX(-102);
        this.setXRot(-102);
        //weapon.forceShoot();

        weapon.transmitInteraction(this.level(), player, hand);

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected void jumpFromGround() {
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public int getAirSupply() {
        return this.getMaxAirSupply();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayerSq) {
        return false;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
