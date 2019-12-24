package jp.axer.CocoaInput.wrapper;

import jp.axer.CocoaInput.CocoaInput;
import jp.axer.CocoaInput.adapters.SignEditScreenInterface;
import jp.axer.CocoaInput.plugin.IMEOperator;
import jp.axer.CocoaInput.plugin.IMEReceiver;
import jp.axer.CocoaInput.util.PreeditFormatter;
import jp.axer.CocoaInput.util.Rect;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.text.LiteralText;

public class SignEditScreenWrapper implements IMEReceiver {
    private SignEditScreen owner;
    private IMEOperator myIME;
    private SignEditScreenInterface ownerAdapter;
    private int length = 0;
    private boolean hasMarkedText = false;
    private int lengthBeforeMarkedText;

    public SignEditScreenWrapper(SignEditScreen field) {
        owner = field;
        ownerAdapter = (SignEditScreenInterface) field;
        myIME = CocoaInput.getController().generateIMEOperator(this);
        myIME.setFocused(true);
    }

    @Override
    public void insertText(String aString, int position1, int length1) {
        String text = ownerAdapter.getBlockTile().getTextOnRow(ownerAdapter.getRow()).getString();
        ownerAdapter.getBlockTile().setTextOnRow(ownerAdapter.getRow(), new LiteralText(
                new StringBuffer(text).replace(lengthBeforeMarkedText, lengthBeforeMarkedText + length, aString)
                        .toString()));
        hasMarkedText = false;
        length = 0;
    }

    @Override
    public void setMarkedText(String aString, int position1, int length1, int position2, int length2) {
        String str = PreeditFormatter.formatMarkedText(aString, position1, length1)._1();
        String text = ownerAdapter.getBlockTile().getTextOnRow(ownerAdapter.getRow()).getString();
        if (hasMarkedText == false) {
            hasMarkedText = true;
            lengthBeforeMarkedText = text.length();
        }
        ownerAdapter.getBlockTile().setTextOnRow(ownerAdapter.getRow(), new LiteralText(
                new StringBuffer(text).replace(lengthBeforeMarkedText, lengthBeforeMarkedText + length, str)
                        .toString()));
        length = str.length();
    }

    @Override
    public Rect getRect() {
        TextRenderer font = ownerAdapter.getFont();
        float y = 91 + (ownerAdapter.getRow() - 1) * (10);
        if (!(ownerAdapter.getBlockTile().getCachedState().getBlock() instanceof AbstractSignBlock)) {
            y += 30;
        }
        return new Rect(owner.width / 2 + font.getStringWidth(ownerAdapter.getBlockTile().getTextOnRow(ownerAdapter.getRow()).getString()) / 2, y, 0, 0);
    }

}