package io.github.mulreon.cities;

import io.github.mulreon.cities.blocks.LaptopBlock;
import io.github.mulreon.cities.items.PhoneItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameRules;

public class CitiesMod implements ModInitializer {
    public static final String MOD_ID = "cities";

    @Override
    public void onInitialize() {
        // registry items
        PhoneItem.registry();

        // registry blocks
        LaptopBlock.registry();
    }
}
