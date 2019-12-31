package jp.axer.CocoaInput.adapters;

import jp.axer.CocoaInput.util.Rect;
import net.minecraft.client.font.TextRenderer;

public interface TextFieldInterface extends AbstractButtonWidgetInterface{
    void setFocusedTicks(int ft);
    void setFocus(boolean bl);
    TextRenderer getTextRenderer();
    int getCursor();
    void setText(String s);
    void setCursor(int c);
    String getText();
    Rect getRect(int originalCursorPosition);
}
