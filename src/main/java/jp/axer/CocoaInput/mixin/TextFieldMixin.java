package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.plugin.TextFieldInterface;
import jp.axer.CocoaInput.wrapper.TextFieldWrapper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextFieldWidget.class)
public abstract class TextFieldMixin extends AbstractButtonWidget implements TextFieldInterface {
    TextFieldWrapper wrapper;

    @Shadow
    private int focusedTicks;

    @Shadow
    private TextRenderer textRenderer;

    private TextFieldMixin(int x, int y, String text) {
        super(x, y, text);
    }

    @Inject(at=@At("RETURN"), method="<init>*")
    protected void constructor(CallbackInfo cb){
        wrapper = new TextFieldWrapper((TextFieldWidget)(Object)this);
    }

    @Inject(method = "onFocusedChanged", at=@At("TAIL"))
    protected void onFocusedChanged(boolean bl, CallbackInfo cb){
        this.setFocused(bl);
    }

    /*
     * implemented TextFieldInterface
     * mainly called by superclass
     */
    @Override
    public void setFocused(boolean bl){
        wrapper.setFocused(bl);
    }

    @Override
    public TextRenderer getTextRenderer(){
        return textRenderer;
    }

    @Override
    public void setFocusedTicks(int ft) {
        this.focusedTicks = ft;
    }
}
