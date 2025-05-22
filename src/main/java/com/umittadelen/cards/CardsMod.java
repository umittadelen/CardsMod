package com.umittadelen.cards;

import net.fabricmc.api.ModInitializer;

import com.umittadelen.cards.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardsMod implements ModInitializer {
	public static final String MOD_ID = "cards-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}