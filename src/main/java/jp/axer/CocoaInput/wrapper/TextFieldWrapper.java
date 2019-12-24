package jp.axer.CocoaInput.wrapper;

import jp.axer.CocoaInput.CocoaInput;
import jp.axer.CocoaInput.plugin.AbstractButtonWidgetInterface;
import jp.axer.CocoaInput.plugin.IMEOperator;
import jp.axer.CocoaInput.plugin.IMEReceiver;
import jp.axer.CocoaInput.plugin.TextFieldInterface;
import jp.axer.CocoaInput.util.PreeditFormatter;
import jp.axer.CocoaInput.util.Rect;
import jp.axer.CocoaInput.util.Tuple3;
import net.minecraft.client.gui.widget.TextFieldWidget;

public class TextFieldWrapper implements IMEReceiver {
    private IMEOperator myIME;
    private TextFieldWidget owner;
    private TextFieldInterface ownerAdapter;
    private AbstractButtonWidgetInterface ownerParentAdapter;
    private int length = 0;
    private boolean cursorVisible = true;
    private boolean preeditBegin = false;
    private int originalCursorPosition = 0;

    public TextFieldWrapper(TextFieldWidget field){
        owner = field;
        ownerAdapter = (TextFieldInterface)field;
        myIME = CocoaInput.getController().generateIMEOperator(this);
    }

    public void setFocused(boolean newParam) {
        if (newParam != myIME.getFocused()) {
            myIME.setFocused(newParam);
        }
    }

    @Override
    public void insertText(String aString, int position1, int length1) {
        if (!preeditBegin) {
            originalCursorPosition = owner.getCursor();
        }
        preeditBegin = false;
        cursorVisible = true;
        if (aString.length() == 0) {
            owner.setText((new StringBuffer(owner.getText())).replace(originalCursorPosition, originalCursorPosition + length, "").toString());
            length = 0;
            owner.setCursor(originalCursorPosition);
            return;
        }
        owner.setText((new StringBuffer(owner.getText()))
                .replace(originalCursorPosition, originalCursorPosition + length, aString.substring(0, aString.length()))
                .toString());
        length = 0;
        owner.setCursor(originalCursorPosition + aString.length());
    }

    @Override
    public void setMarkedText(String aString, int position1, int length1, int position2, int length2) {
        if (!preeditBegin) {
            originalCursorPosition = owner.getCursor();
            preeditBegin = true;
        }
        Tuple3<String, Integer, Boolean> formattedText = PreeditFormatter.formatMarkedText(aString, position1, length1);
        String str = formattedText._1();
        int caretPosition = formattedText._2();//相対値
        boolean hasCaret = formattedText._3();
        if (hasCaret) {
            this.cursorVisible = true;
            owner.setCursor(originalCursorPosition + caretPosition);
        } else {
            this.cursorVisible = false;
            ownerAdapter.setFocusedTicks(6);
            owner.setCursor(originalCursorPosition);
        }
        owner.setText((new StringBuffer(owner.getText())).replace(originalCursorPosition, originalCursorPosition + length, str).toString());
        length = str.length();
    }

    @Override
    public Rect getRect() {
        return new Rect(//{x,y}
                (ownerAdapter.getTextRenderer().getStringWidth(owner.getText().substring(0, originalCursorPosition)) + owner.x),
                (ownerAdapter.getTextRenderer().fontHeight + owner.y),
                owner.getWidth(),
                ownerAdapter.getHeight()
        );
    }
}
