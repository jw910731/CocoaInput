package jp.axer.CocoaInput.mixin.rei;

import jp.axer.CocoaInput.adapters.TextFieldInterface;
import jp.axer.CocoaInput.util.Rect;
import jp.axer.CocoaInput.wrapper.TextFieldWrapper;
import me.shedaniel.math.api.Rectangle;
import me.shedaniel.rei.gui.widget.TextFieldWidget;
import me.shedaniel.rei.gui.widget.WidgetWithBounds;
import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TextFieldWidget.class, remap = false)
public abstract class TextFieldWidgetMixin extends WidgetWithBounds implements TextFieldInterface {

    TextFieldWrapper wrapper;

    @Shadow
    private int focusedTicks;

    @Shadow
    public abstract Rectangle getBounds();

    @Inject(at=@At("RETURN"), method="<init>*")
    protected void constructor(CallbackInfo cb){
        wrapper = new TextFieldWrapper((TextFieldInterface) (Object)this);
    }

    @Override
    public void setFocusedTicks(int ft){
        focusedTicks = ft;
    }

    @Override
    public void setFocus(boolean bl){
        wrapper.setFocused(bl);
    }

    @Inject(method="setFocused", at=@At("HEAD"))
    protected void setFocused(boolean bl, CallbackInfo cb){
        this.setFocus(bl);
    }

    @Override
    public TextRenderer getTextRenderer(){
        return font;
    }

    @Override
    public int getHeight(){
        return this.getBounds().getHeight();
    }

    @Override
    public Rect getRect(int originalCursorPosition){
        Rectangle bound = this.getBounds();
        return new Rect(bound.x, bound.y, bound.width, bound.height);
    }
}
