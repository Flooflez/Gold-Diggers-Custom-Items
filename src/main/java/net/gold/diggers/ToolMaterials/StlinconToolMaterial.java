package net.gold.diggers.ToolMaterials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class StlinconToolMaterial implements ToolMaterial{

    @Override
    public int getDurability() {

        return 3569;
    }

    @Override
    public float getMiningSpeedMultiplier() {

        return 15;
    }

    @Override
    public float getAttackDamage() {

        return 1;
    }

    @Override
    public int getMiningLevel() {

        return 4;
    }

    @Override
    public int getEnchantability() {

        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
       
        return Ingredient.ofItems(Items.BAMBOO);
    }
    
}