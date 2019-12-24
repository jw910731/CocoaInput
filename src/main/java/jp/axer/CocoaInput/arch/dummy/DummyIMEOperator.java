
package jp.axer.CocoaInput.arch.dummy;


import jp.axer.CocoaInput.plugin.IMEOperator;

public class DummyIMEOperator implements IMEOperator {

    @Override
    public void setFocused(boolean inFocused) {
    }

    @Override
    public void discardMarkedText() {
    }

    @Override
    public void removeInstance() {
    }

    @Override
    public boolean getFocused(){
        return false;
    }
}