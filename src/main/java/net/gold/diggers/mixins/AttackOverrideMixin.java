package net.gold.diggers.mixins;

import net.gold.diggers.Tools.GGbaoleTools.GGbaoleGun;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class AttackOverrideMixin {
    @Shadow
    public ClientPlayerEntity player;

    @Shadow
    public ClientWorld world;

    //@Inject(method = "doAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;swingHand(Lnet/minecraft/util/Hand;)V") ,cancellable = true)
    private void attack(CallbackInfo ci) {

        ItemStack itemStack = this.player.getInventory().getMainHandStack();

        System.out.println("uh I hope this works " + (itemStack.getItem() instanceof GGbaoleGun));

        if(itemStack.getItem() instanceof GGbaoleGun){

            //TODO: SCOPE

            ci.cancel();
            return;
        }
        System.out.println("don't print");

    }
}
