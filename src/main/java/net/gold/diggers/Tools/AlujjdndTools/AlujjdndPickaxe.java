package net.gold.diggers.Tools.AlujjdndTools;

import net.gold.diggers.ToolMaterials.AlujjdndToolMaterial;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AlujjdndPickaxe extends PickaxeItem{

    public AlujjdndPickaxe() {
        super(new AlujjdndToolMaterial(), -1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
        //super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        //playerEntity.playSound(SoundEvents.ENTITY_PANDA_SNEEZE, 1.0F, 1.0F);
        //sound file, volume (max 1), pitch ?

        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_PANDA_SNEEZE, SoundCategory.NEUTRAL, 0.5F, 1F);

        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 1));
        // effect, duration, amplifier

        playerEntity.getItemCooldownManager().set(this, 1200);

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
    
}
