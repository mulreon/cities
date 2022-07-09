package io.github.mulreon.cities.items;

import io.github.mulreon.cities.CitiesMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ItemGroups {
    public static final ItemGroup DEVICE_GROUP = FabricItemGroupBuilder.create(
                    new Identifier(CitiesMod.MOD_ID, "device"))
            .icon(() -> new ItemStack(PhoneItem.PHONE_ITEM))
            .build();
}
