package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.arch.darwin.Handle;
import jp.axer.CocoaInput.plugin.IMEReceiver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "openScreen", at=@At("HEAD"))
    private void openScreen(Screen screen, CallbackInfo cb){
        if(!(screen instanceof IMEReceiver)) {
            Handle.INSTANCE.refreshInstance();
        }
    }
}
