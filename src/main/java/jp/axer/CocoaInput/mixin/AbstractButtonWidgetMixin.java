package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.adapters.AbstractButtonWidgetInterface;
import jp.axer.CocoaInput.adapters.TextFieldInterface;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractButtonWidget.class)
public abstract class AbstractButtonWidgetMixin extends DrawableHelper implements AbstractButtonWidgetInterface {
    @Shadow
    protected int height;

    @Inject(method="setFocused", at=@At("HEAD"))
    private void setFocused(boolean bl, CallbackInfo cb){
        if(this instanceof TextFieldInterface){
            ((TextFieldInterface)this).setFocus(bl);
        }
    }

    @Override
    public int getHeight(){
        return height;
    }
}
