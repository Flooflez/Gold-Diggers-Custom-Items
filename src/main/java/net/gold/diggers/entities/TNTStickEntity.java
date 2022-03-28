package net.gold.diggers.entities;

import net.gold.diggers.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class TNTStickEntity extends PersistentProjectileEntity{

    public TNTStickEntity(EntityType<? extends TNTStickEntity> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
    }

    public TNTStickEntity(World world, double x, double y, double z) {
        super(EntityInit.TNTStickEntityType, x, y, z, world);
    }

    public TNTStickEntity(World world, LivingEntity owner) {
        super(EntityInit.TNTStickEntityType, owner, world);
    }


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        
    }

    @Override
    protected ItemStack asItemStack() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        world.createExplosion(null, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z, 3, false, Explosion.DestructionType.DESTROY);
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        world.createExplosion(null, blockHitResult.getPos().x, blockHitResult.getPos().y, blockHitResult.getPos().z, 3, false, Explosion.DestructionType.DESTROY);
        this.discard();
    }
}
