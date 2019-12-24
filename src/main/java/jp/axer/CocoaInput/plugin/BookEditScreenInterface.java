package jp.axer.CocoaInput.plugin;

import net.minecraft.client.font.TextRenderer;

public interface BookEditScreenInterface {
    boolean getSigning();
    int getCurrentPage();
    String getTitleTexts();
    void setTitle(String new_str);
    String getCurrentPageContent_();
    void setCurrentPageContent(String newContent);
    TextRenderer getFont();
}
