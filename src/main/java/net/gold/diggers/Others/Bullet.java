package net.gold.diggers.Others;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Bullet extends Item {
    public Bullet() {
        super(new Item.Settings().group(ItemGroup.COMBAT));
    }
}
