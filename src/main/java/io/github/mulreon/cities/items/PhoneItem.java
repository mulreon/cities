package io.github.mulreon.cities.items;

import io.github.mulreon.cities.CitiesMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class PhoneItem extends Item {
    public PhoneItem(Settings settings) {
        super(settings);
    }
    public static final PhoneItem PHONE_ITEM = new PhoneItem(new FabricItemSettings().group(ItemGroups.DEVICE_GROUP).maxCount(1));
    public static void registry() {
        Registry.register(Registry.ITEM, new Identifier(CitiesMod.MOD_ID, "phone"), PHONE_ITEM);
    }
}
