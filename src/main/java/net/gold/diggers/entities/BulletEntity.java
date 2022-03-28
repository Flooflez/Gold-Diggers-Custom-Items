package net.gold.diggers.entities;

import net.gold.diggers.EntityInit;
import net.gold.diggers.ItemInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BulletEntity extends PersistentProjectileEntity  {

    private int ticksInAir;
    private final static float bulletDamage = 3f;

    public BulletEntity(EntityType<? extends BulletEntity> entityType, World world) {
        super(entityType, world);
        this.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
    }


    protected BulletEntity( double x, double y, double z, World world) {
        super(EntityInit.BulletEntityType, x, y, z, world);
    }

    public BulletEntity(LivingEntity owner, World world) {
        this(owner.getX(), owner.getEyeY() - 0.10000000149011612D, owner.getZ(), world);
        this.setOwner(owner);
        if (owner instanceof PlayerEntity) {
            this.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        }

    }


    @Override
    protected void onHit(LivingEntity living) {
        super.onHit(living);
        if (!(living instanceof PlayerEntity)) {
            living.timeUntilRegen = 0;
            living.setVelocity(0, 0, 0);
        }
    }

    @Override
    public void age() {
        ++this.ticksInAir;
        if (this.ticksInAir >= 40) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, speed, divergence);
        this.ticksInAir = 0;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putShort("life", (short) this.ticksInAir);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        this.ticksInAir = tag.getShort("life");
    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient) {
            double d2 = this.getX() + (this.random.nextDouble()) * (double) this.getWidth() * 0.5D;
            double f2 = this.getZ() + (this.random.nextDouble()) * (double) this.getWidth() * 0.5D;
            this.world.addParticle(ParticleTypes.SMOKE, true, d2, this.getY(), f2, 0, 0, 0);
        }
    }


    @Override
    public boolean hasNoGravity() {
        if (this.isSubmergedInWater()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_STEP;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getType() != HitResult.Type.ENTITY || !entityHitResult.getEntity().isPartOf(entity)) {
            if (!this.world.isClient) {
                this.discard();
            }
        }
        Entity entity2 = this.getOwner();
        DamageSource damageSource2;
        if (entity2 == null) {
            damageSource2 = DamageSource.arrow(this, this);
        } else {
            damageSource2 = DamageSource.arrow(this, entity2);
            if (entity2 instanceof LivingEntity) {
                ((LivingEntity) entity2).onAttacking(entity);
            }
        }
        if (entity.damage(damageSource2, bulletDamage)) {
            if (entity instanceof LivingEntity livingEntity) {
                if (!this.world.isClient && entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity) entity2, livingEntity);
                }

                this.onHit(livingEntity);
                if (livingEntity != entity2 && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity) entity2).networkHandler.sendPacket(
                            new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, 0.0F));
                }
            }
        } else {
            if (!this.world.isClient) {
                this.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    public ItemStack asItemStack() {
        return new ItemStack(ItemInit.BULLET);
    }

}
