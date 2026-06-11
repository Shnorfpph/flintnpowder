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
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.ragingzombies.flintnpowder.sound.ModSounds;

public class CastIronRoundshotProjectile extends AbstractArrow implements ItemSupplier {

    public float damage = 1;

    public CastIronRoundshotProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public CastIronRoundshotProjectile(Level pLevel) {
        super(ModProjectiles.CASTIRONROUNDSHOTPROJECTILE.get(), pLevel);
    }
    public CastIronRoundshotProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModProjectiles.CASTIRONROUNDSHOTPROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            Vec3 motion = this.getDeltaMovement();
            for (int i = 0; i < 5; i++) {
                double offset = i * 0.2;
                ((ServerLevel) this.level()).sendParticles(
                        ParticleTypes.POOF,
                        this.getX() - motion.x * offset,
                        this.getY() - motion.y * offset + 0.1,
                        this.getZ() - motion.z * offset,
                        1,
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

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                ModSounds.BULLETHIT.get(), SoundSource.NEUTRAL, 0.5F, 1.0F);
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
    pResult.getEntity().invulnerableTime = 0;
        if (!this.level().isClientSide()) {
            DamageSource dmg = this.damageSources().arrow( this, this.getOwner());

            double speed = this.getDeltaMovement().length();
            pResult.getEntity().hurt(dmg, damage);

            collisionParticles(pResult.getEntity().getOnPos());
        }
        this.discard();
    }

}
