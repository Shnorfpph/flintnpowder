/*
 * Copyright (C) 2026 Livelandr
 *
 * This file is part of Flint'N'Powder.
 *
 * Flint'N'Powder is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Flint'N'Powder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.ragingzombies.flintnpowder.item.ammo.projectiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.sound.ModSounds;

public class FoolsGoldRoundshotProjectile extends AbstractArrow implements ItemSupplier {

    private int entsPierced = 0;
    public float damage = 1;

    public FoolsGoldRoundshotProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public FoolsGoldRoundshotProjectile(Level pLevel) {
        super(ModProjectiles.STEELROUNDSHOTPROJECTILE.get(), pLevel);
    }
    public FoolsGoldRoundshotProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModProjectiles.STEELROUNDSHOTPROJECTILE.get(), livingEntity, pLevel);
        this.setPierceLevel((byte) 1);
    }
    @Override
    public void tick() {
        double prevX = this.getX();
        double prevY = this.getY();
        double prevZ = this.getZ();

        super.tick();

        if (!level().isClientSide()) {
            Vec3 motion = this.getDeltaMovement();
            double length = motion.length();

            if (length == 0) return;

            double step = 3;
            int spawnPointsCount = (int) (length / step);

            double chaosRadius = 0.05;

            ServerLevel serverLevel = (ServerLevel) this.level();

            for (int i = 0; i < spawnPointsCount; i++) {
                double pct = (double) i / spawnPointsCount;

                double pX = prevX + (this.getX() - prevX) * pct;
                double pY = prevY + (this.getY() - prevY) * pct + 0.1;
                double pZ = prevZ + (this.getZ() - prevZ) * pct;

                serverLevel.sendParticles(
                        ParticleTypes.FIREWORK,
                        pX, pY, pZ,
                        1,
                        chaosRadius,
                        chaosRadius,
                        chaosRadius,
                        0.03
                );
            }
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.AIR);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
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

        this.level().playSeededSound(null, this.getX(), this.getY(), this.getZ(),
                ModSounds.BULLETHIT.get(), SoundSource.NEUTRAL, 2.0F, 1.0F, 0);
    }


    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide()) {
            collisionParticles(pResult.getBlockPos());
            this.discard();
        }

        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (!this.level().isClientSide()) {
            DamageSource dmg = this.damageSources().arrow( this, this.getOwner());

            double speed = this.getDeltaMovement().length();
            pResult.getEntity().hurt(dmg, damage);

            collisionParticles(pResult.getEntity().getOnPos());
            damage /= 1.25;
        }

        if (++entsPierced > 1) {
            this.discard();
        }

        //
    }

}
