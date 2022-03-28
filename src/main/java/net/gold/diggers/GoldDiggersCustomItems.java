package net.gold.diggers;

import net.fabricmc.api.ModInitializer;

public class GoldDiggersCustomItems implements ModInitializer{

    public final static String MODID = "golddiggers";
    
    //public static final Item HansolopewpewAxe = new HansolopewpewAxe(new HansolopewpewToolMaterial());

    @Override
    public void onInitialize() {
        System.out.println("yeehaw modinitializer");

        ItemInit.registerAllItems();
        EntityInit.init();
    }
    
    
}
