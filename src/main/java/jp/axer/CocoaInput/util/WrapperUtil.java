package jp.axer.CocoaInput.util;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;

import java.lang.reflect.Field;

public class WrapperUtil {
    public static TextRenderer makeFontRenderer(Screen owner) throws Exception {
        Field font = Screen.class.getDeclaredField("textRenderer");
        font.setAccessible(true);
        return (TextRenderer) font.get(owner);

    }
}