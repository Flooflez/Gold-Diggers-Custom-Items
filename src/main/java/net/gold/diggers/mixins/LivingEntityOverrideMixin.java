package net.gold.diggers.mixins;


import net.gold.diggers.Tools.GGbaoleTools.GGbaoleGun;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityOverrideMixin {

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At("HEAD"), cancellable = true)
    private void onSwing(Hand hand, boolean bl, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandStack();
        if (!itemStack.isEmpty()){
            if(itemStack.getItem() instanceof GGbaoleGun ggbaoleGun){
                System.out.println("is ggbaolegun!");



                ci.cancel();
            }
        }
    }

}