import os
import json

# Card info
colors = ["red", "blue", "green", "yellow"]
special_cards = ["block", "reverse"]
card_types = [f"w{i}" for i in range(10)] + ["wx2"] + special_cards


# Base paths
model_output_dir = "generate_json_models/uno"
item_model_output_dir = "generate_json_models/items"
os.makedirs(model_output_dir, exist_ok=True)
os.makedirs(item_model_output_dir, exist_ok=True)

# Texture mappings for color fronts
front_textures = {
    "red": "f_red",
    "blue": "f_blue",
    "green": "f_green",
    "yellow": "f_yellow"
}

# Generate JSON models
model_data = []
for color in colors:
    for card in card_types:
        name = f"uno_{color}_{card}"

        model = {
            "parent": "cards-mod:item/uno_base",
            "textures": {
                "front": f"cards-mod:item/uno/{front_textures[color]}",
                "side": f"cards-mod:item/numbers/{card}",
                "middle": "#missing",
                "joker": "#missing"
            }
        }

        # Decide where to place the card texture
        if card in special_cards:
            model["textures"]["joker"] = f"cards-mod:item/uno/numbers/{card}"
            model["textures"]["middle"] = f"#missing"
        else:
            model["textures"]["middle"] = f"cards-mod:item/numbers/{card}"
            model["textures"]["joker"] = f"#missing"

        # Item model
        item_model = {
            "model": {
                "type": "minecraft:model",
                "model": f"cards-mod:item/uno/{name}",
            }
        }

        # Write model JSON
        path = os.path.join(model_output_dir, f"{name}.json")
        with open(path, "w") as f:
            json.dump(model, f, indent=4)

        # Write item JSON
        path = os.path.join(item_model_output_dir, f"{name}.json")
        with open(path, "w") as f:
            json.dump(item_model, f, indent=4)

        model_data.append((name, model))
