package net.gold.diggers.Tools.StlinconTools;

import net.gold.diggers.ToolMaterials.StlinconToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import static net.minecraft.util.hit.HitResult.Type.*;

import java.util.Random;

public class StlinconHoe extends HoeItem{

    public StlinconHoe() {
        super(new StlinconToolMaterial(), -1, +1f, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult hit = user.raycast(100.0D, 1.0F, false);
        ItemStack itemStack = user.getStackInHand(hand);

        user.getItemCooldownManager().set(this, 600);

        user.playSound(SoundEvents.ENTITY_VILLAGER_CELEBRATE, 1.0F, 1.0F);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_VILLAGER_CELEBRATE, SoundCategory.NEUTRAL, 0.5F, 1F);
        //sound file, volume (max 1), pitch ?

        Random random = new Random();
        double d = (double)(16 & 0xFF) / 255.0;
        double e = (double)(8 & 0xFF) / 255.0;
        double f = (double)(0) / 255.0;
        int amount = 10;
        for (int j = 0; j < amount; ++j) {
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, user.getX() + (2.0 * random.nextDouble() - 1.0), user.getY() + 1f, user.getZ()+ (2.0 * random.nextDouble() - 1.0), d, e, f);
        }

        if (hit.getType() == MISS)
            return TypedActionResult.fail(itemStack);
        else {
            world.createExplosion(null, hit.getPos().x, hit.getPos().y, hit.getPos().z, 3, false, Explosion.DestructionType.DESTROY);

            double x = hit.getPos().x;
            double y = hit.getPos().y;
            double z = hit.getPos().z;

            world.setBlockState(new BlockPos(x, y, z), Blocks.FARMLAND.getDefaultState(), Block.NOTIFY_ALL);
            world.setBlockState(new BlockPos(x, y-1, z), Blocks.WATER.getDefaultState(), Block.NOTIFY_ALL);

            int randomInt = random.nextInt(3);

            switch(randomInt){
                case 0:
                    world.setBlockState(new BlockPos(x, y+1, z), Blocks.CARROTS.getDefaultState(), Block.NOTIFY_ALL);
                    break;
                case 1:
                    world.setBlockState(new BlockPos(x, y+1, z), Blocks.WHEAT.getDefaultState(), Block.NOTIFY_ALL);
                    break;
                case 2:
                    world.setBlockState(new BlockPos(x, y+1, z), Blocks.POTATOES.getDefaultState(), Block.NOTIFY_ALL);
                    break;
            }

            itemStack.setDamage( itemStack.getDamage()+200);

            if(itemStack.getDamage() <= 0){
                user.getInventory().removeOne(itemStack);
            }
        }

        return TypedActionResult.pass(itemStack);
    }
    
    
}
