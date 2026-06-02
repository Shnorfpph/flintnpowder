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
import org.ragingzombies.flintnpowder.sound.ModSounds;

public class InvisibleProjectile extends AbstractArrow implements ItemSupplier {

    public float damage = 1;

    public InvisibleProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public InvisibleProjectile(Level pLevel) {
        super(ModProjectiles.CASTIRONROUNDSHOTPROJECTILE.get(), pLevel);
    }
    public InvisibleProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModProjectiles.CASTIRONROUNDSHOTPROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
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
        this.level().playSeededSound(null, this.getX(), this.getY(), this.getZ(),
                ModSounds.BULLETHIT.get(), SoundSource.NEUTRAL, 0.125F, 1.0F, 0);
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
        }
        this.discard();
    }

}
