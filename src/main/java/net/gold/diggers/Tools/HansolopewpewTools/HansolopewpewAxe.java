package net.gold.diggers.Tools.HansolopewpewTools;

import net.gold.diggers.ToolMaterials.HansolopewpewToolMaterial;
import net.gold.diggers.entities.HansolopewpewAxeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HansolopewpewAxe extends AxeItem{
    public HansolopewpewAxe() {
        super(new HansolopewpewToolMaterial(), +3, -3f, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
        //super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        ItemStack itemStack = playerEntity.getStackInHand(hand);


        playerEntity.playSound(SoundEvents.ENCHANT_THORNS_HIT, 1.0F, 1.0F);
        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENCHANT_THORNS_HIT, SoundCategory.NEUTRAL, 0.5F, 1F);
        //sound file, volume (max 1), pitch ?

        playerEntity.getItemCooldownManager().set(this, 300);


        if (!world.isClient) {
			HansolopewpewAxeEntity axeEntity = new HansolopewpewAxeEntity(world, playerEntity);
			//axeEntity.setItem(itemStack);
			axeEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2F, 0F);
			world.spawnEntity(axeEntity); // spawns entity

            if (!playerEntity.getAbilities().creativeMode) {
                playerEntity.getInventory().removeOne(itemStack);
            }

            return TypedActionResult.consume(itemStack);
		}

        //itemStack.setDamage( itemStack.getDamage()+100);

        return TypedActionResult.fail(itemStack);


        //return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
    
}
