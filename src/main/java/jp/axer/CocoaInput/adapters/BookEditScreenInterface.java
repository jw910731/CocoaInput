package jp.axer.CocoaInput.adapters;

import net.minecraft.client.font.TextRenderer;

public interface BookEditScreenInterface extends ScreenInterface{
    boolean getSigning();
    int getCurrentPage();
    String getTitleTexts();
    void setTitle(String new_str);
    String getCurrentPageContent_();
    void setCurrentPageContent(String newContent);
}
