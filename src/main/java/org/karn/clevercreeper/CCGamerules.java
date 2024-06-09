package org.karn.clevercreeper;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class CCGamerules {
    public static final GameRules.Key<GameRules.BooleanRule> SYNC_SIZE_POWER = GameRuleRegistry.register("CleverCreeper_syncSizeAndPower", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.IntRule> FOLLOW_RANGE = GameRuleRegistry.register("CleverCreeper_followRange", GameRules.Category.MOBS, GameRuleFactory.createIntRule(16,1));
    public static final GameRules.Key<GameRules.BooleanRule> ENABLE_XRAY = GameRuleRegistry.register("CleverCreeper_enableXray", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> BREACH_WALLS = GameRuleRegistry.register("CleverCreeper_breachWalls", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.IntRule> BREACH_WALLS_RANGE = GameRuleRegistry.register("CleverCreeper_breachWallsRange", GameRules.Category.MOBS, GameRuleFactory.createIntRule(64,1));
    public static final GameRules.Key<GameRules.BooleanRule> BOUNCE_ATTACK = GameRuleRegistry.register("CleverCreeper_doBounceAttack", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.IntRule> MIN_POWER = GameRuleRegistry.register("CleverCreeper_minExplosionPower", GameRules.Category.MOBS, GameRuleFactory.createIntRule(3,1));
    public static final GameRules.Key<GameRules.IntRule> MAX_POWER = GameRuleRegistry.register("CleverCreeper_maxExplosionPower", GameRules.Category.MOBS, GameRuleFactory.createIntRule(3,Integer.MAX_VALUE));


    public static void init(){
        //just to load the class
    }
}
