package jp.axer.CocoaInput.adapters;

import net.minecraft.client.font.TextRenderer;

public interface TextFieldInterface extends AbstractButtonWidgetInterface{
    public void setFocusedTicks(int ft);
    public void setFocus(boolean bl);
    public TextRenderer getTextRenderer();
}
