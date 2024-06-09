package org.karn.clevercreeper;

import net.fabricmc.api.ModInitializer;

public class CleverCreeper implements ModInitializer {
    public static final String MODID = "clevercreeper";
    @Override
    public void onInitialize() {
        System.out.println("CleverCreeper Online! Oww Man!");
        CCGamerules.init();
    }
}
