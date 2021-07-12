package github.Louwind.Reload.core.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static github.Louwind.Reload.core.event.ClientWorldEvents.COLOR_CACHE_LOAD;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

    @Shadow
    private ClientWorld world;

    @Inject(method = "onPlayerRespawn", at = @At(value = "NEW", target = "net/minecraft/client/world/ClientWorld"))
    private void onPlayerRespawn(PlayerRespawnS2CPacket packet, CallbackInfo ci) {

        if (this.world != null)
            COLOR_CACHE_LOAD.invoker().onLoad(((AccessorClientWorld) this.world).getColorCache());

    }

    @Inject(method = "onGameJoin", at = @At(value = "NEW", target = "net/minecraft/client/world/ClientWorld"))
    private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {

        if (this.world != null)
            COLOR_CACHE_LOAD.invoker().onLoad(((AccessorClientWorld) this.world).getColorCache());

    }

}