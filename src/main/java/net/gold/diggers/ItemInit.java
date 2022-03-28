package net.gold.diggers;

import net.gold.diggers.Others.*;
import net.gold.diggers.Tools.AlujjdndTools.AlujjdndPickaxe;
import net.gold.diggers.Tools.FlooflezTools.FlooflezSword;
import net.gold.diggers.Tools.FrozaxTools.FrozaxShield;
import net.gold.diggers.Tools.GGbaoleTools.GGbaoleGun;
import net.gold.diggers.Tools.HansolopewpewTools.HansolopewpewAxe;
import net.gold.diggers.Tools.StlinconTools.StlinconHoe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    public static final RoastedCarrot ROASTED_CARROT = new RoastedCarrot();
    public static final AlujjdndPickaxe ALUJJDND_PICKAXE = new AlujjdndPickaxe();
    public static final ReinforcedBambooStick REINFORCED_BAMBOO_STICK = new ReinforcedBambooStick();
    public static final Floof FLOOF = new Floof();
    public static final FloofyNetherite FLOOFY_NETHERITE = new FloofyNetherite();
    public static final FlooflezSword FLOOFLEZ_SWORD = new FlooflezSword();
    public static final PaddedBlazeRod PADDED_BLAZE_ROD = new PaddedBlazeRod();
    public static final HansolopewpewAxe HANSOLOPEWPEW_AXE = new HansolopewpewAxe();
    public static final NetheriteStick NETHERITE_STICK = new NetheriteStick();
    public static final FrozaxShield FROZAX_SHIELD = new FrozaxShield();
    public static final GoldRod GOLD_ROD = new GoldRod();
    public static final StlinconHoe STLINCON_HOE = new StlinconHoe();
    public static final TNTStick TNT_STICK = new TNTStick();
    public static final Bullet BULLET = new Bullet();


    public static final GGbaoleGun GGBAOLE_GUN = new GGbaoleGun();

    public static void registerAllItems(){
        String MODID = GoldDiggersCustomItems.MODID;

        Registry.register(Registry.ITEM, new Identifier(MODID, "roasted_carrot"), ROASTED_CARROT);

        Registry.register(Registry.ITEM, new Identifier(MODID, "alujjdnd_pickaxe"),ALUJJDND_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "reinforced_bamboo_stick"), REINFORCED_BAMBOO_STICK);

        Registry.register(Registry.ITEM, new Identifier(MODID, "floof"),FLOOF);
        Registry.register(Registry.ITEM, new Identifier(MODID, "floofy_netherite"), FLOOFY_NETHERITE);    
        Registry.register(Registry.ITEM, new Identifier(MODID, "flooflez_sword"), FLOOFLEZ_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MODID, "padded_blaze_rod"), PADDED_BLAZE_ROD);

        Registry.register(Registry.ITEM, new Identifier(MODID, "hansolopewpew_axe"), HANSOLOPEWPEW_AXE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_stick"), NETHERITE_STICK);

        Registry.register(Registry.ITEM, new Identifier(MODID, "frozax_shield"), FROZAX_SHIELD);
        Registry.register(Registry.ITEM, new Identifier(MODID, "gold_rod"), GOLD_ROD);

        Registry.register(Registry.ITEM, new Identifier(MODID, "stlincon_hoe"), STLINCON_HOE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tnt_stick"), TNT_STICK);

        Registry.register(Registry.ITEM, new Identifier(MODID, "ggbaole_gun"), GGBAOLE_GUN);
        Registry.register(Registry.ITEM, new Identifier(MODID, "bullet"), BULLET);
    }
}
