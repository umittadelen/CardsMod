import os
import json

# Setup card suits and ranks (both red and black!)
suits = {
    "heart": "r",     # red
    "diamond": "r",   # red
    "club": "b",      # black
    "spade": "b"      # black
}

ranks = ["a"] + [str(i) for i in range(2, 11)] + ["j", "q", "k"]

# Output folders
model_output_dir = "generate_json_models/playing_cards"
item_model_output_dir = "generate_json_models/items"
os.makedirs(model_output_dir, exist_ok=True)
os.makedirs(item_model_output_dir, exist_ok=True)

# Generate all the card models~!
for suit, color_prefix in suits.items():
    for rank in ranks:
        card_id = f"playing_{suit}_{rank}"
        texture_id = f"{color_prefix}{rank}"

        model = {
            "parent": "cards-mod:item/playing_cards_base",
            "textures": {
                "base-symbol": f"cards-mod:item/playing_cards/{suit}",
                "base-number": f"cards-mod:item/numbers/{texture_id}",
                "4-5-6-7-8-9-10": f"cards-mod:item/playing_cards/{suit}" if rank in ["4", "5", "6", "7", "8", "9", "10"] else f"#missing",
                "2-3": f"cards-mod:item/playing_cards/{suit}" if rank in ["2", "3"] else "#missing",
                "6-7-8": f"cards-mod:item/playing_cards/{suit}" if rank in ["6", "7", "8"] else "#missing",
                "A-3-5-9": f"cards-mod:item/playing_cards/{suit}" if rank in ["a", "3", "5", "9"] else "#missing",
                "9-10": f"cards-mod:item/playing_cards/{suit}" if rank in ["9", "10"] else "#missing",
                "7-8-10": f"cards-mod:item/playing_cards/{suit}" if rank in ["7", "8", "10"] else "#missing",
                "8-10": f"cards-mod:item/playing_cards/{suit}" if rank in ["8", "10"] else "#missing",
            }
        }

        # Item model wrapper
        item_model = {
            "model": {
                "type": "minecraft:model",
                "model": f"cards-mod:item/playing_cards/{card_id}",
            }
        }

        # Write the model JSON~ 🧸
        with open(os.path.join(model_output_dir, f"{card_id}.json"), "w") as f:
            json.dump(model, f, indent=4)

        # Write the item model JSON~ 💫
        with open(os.path.join(item_model_output_dir, f"{card_id}.json"), "w") as f:
            json.dump(item_model, f, indent=4)
