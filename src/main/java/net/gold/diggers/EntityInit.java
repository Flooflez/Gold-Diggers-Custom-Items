package net.gold.diggers;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.gold.diggers.entities.BulletEntity;
import net.gold.diggers.entities.TNTStickEntity;
import net.gold.diggers.entities.HansolopewpewAxeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit {
    public static EntityType<HansolopewpewAxeEntity> HansolopewpewAxeEntityType;
    public static EntityType<TNTStickEntity> TNTStickEntityType;
    public static EntityType<BulletEntity> BulletEntityType;
    
    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(GoldDiggersCustomItems.MODID,name), entityType);
    }

    private static <T extends Entity> EntityType<T> createArrowEntityType(EntityType.EntityFactory<T> factory) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }

    private static <T extends Entity> EntityType<T> createBulletEntityType(EntityType.EntityFactory<T> factory) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.fixed(0.2f, 0.2f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }

    public static void init() {
        HansolopewpewAxeEntityType = register("hansolopewpew_axe_entity", createArrowEntityType(HansolopewpewAxeEntity::new));
        TNTStickEntityType = register("tnt_stick_entity", createArrowEntityType(TNTStickEntity::new));
        BulletEntityType = register("bullet_entity", createBulletEntityType(BulletEntity::new));
    }
}
