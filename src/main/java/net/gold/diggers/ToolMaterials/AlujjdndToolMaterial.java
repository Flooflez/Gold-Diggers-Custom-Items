package net.gold.diggers.ToolMaterials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AlujjdndToolMaterial implements ToolMaterial{

    @Override
    public int getDurability() {
        // TODO Auto-generated method stub
        return 3569;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        // TODO Auto-generated method stub
        return 15;
    }

    @Override
    public float getAttackDamage() {
        // TODO Auto-generated method stub
        return 6;
    }

    @Override
    public int getMiningLevel() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    public int getEnchantability() {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        // TODO Auto-generated method stub
        return Ingredient.ofItems(Items.BAMBOO);
    }
    
}