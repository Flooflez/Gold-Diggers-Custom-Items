package net.gold.diggers.Others;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class NetheriteStick extends Item{
    public NetheriteStick() {
        super(new Item.Settings().group(ItemGroup.MISC).fireproof());
    }
}
