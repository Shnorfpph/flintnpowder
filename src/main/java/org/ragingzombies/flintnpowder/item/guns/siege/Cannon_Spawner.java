package org.ragingzombies.flintnpowder.item.guns.siege;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.ragingzombies.flintnpowder.entity.ModEntities;
import org.ragingzombies.flintnpowder.entity.custom.CannonEntity;
import org.ragingzombies.flintnpowder.entity.custom.MortarEntity;

public class Cannon_Spawner extends Item {
    public Cannon_Spawner(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            BlockHitResult hitResult = (BlockHitResult) pPlayer.pick(4.0D, 0.0F, false);

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = hitResult.getBlockPos().above();

                pLevel.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.NEUTRAL, 1.0F, 1F);

                CannonEntity ent = new CannonEntity(ModEntities.CANNON.get(), pLevel);
                ent.setPos(blockPos.getCenter());
                ent.setOwnerName(pPlayer.getName());

                pLevel.addFreshEntity(ent);
                pPlayer.getItemInHand(pUsedHand).shrink(1);
            }
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }
}
