package org.ragingzombies.flintnpowder.item.ammo.projectiles.mortar;

import com.livelandr.flintcore.core.sounds.ModSoundDeferred;
import com.livelandr.flintcore.core.sounds.TickableSounds.MortarFallingSoundInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.ragingzombies.flintnpowder.entity.ModEntities;
import org.ragingzombies.flintnpowder.entity.custom.SmokeEntity;
import org.ragingzombies.flintnpowder.item.ammo.projectiles.ModProjectiles;

public class PoisonGasProjectile extends AbstractArrow implements ItemSupplier {
    MortarFallingSoundInstance snd = null;
    public PoisonGasProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public PoisonGasProjectile(Level pLevel) {
        super(ModProjectiles.HESHELL.get(), pLevel);
    }
    public PoisonGasProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModProjectiles.HESHELL.get(), livingEntity, pLevel);
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            if (snd == null) {
                snd = new MortarFallingSoundInstance(ModSoundDeferred.BROWNNOISE.get(), getX(), getY(), getZ());
                snd.setEnt(this);
                Minecraft.getInstance().getSoundManager().play(snd);
            } else {
                snd.setPosition(getX(),getY(),getZ());
            }
        }

        // Sounds
        super.tick();

        if (!level().isClientSide()) {
            Vec3 motion = this.getDeltaMovement();
            for (int i = 0; i < 5; i++) {
                double offset = i * 0.2;
                ((ServerLevel) this.level()).sendParticles(
                        ParticleTypes.SMOKE,
                        this.getX() - motion.x * offset,
                        this.getY() - motion.y * offset + 0.1,
                        this.getZ() - motion.z * offset,
                        0,
                        motion.x * 0.05, motion.y * 0.05, motion.z * 0.05,
                        0.06
                );
            }
            for (int i = 0; i < 5; i++) {
                double offset = i * 0.2;
                ((ServerLevel) this.level()).sendParticles(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        this.getX() - motion.x * offset,
                        this.getY() - motion.y * offset + 0.1,
                        this.getZ() - motion.z * offset,
                        0,
                        motion.x * 0.05, motion.y * 0.05, motion.z * 0.05,
                        0.06
                );
            }
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.EMPTY;
    }


    void collisionParticles(BlockPos pos) {
        ((ServerLevel) this.level()).sendParticles(
                ParticleTypes.LARGE_SMOKE,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                10,
                0.05, 0.05, 0.05,
                0.06
        );
        ((ServerLevel) this.level()).sendParticles(
                ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                15,
                0.05, 0.05, 0.05,
                0.01
        );

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL, 2.0F, 1.0F);
    }

    void impact(BlockPos pos) {
        collisionParticles(pos);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 5; x++) {
                Vec3 vec = new Vec3( Math.cos(Math.toRadians(((360.0 / 5.0) * x))), y/3.0, Math.sin(Math.toRadians(((360.0 / 5.0) * x))) );

                SmokeEntity smoke = new SmokeEntity(ModEntities.SMOKE_GAS.get(), this.level());
                smoke.effect = MobEffects.POISON;
                smoke.setPos(this.getOnPos().getCenter());
                smoke.setBaseVector(vec);
                smoke.setColor(0, 110, 0);
                smoke.spriteSize = (float) (1F / (1+y));

                level().addFreshEntity(smoke);
            }
        }

        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide()) {
            impact(pResult.getBlockPos());
        }

        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.getOwner() == pResult.getEntity()) return;

        pResult.getEntity().invulnerableTime = 0;
        if (!this.level().isClientSide()) {
            DamageSource dmg = this.damageSources().arrow(this, this.getOwner());
            pResult.getEntity().hurt(dmg, 25);

            impact(pResult.getEntity().getOnPos());
        }
    }

}
