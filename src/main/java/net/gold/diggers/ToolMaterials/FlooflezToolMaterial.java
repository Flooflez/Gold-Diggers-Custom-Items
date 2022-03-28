package net.gold.diggers.ToolMaterials;

import net.gold.diggers.Others.Floof;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FlooflezToolMaterial implements ToolMaterial{

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

        return 6;
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
       
        return Ingredient.ofItems(new Floof());
    }
    
}