package net.gold.diggers.Tools.FlooflezTools;

import net.gold.diggers.ToolMaterials.FlooflezToolMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class FlooflezSword extends SwordItem{
    public FlooflezSword() {
        super(new FlooflezToolMaterial(), +2, -2.4f, new Item.Settings().group(ItemGroup.COMBAT).fireproof());
        //super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        ItemStack itemStack = playerEntity.getStackInHand(hand);

        //playerEntity.playSound(SoundEvents.ENTITY_CAT_PURREOW, 1.0F, 1.0F);
        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_CAT_PURREOW, SoundCategory.NEUTRAL, 0.5F, 1F);
        //sound file, volume (max 1), pitch ?

        FireballEntity fireball = new FireballEntity(world, playerEntity, 0, 0, 0, 1);
        //fireball.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1.5F, 0F);

        fireball.updatePosition(playerEntity.getX() - 2*MathHelper.sin(2*MathHelper.PI* playerEntity.getYaw()/360.0f), playerEntity.getEyeY() - 2*MathHelper.sin(2*MathHelper.PI* playerEntity.getPitch()/360.0f), playerEntity.getZ() + 2*MathHelper.cos(2*MathHelper.PI* playerEntity.getYaw()/360.0f));
        world.spawnEntity(fireball);

        playerEntity.getItemCooldownManager().set(this, 300);

        itemStack.setDamage( itemStack.getDamage()+100);

        if(itemStack.getDamage() <= 0){
            playerEntity.getInventory().removeOne(itemStack);
        }


        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}
