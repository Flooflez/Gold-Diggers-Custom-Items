package net.gold.diggers.Others;

import net.gold.diggers.entities.TNTStickEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TNTStick extends Item{

    public TNTStick() {
        super(new Item.Settings().group(ItemGroup.COMBAT).maxCount(16));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        ItemStack itemStack = playerEntity.getStackInHand(hand);


        //playerEntity.playSound(SoundEvents.ENTITY_EGG_THROW, 1.0F, 1.0F);
        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 1F);
        //sound file, volume (max 1), pitch ?


        if (!world.isClient) {
			TNTStickEntity stickEntity = new TNTStickEntity(world, playerEntity);
			//axeEntity.setItem(itemStack);
			stickEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2F, 0F);
			world.spawnEntity(stickEntity); // spawns entity

            if (!playerEntity.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
		}

        //itemStack.setDamage( itemStack.getDamage()+100);

        return TypedActionResult.fail(itemStack);


        //return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
    
}
