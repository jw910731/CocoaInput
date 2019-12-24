package jp.axer.CocoaInput.adapters;

import net.minecraft.client.font.TextRenderer;

public interface TextFieldInterface extends AbstractButtonWidgetInterface{
    public void setFocusedTicks(int ft);
    public void setFocused(boolean bl);
    public TextRenderer getTextRenderer();
}
