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
        // Blue Cards
        "uno_blue_block", "uno_blue_reverse", "uno_blue_w0", "uno_blue_w1", "uno_blue_w2",
        "uno_blue_w3", "uno_blue_w4", "uno_blue_w5", "uno_blue_w6", "uno_blue_w7",
        "uno_blue_w8", "uno_blue_w9", "uno_blue_wx2",
        // Green Cards
        "uno_green_block", "uno_green_reverse", "uno_green_w0", "uno_green_w1", "uno_green_w2",
        "uno_green_w3", "uno_green_w4", "uno_green_w5", "uno_green_w6", "uno_green_w7",
        "uno_green_w8", "uno_green_w9", "uno_green_wx2",
        // Red Cards
        "uno_red_block", "uno_red_reverse", "uno_red_w0", "uno_red_w1", "uno_red_w2",
        "uno_red_w3", "uno_red_w4", "uno_red_w5", "uno_red_w6", "uno_red_w7",
        "uno_red_w8", "uno_red_w9", "uno_red_wx2",
        // Yellow Cards
        "uno_yellow_block", "uno_yellow_reverse", "uno_yellow_w0", "uno_yellow_w1", "uno_yellow_w2",
        "uno_yellow_w3", "uno_yellow_w4", "uno_yellow_w5", "uno_yellow_w6", "uno_yellow_w7",
        "uno_yellow_w8", "uno_yellow_w9", "uno_yellow_wx2",
        // Wild Cards
        "uno_wild_color", "uno_wild_x4", "uno_wild"
    };

    private static final String[] PLAYING_CARD_NAMES = {
        // clubs
        "playing_club_2", "playing_club_3", "playing_club_4", "playing_club_5", "playing_club_6",
        "playing_club_7", "playing_club_8", "playing_club_9", "playing_club_10", "playing_club_a",
        "playing_club_j", "playing_club_q", "playing_club_k",
        // diamonds
        "playing_diamond_2", "playing_diamond_3", "playing_diamond_4", "playing_diamond_5", "playing_diamond_6",
        "playing_diamond_7", "playing_diamond_8", "playing_diamond_9", "playing_diamond_10", "playing_diamond_a",
        "playing_diamond_j", "playing_diamond_q", "playing_diamond_k",
        // hearts
        "playing_heart_2", "playing_heart_3", "playing_heart_4", "playing_heart_5", "playing_heart_6",
        "playing_heart_7", "playing_heart_8", "playing_heart_9", "playing_heart_10", "playing_heart_a",
        "playing_heart_j", "playing_heart_q", "playing_heart_k",
        // spades
        "playing_spade_2", "playing_spade_3", "playing_spade_4", "playing_spade_5", "playing_spade_6",
        "playing_spade_7", "playing_spade_8", "playing_spade_9", "playing_spade_10", "playing_spade_a",
        "playing_spade_j", "playing_spade_q", "playing_spade_k",
        // joker
        "playing_joker"
    };

    public static final Item[] UNO_CARDS = new Item[UNO_CARD_NAMES.length];
    static {
        for (int i = 0; i < UNO_CARD_NAMES.length; i++) {
            UNO_CARDS[i] = registerItem(UNO_CARD_NAMES[i], Item::new, new Item.Settings());
        }
    }

    public static final Item[] PLAYING_CARDS = new Item[PLAYING_CARD_NAMES.length];
    static {
        for (int i = 0; i < PLAYING_CARD_NAMES.length; i++) {
            PLAYING_CARDS[i] = registerItem(PLAYING_CARD_NAMES[i], Item::new, new Item.Settings());
        }
    }

    public static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings setting) {
        final RegistryKey<Item> registerKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CardsMod.MOD_ID, name));
        return Items.register(registerKey, factory, setting);
    }

    public static void registerItemGroup(RegistryKey<ItemGroup> GROUP, Item[] items, String name) {
        Registry.register(
            Registries.ITEM_GROUP,
            GROUP,
            net.minecraft.item.ItemGroup.create(
                ItemGroup.Row.TOP, // or BOTTOM, as you wish
                0 // position in the row
            )
            .displayName(Text.translatable("itemGroup.cards-mod." + name))
            .icon(() -> new ItemStack(items[0])) // choose an icon
            .build()
        );
    }

    private static void UnoCards(FabricItemGroupEntries entries) {
        for (Item item : UNO_CARDS) {
            entries.add(item);
        }
    }

    private static void PlayingCards(FabricItemGroupEntries entries) {
        for (Item item : PLAYING_CARDS) {
            entries.add(item);
        }
    }

    // new ItemGroup for uno cards
    public static final RegistryKey<ItemGroup> UNO_CARDS_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(CardsMod.MOD_ID, "uno_cards_group"));
    // new ItemGroup for playing cards
    public static final RegistryKey<ItemGroup> PLAYING_CARDS_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(CardsMod.MOD_ID, "playing_cards_group"));

    public static void registerModItems(){
        registerItemGroup(UNO_CARDS_GROUP, UNO_CARDS, "uno_cards_group");
        ItemGroupEvents.modifyEntriesEvent(UNO_CARDS_GROUP).register(ModItems::UnoCards);
        registerItemGroup(PLAYING_CARDS_GROUP, PLAYING_CARDS, "playing_cards_group");
        ItemGroupEvents.modifyEntriesEvent(PLAYING_CARDS_GROUP).register(ModItems::PlayingCards);
    }
}