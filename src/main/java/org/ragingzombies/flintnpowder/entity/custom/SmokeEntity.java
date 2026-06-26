package org.ragingzombies.flintnpowder.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class SmokeEntity extends Entity {
    private static final EntityDataAccessor<Integer> COLOR_R =
            SynchedEntityData.defineId(SmokeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COLOR_G =
            SynchedEntityData.defineId(SmokeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COLOR_B =
            SynchedEntityData.defineId(SmokeEntity.class, EntityDataSerializers.INT);

    public int a = 150;

    public int frameOffset = 0;

    public MobEffect effect = null;
    int effectTickLength = 80;
    int effectAmplitude = 1;

    int lifetime = 600;

    public float spriteSize = 1.5F;
    public double speedModifier;

    Vec3 mainVec;

    public SmokeEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        Random rand = new Random();
        speedModifier = 1.0 + rand.nextDouble(3.0);
        frameOffset = rand.nextInt(4);
    }

    public void setColor(int _r, int _g, int _b) {
        this.entityData.set(COLOR_R, _r);
        this.entityData.set(COLOR_G, _g);
        this.entityData.set(COLOR_B, _b);
    }

    public int getR() {
        return this.entityData.get(COLOR_R);
    }
    public int getG() {
        return this.entityData.get(COLOR_G);
    }
    public int getB() {
        return this.entityData.get(COLOR_B);
    }

    public float calculateSpeed() {
        return (float) (1F * Math.pow(0.5, this.tickCount/speedModifier));
    }

    public void setBaseVector(Vec3 base) {
        mainVec = base;
    }

    @Override
    public void tick() {
        if (!level().isClientSide() && effect == null) {
            this.discard();
            return;
        }

        if (mainVec == null)
            mainVec = this.getForward().normalize();
        float speed = calculateSpeed();
        this.setDeltaMovement(mainVec.multiply(speed,speed,speed));

        this.move(MoverType.SELF, this.getDeltaMovement());
        super.tick();

        Vec3 vec3 = this.getDeltaMovement().normalize();
        Vec3 vec32 = this.position();
        Vec3 vec33 = vec32.add(vec3);
        HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitresult;
            Vec3 normal = Vec3.atLowerCornerOf(blockHitResult.getDirection().getNormal());

            Vec3 reflectedMotion = this.getDeltaMovement().subtract(normal.scale(2.0D * this.getDeltaMovement().dot(normal)));
            reflectedMotion = reflectedMotion.scale(0.8D);
            this.setDeltaMovement(reflectedMotion);
            setBaseVector(reflectedMotion.normalize());

            Vec3 safeSpawnPos = hitresult.getLocation().add(reflectedMotion.normalize().scale(0.05D));
            this.setPos(safeSpawnPos);
        }

        double radius = 3.0D;

        if (!level().isClientSide()) {
            AABB searchBox = this.getBoundingBox().inflate(radius);
            List<LivingEntity> playersInRange = this.level().getEntitiesOfClass(
                    LivingEntity.class,
                    searchBox,
                    player -> player.isAlive()
            );

            for (LivingEntity player : playersInRange) {
                if (!player.hasEffect(effect))
                    player.addEffect(new MobEffectInstance(effect, effectTickLength, effectAmplitude));
            }

            if (this.tickCount >= lifetime) {
                this.discard();
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(COLOR_R, 0);
        this.entityData.define(COLOR_G, 0);
        this.entityData.define(COLOR_B, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }
}
