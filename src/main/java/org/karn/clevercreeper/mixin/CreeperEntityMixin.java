package org.karn.clevercreeper.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.GameRules;
import org.karn.clevercreeper.CCGamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void syncSizePower(CallbackInfo ci){
        CreeperEntity centity = ((CreeperEntity)(Object)this);
        GameRules gameRules = centity.getWorld().getGameRules();
        double ScalebaseValue = centity.getAttributeInstance(EntityAttributes.GENERIC_SCALE).getBaseValue();

        if(centity.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).getBaseValue() != gameRules.getInt(CCGamerules.FOLLOW_RANGE))
            centity.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(gameRules.getInt(CCGamerules.FOLLOW_RANGE));

        if(gameRules.getBoolean(CCGamerules.SYNC_SIZE_POWER) && (double) ((CreeperEntityAccessor) centity).getExplosionRadius()/3 != ScalebaseValue){
            centity.getAttributeInstance(EntityAttributes.GENERIC_SCALE).setBaseValue((double) ((CreeperEntityAccessor) centity).getExplosionRadius()/3);
        }
        /*
        if(gameRules.getBoolean(CCGamerules.SYNC_SIZE_POWER) && Math.floor(ScalebaseValue*3) != ((CreeperEntityAccessor)centity).getExplosionRadius()){
            NbtCompound tag = new NbtCompound();
            centity.writeCustomDataToNbt(tag);
            tag.putByte("ExplosionRadius", (byte) Math.floor(ScalebaseValue*3));
            centity.readCustomDataFromNbt(tag);
        }*/
    }
}
