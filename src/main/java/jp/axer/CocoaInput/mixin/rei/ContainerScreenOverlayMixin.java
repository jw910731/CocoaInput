package jp.axer.CocoaInput.mixin.rei;

import jp.axer.CocoaInput.adapters.TextFieldInterface;
import jp.axer.CocoaInput.adapters.rei.TextFieldWidgetInterface;
import jp.axer.CocoaInput.wrapper.TextFieldWrapper;
import me.shedaniel.rei.gui.ContainerScreenOverlay;
import me.shedaniel.rei.gui.widget.WidgetWithBounds;
import me.shedaniel.rei.impl.ScreenHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ContainerScreenOverlay.class, remap = false)
public abstract class ContainerScreenOverlayMixin extends WidgetWithBounds {
    @Inject(method="init()V", at=@At("TAIL"))
    protected void init(CallbackInfo cb){
        ((TextFieldWidgetInterface)ScreenHelper.getSearchField()).initWrapper();
    }
}
