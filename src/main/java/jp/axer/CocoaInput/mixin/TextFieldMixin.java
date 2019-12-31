package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.adapters.TextFieldInterface;
import jp.axer.CocoaInput.util.Rect;
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

    @Shadow(prefix = "shadow$")
    public abstract int shadow$getCursor();

    @Shadow(prefix = "shadow$")
    public abstract void shadow$setText(String s);

    @Shadow(prefix = "shadow$")
    public abstract void shadow$setCursor(int c);

    @Shadow(prefix = "shadow$")
    public abstract String shadow$getText();


    private TextFieldMixin(int x, int y, String text) {
        super(x, y, text);
    }

    @Inject(at=@At("RETURN"), method="<init>*")
    protected void constructor(CallbackInfo cb){
        wrapper = new TextFieldWrapper((TextFieldInterface) this);
    }

    @Inject(method = "onFocusedChanged", at=@At("TAIL"))
    protected void onFocusedChanged(boolean bl, CallbackInfo cb){
        this.setFocused(bl);
    }

    @Override
    public void setFocus(boolean bl){
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

    @Override
    public Rect getRect(int originalCursorPosition){
        return new Rect(//{x,y}
                (this.getTextRenderer().getStringWidth(this.getText().substring(0, originalCursorPosition)) + this.x),
                (this.getTextRenderer().fontHeight + this.y),
                this.getWidth(),
                this.getHeight()
        );
    }

    @Override
    public int getCursor(){
        return shadow$getCursor();
    }

    @Override
    public void setText(String s){
        shadow$setText(s);
    }

    @Override
    public void setCursor(int c){
        shadow$setCursor(c);
    }

    @Override
    public String getText(){
        return shadow$getText();
    }
}
