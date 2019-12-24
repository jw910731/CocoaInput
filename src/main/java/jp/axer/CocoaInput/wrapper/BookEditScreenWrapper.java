package jp.axer.CocoaInput.wrapper;

import java.util.List;


import jp.axer.CocoaInput.CocoaInput;
import jp.axer.CocoaInput.plugin.BookEditScreenInterface;
import jp.axer.CocoaInput.plugin.IMEOperator;
import jp.axer.CocoaInput.plugin.IMEReceiver;
import jp.axer.CocoaInput.util.PreeditFormatter;
import jp.axer.CocoaInput.util.Rect;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;

import javax.xml.soap.Text;

public class BookEditScreenWrapper implements IMEReceiver {
    private IMEOperator myIME;
    private BookEditScreen owner;
    private BookEditScreenInterface ownerAdapter;
    private int length = 0;
    private boolean hasMarkedText = false;
    private int lengthBeforeMarkedText;

    public BookEditScreenWrapper(BookEditScreen field) {
        owner = field;
        ownerAdapter = (BookEditScreenInterface)field;
        myIME = CocoaInput.getController().generateIMEOperator(this);
        myIME.setFocused(true);
    }

    @Override
    public void insertText(String aString, int position1, int length1) {
        if (ownerAdapter.getSigning()) {
            ownerAdapter.setTitle(new StringBuffer(ownerAdapter.getTitleTexts()).replace(lengthBeforeMarkedText,
                    lengthBeforeMarkedText + length, aString).toString());
            hasMarkedText = false;
            length = 0;
        } else {
            ownerAdapter.setCurrentPageContent(new StringBuffer(ownerAdapter.getCurrentPageContent_()).replace(
                    lengthBeforeMarkedText, lengthBeforeMarkedText + length, aString).toString());
            hasMarkedText = false;
            length = 0;
        }
    }

    @Override
    public void setMarkedText(String aString, int position1, int length1, int position2, int length2) {
        String str = PreeditFormatter.formatMarkedText(aString, position1, length1)._1();
        if (ownerAdapter.getSigning()) {
            if (hasMarkedText == false) {
                hasMarkedText = true;
                lengthBeforeMarkedText = ownerAdapter.getTitleTexts().length();
            }
            ownerAdapter.setTitle(new StringBuffer(ownerAdapter.getTitleTexts()).replace(lengthBeforeMarkedText,
                    lengthBeforeMarkedText + length, str).toString());
            length = str.length();
        } else {
            if (hasMarkedText == false) {
                hasMarkedText = true;
                lengthBeforeMarkedText = ownerAdapter.getCurrentPageContent_().length();
            }
            ownerAdapter.setCurrentPageContent(new StringBuffer(ownerAdapter.getCurrentPageContent_()).replace(
                    lengthBeforeMarkedText, lengthBeforeMarkedText + length, str).toString());
            length = str.length();
        }
    }

    @Override
    public Rect getRect() {
        /*TextRenderer fontRendererObj = null;
        try {
            fontRendererObj = WrapperUtil.makeFontRenderer(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        TextRenderer font = ownerAdapter.getFont();
        if (ownerAdapter.getSigning()) {
            return new Rect(
                    (font.getStringWidth(ownerAdapter.getTitleTexts()) / 2 + ((owner.width - 192) / 2) + 36 + (116 - 0) / 2),
                    (50 + font.fontHeight),
                    0,
                    0
            );
        } else {
            List<String> lines = font.wrapStringToWidthAsList(ownerAdapter.getCurrentPageContent_(), 116);
            return new Rect(
                    (((owner.width - 192) / 2) + 36 + font.getStringWidth(lines.get(lines.size() - 1))),
                    (34 + lines.size() * font.fontHeight),
                    0,
                    0
            );
        }
    }

}