package net.gold.diggers.Tools.FrozaxTools;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class FrozaxShield extends ShieldItem{

    public FrozaxShield() {
        super(new Item.Settings().group(ItemGroup.COMBAT).fireproof().maxDamageIfAbsent(12000));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {

        if(remainingUseTicks == 71999){ //THIS IS AWFUL HORRIBLE CODE I'M SO SORRY
            Random random = new Random();
            double d = (double)(16 & 0xFF) / 255.0;
            double e = (double)(8 & 0xFF) / 255.0;
            double f = (double)(0) / 255.0;
            int amount = 10;
            for (int j = 0; j < amount; ++j) {
                world.addParticle(ParticleTypes.SQUID_INK, user.getX() + (2.0 * random.nextDouble() - 1.0), user.getY() + 0.75f, user.getZ()+ (2.0 * random.nextDouble() - 1.0), d, e, f);
            }
        }

        StatusEffect effect = StatusEffects.INVISIBILITY;

        if (!user.hasStatusEffect(effect)) {
            user.addStatusEffect(new StatusEffectInstance(effect, 10, 0, false, false));
        } else if (user.getActiveStatusEffects().get(effect).getDuration() == 1) {
            user.addStatusEffect(new StatusEffectInstance(effect, 10, 0, false, false));
        }

        stack.setDamage( stack.getDamage()+1);

        if(stack.getDamage() <= 0){
            stack.decrement(1);
        }
    }

    /**
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        double d = (double)(16 & 0xFF) / 255.0;
        double e = (double)(8 & 0xFF) / 255.0;
        double f = (double)(0) / 255.0;
        int amount = 5;
        for (int j = 0; j < amount; ++j) {
            world.addParticle(ParticleTypes.SQUID_INK, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), d, e, f);
        }
        
        return new TypedActionResult<ItemStack>(ActionResult.FAIL, playerEntity.getStackInHand(hand));
    }
    */
    
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.TINTED_GLASS);
    }

}
