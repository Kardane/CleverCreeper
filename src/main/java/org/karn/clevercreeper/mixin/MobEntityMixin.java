package org.karn.clevercreeper.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.GameRules;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.karn.clevercreeper.CCGamerules;
import org.karn.clevercreeper.goals.CreeperBreachWallGoal;
import org.karn.clevercreeper.goals.XrayFollowGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "initialize")
    private void onInitialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir){
        if(getType() == EntityType.CREEPER){
            Object object = this;
            CreeperEntity centity = ((CreeperEntity)object);
            GameRules gameRules = centity.getWorld().getGameRules();

            //follow range
            //centity.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(gameRules.getInt(CCGamerules.FOLLOW_RANGE));

            //sync size power
            //if(centity.getWorld().getGameRules().getBoolean(CCGamerules.SYNC_SIZE_POWER)){
            //    double baseValue = centity.getAttributeInstance(EntityAttributes.GENERIC_SCALE).getBaseValue();
            //    NbtCompound tag = new NbtCompound();
            //    centity.writeCustomDataToNbt(tag);
            //    tag.putByte("ExplosionRadius", (byte) Math.floor(baseValue*3));
            //    centity.readCustomDataFromNbt(tag);
            //}

            GoalSelector targets = ((MobEntityAccessor) centity).getTargetSelector();
            Set<PrioritizedGoal> tGoals = ((GoalSelectorAccessor) targets).getGoals();

            targets.remove( ((PrioritizedGoal)tGoals.toArray()[0]).getGoal() );// removing player target
            targets.add(1, new XrayFollowGoal(centity, PlayerEntity.class, false));

            GoalSelector goals = ((MobEntityAccessor) centity).getGoalSelector();
            Set<PrioritizedGoal> gGoals = ((GoalSelectorAccessor) goals).getGoals();

            if(gameRules.getBoolean(CCGamerules.BREACH_WALLS)) {
                goals.remove(((PrioritizedGoal) gGoals.toArray()[1]).getGoal()); // removing ignite goal (second goal added -> second goal in the array)
                goals.add(2, new CreeperBreachWallGoal(centity)); // adding the new Ignite Goal

            }

            if(gameRules.getBoolean(CCGamerules.BOUNCE_ATTACK)) {
                goals.add(3, new PounceAtTargetGoal(centity, 0.4F));
            }
        }
    }
}
