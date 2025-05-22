package com.umittadelen.cards.item;

import java.util.function.Function;

import com.umittadelen.cards.CardsMod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    // Uno Cards ------------------------>
    private static final String[] UNO_CARD_NAMES = {
        "uno_blue_block",
        "uno_blue_reverse",
        "uno_blue_w0",
        "uno_blue_w1",
        "uno_blue_w2",
        "uno_blue_w3",
        "uno_blue_w4",
        "uno_blue_w5",
        "uno_blue_w6",
        "uno_blue_w7",
        "uno_blue_w8",
        "uno_blue_w9",
        "uno_blue_wx2",
        "uno_green_block",
        "uno_green_reverse",
        "uno_green_w0",
        "uno_green_w1",
        "uno_green_w2",
        "uno_green_w3",
        "uno_green_w4",
        "uno_green_w5",
        "uno_green_w6",
        "uno_green_w7",
        "uno_green_w8",
        "uno_green_w9",
        "uno_green_wx2",
        "uno_red_block",
        "uno_red_reverse",
        "uno_red_w0",
        "uno_red_w1",
        "uno_red_w2",
        "uno_red_w3",
        "uno_red_w4",
        "uno_red_w5",
        "uno_red_w6",
        "uno_red_w7",
        "uno_red_w8",
        "uno_red_w9",
        "uno_red_wx2",
        "uno_yellow_block",
        "uno_yellow_reverse",
        "uno_yellow_w0",
        "uno_yellow_w1",
        "uno_yellow_w2",
        "uno_yellow_w3",
        "uno_yellow_w4",
        "uno_yellow_w5",
        "uno_yellow_w6",
        "uno_yellow_w7",
        "uno_yellow_w8",
        "uno_yellow_w9",
        "uno_yellow_wx2",
        "uno_wild_color",
        "uno_wild_x4",
        "uno_wild"
    };

    public static final Item[] UNO_CARDS = new Item[UNO_CARD_NAMES.length];
    static {
        for (int i = 0; i < UNO_CARD_NAMES.length; i++) {
            UNO_CARDS[i] = registerItem(UNO_CARD_NAMES[i], Item::new, new Item.Settings());
        }
    }

    public static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings setting) {
        final RegistryKey<Item> registerKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CardsMod.MOD_ID, name));
        return Items.register(registerKey, factory, setting);
    }

    public static void registerItemGroup() {
        Registry.register(
            Registries.ITEM_GROUP,
            UNO_CARDS_GROUP,
            net.minecraft.item.ItemGroup.create(
                ItemGroup.Row.TOP, // or BOTTOM, as you wish
                0 // position in the row
            )
            .displayName(Text.translatable("itemGroup.cards-mod.uno_cards_group"))
            .icon(() -> new ItemStack(UNO_CARDS[0])) // choose an icon
            .build()
        );
    }

    private static void UnoCards(FabricItemGroupEntries entries) {
        for (Item item : UNO_CARDS) {
            entries.add(item);
        }
    }

    // new ItemGroup for uno cards
    public static final RegistryKey<ItemGroup> UNO_CARDS_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(CardsMod.MOD_ID, "uno_cards_group"));

    public static void registerModItems(){
        registerItemGroup();
        ItemGroupEvents.modifyEntriesEvent(UNO_CARDS_GROUP).register(ModItems::UnoCards);
    }
}