package jp.axer.CocoaInput.mixin;

import jp.axer.CocoaInput.adapters.ScreenInterface;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Screen.class)
public abstract class ScreenMixin extends AbstractParentElement implements ScreenInterface {
    @Shadow
    protected TextRenderer font;

    @Override
    public TextRenderer getFont(){
        return font;
    }
}
