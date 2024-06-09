package org.karn.clevercreeper.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;
import org.karn.clevercreeper.CCGamerules;

import java.util.function.Predicate;

public class XrayFollowGoal<T extends LivingEntity> extends ActiveTargetGoal {

    public XrayFollowGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
        this(mob, targetClass, checkVisibility, false);
    }

    public XrayFollowGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, boolean checkCanNavigate) {
        this(mob, targetClass, 10, checkVisibility, checkCanNavigate, (Predicate)null);
    }

    public XrayFollowGoal(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(mob, targetClass, reciprocalChance, checkVisibility, checkCanNavigate, targetPredicate);
        if(mob.getWorld().getGameRules().getBoolean(CCGamerules.ENABLE_XRAY)) this.targetPredicate = (TargetPredicate.createAttackable()).setBaseMaxDistance(this.getFollowRange()).setPredicate(targetPredicate).ignoreVisibility();
    }
}