package net.gold.diggers.Others;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class RoastedCarrot extends Item{

    public RoastedCarrot() {
        super(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(5).saturationModifier(6f).build()));
    }
    
}
