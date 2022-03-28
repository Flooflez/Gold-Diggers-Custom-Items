package net.gold.diggers.entities;

import net.gold.diggers.EntityInit;
import net.gold.diggers.ItemInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class HansolopewpewAxeEntity extends PersistentProjectileEntity{
	private final ItemStack axeStack = new ItemStack(ItemInit.HANSOLOPEWPEW_AXE);
	//private boolean shouldReturn = false;

	public HansolopewpewAxeEntity(EntityType<? extends HansolopewpewAxeEntity> entityType, World world) {
        super(entityType, world);
    }

    public HansolopewpewAxeEntity(World world, double x, double y, double z) {
        super(EntityInit.HansolopewpewAxeEntityType, x, y, z, world);
    }

    public HansolopewpewAxeEntity(World world, LivingEntity owner) {
        super(EntityInit.HansolopewpewAxeEntityType, owner, world);
    }


    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1, ParticleTypes.LAVA);
                }
            } else {
                this.spawnParticles(2, ParticleTypes.FALLING_LAVA);
            }
        } else{
			
			if (this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 100) {
				Entity entity = this.getOwner();
				//shouldReturn = true;
				if (!this.isOwnerAlive() || entity == null) {
					if (!this.world.isClient && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
						this.dropStack(this.asItemStack(), 0.1f);
					}
					this.discard();
				}
				else{
					this.setPos(entity.getEyePos().getX(), entity.getEyePos().getY(), entity.getEyePos().getZ());
				}
				
			}
		}
		
    }

    private void spawnParticles(int amount, ParticleEffect type) {
        double d = (double)(16 & 0xFF) / 255.0;
        double e = (double)(8 & 0xFF) / 255.0;
        double f = (double)(0) / 255.0;
        for (int j = 0; j < amount; ++j) {
            this.world.addParticle(type, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemInit.HANSOLOPEWPEW_AXE);
		//return new ItemStack(Items.ACACIA_BOAT);
    }

	@Override
	protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
    }

	@Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

	@Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity2;
        Entity entity = entityHitResult.getEntity();
        float f = 8.0f;
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getAttackDamage(this.axeStack, livingEntity.getGroup());
        }
        DamageSource damageSource = new ProjectileDamageSource("arrow", this,(entity2 = this.getOwner()) == null ? this : entity2).setProjectile();
        if (entity.damage(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }
                this.onHit(livingEntity2);
                if (entity != entity2 && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity) entity2).networkHandler.sendPacket(
                            new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, 0.0F));
                }
            }
        }
        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));



        this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0f, 1.0f);
    }

	private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity == null || !entity.isAlive()) {
            return false;
        }
        return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
    }
}
