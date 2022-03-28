package net.gold.diggers.Tools.GGbaoleTools;

import net.gold.diggers.entities.BulletEntity;
import net.gold.diggers.entities.TNTStickEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class GGbaoleGun extends Item{
    public GGbaoleGun() {
        super(new Item.Settings().group(ItemGroup.COMBAT).fireproof());
    }

    public boolean scoped = true;


    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        System.out.println("hello beans on toast");
        if (user instanceof PlayerEntity) {
            ((PlayerEntity) user).getItemCooldownManager().set(this, 20);

            if (!world.isClient) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5F, 1F);

                BulletEntity bulletEntity = new BulletEntity(user, world);
                //axeEntity.setItem(itemStack);
                bulletEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 100F, 0F);

                if (scoped) {
                    world.spawnEntity(bulletEntity); // spawns entity
                } else {
                    Random random = new Random();
                    float offset = 3f;
                    bulletEntity.updatePosition(user.getX() - 0.1 * MathHelper.sin(2 * MathHelper.PI * (user.getYaw() + (random.nextFloat() /offset) )/ 360.0f), user.getEyeY() - 0.1 * MathHelper.sin(2 * MathHelper.PI * (user.getPitch()+ (random.nextFloat() /offset)) / 360.0f), user.getZ() + 0.1 * MathHelper.cos(2 * MathHelper.PI * (user.getYaw() + (random.nextFloat() /offset)) / 360.0f));
                    world.spawnEntity(bulletEntity); // spawns entity
                }


            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        System.out.println("WORK PLS");

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }
}
